package tutorial.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void main(String[] args) {

        JdbcCorePackage demo = new JdbcCorePackage();
        demo.setUp();
        demo.staticInsert();

    }

}
