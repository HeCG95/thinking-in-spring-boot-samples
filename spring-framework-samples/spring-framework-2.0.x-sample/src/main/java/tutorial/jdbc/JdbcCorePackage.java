package tutorial.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ListIterator;

public class JdbcCorePackage {

    private JdbcTemplate tpl;
    private DataSource ds; // the data source

    private String dbUrl = "";
    private String user = "";
    private String pass = "";

    public JdbcCorePackage() {
        this.ds = new SingleConnectionDataSource("com.mysql.jdbc.Driver", dbUrl, user, pass,
                false);;
        this.tpl = new JdbcTemplate(ds);
    }

    public void setUp(){
        // Delete existing rows
        tpl.update("delete from specialty");
    }

    public void staticInsert(){
        // Insert one row
        int numRows = tpl.update("insert into specialty values(\"HOMEO\", \"Homeopathy\")");
        if (1 == numRows) {
            System.out.println("One row was inserted. Rows in table are:");
            // Need a RowCallbackHandler
            tpl.query("select * from specialty",
                    new RowCallbackHandler() {
                        public void processRow(ResultSet rs) throws SQLException {
                            System.out.println("Specialty code = " + rs.getString(1) +", specialty name = " + rs.getString(2));
                        }
                    }
            );
        }
    }

    public void dynamicInsert(){
        // Insert one row
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            private String sql = "insert into specialty values(?, ?)";
            public String getSql() { return sql; }
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, "INTERN");
                ps.setString(2, "Internal Medicine");
                return ps;
            }
        };

        int numRows = tpl.update(psc);
        if (1 == numRows) {
            System.out.println("One row was inserted. Rows in table are:");
            // Need a RowCallbackHandler
            tpl.query("select * from specialty",
                    new RowCallbackHandler() {
                        public void processRow(ResultSet rs) throws SQLException {
                            System.out.println("Specialty code = " +
                                    rs.getString(1) + ", specialty name = " + rs.getString(2));
                        }
                    }
            );

        }
    }

    public void dynamicInsertUsingPSFactory(){
        // Insert one row
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        Object[] params = new Object[] { new String("RESP"),
                new String("Respiratory Diseases") };
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory("insert into specialty values(?, ?)", types).
                        newPreparedStatementCreator(params);
//                PreparedStatementCreatorFactory.newPreparedStatementCreator("insert into specialty values(?, ?)", types, params);

        int numRows = tpl.update(psc);
        if (1 == numRows) {
            System.out.println("One row was inserted. Rows in table are:");
            // Need a RowCallbackHandler
            tpl.query("select * from specialty",
                    new RowCallbackHandler() {
                        public void processRow(ResultSet rs) throws SQLException {
                            System.out.println("Specialty code = " +
                                    rs.getString(1) + ", specialty name = " + rs.getString(2));
                        }
                    }
            );

        }
    }

    /**
     * ResultReader 换成了 RowMapper
     */
    public void readAllRows(){
//        new
        RowMapper reader = new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Specialty spec = new Specialty();
                spec.setCode(rs.getString(1));
                spec.setDescription(rs.getString(2));
                return spec;
            }
        };

        List list = tpl.query("select * from specialty", reader);
        System.out.println("Listing all rows");
        ListIterator iter = list.listIterator();
        while (iter.hasNext()) {
            Specialty spec = (Specialty)(iter.next());
            System.out.println("Specialty code = " +
                    spec.getCode() + ", specialty name = " +
                    spec.getDescription());
        }
    }

    public void insertWithSequence() throws DataAccessException {
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR };
        String[] species = new String[] {"Cat", "Dog", "Horse"};
        JdbcTemplate tpl = new JdbcTemplate(ds);
        // First clean up the table
        tpl.update("delete from species");
        // Read keys by bunches of 2 values
        MySQLMaxValueIncrementer incr = new MySQLMaxValueIncrementer(ds, "species_seq", "seq");
        incr.setCacheSize(2);

        // Insert 3 records
        for (int i = 0; i < 3; i++) {
            Object[] params = new Object[] { new Integer(incr.nextIntValue()), species[i] };
            PreparedStatementCreator psc =
                    new PreparedStatementCreatorFactory("insert into species values(?, ?)", types).
                            newPreparedStatementCreator(params);
            tpl.update(psc);
            System.out.println("Inserted species with id = " + params[0]);
        }

        // List all rows
        tpl.query("select * from species",
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        System.out.println("Species id = " + rs.getInt(1) +
                                ", species name = " + rs.getString(2));
                    }
                }
        );
    }

    public static void main(String[] args) {

        JdbcCorePackage demo = new JdbcCorePackage();
//        demo.setUp();
//        demo.staticInsert();
//        demo.dynamicInsert();
//        demo.dynamicInsertUsingPSFactory();
//        demo.readAllRows();
        demo.insertWithSequence();

    }

}
