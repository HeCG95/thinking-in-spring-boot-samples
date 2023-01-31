package tutorial.jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.ListIterator;

public class JdbcObjectPackage {

    private DataSource ds; // the data source

    private String dbUrl = "";
    private String user = "";
    private String pass = "";

    public JdbcObjectPackage() {
        this.ds = new SingleConnectionDataSource("com.mysql.jdbc.Driver", dbUrl, user, pass,
                false);;
    }

    public void setUp(){
        // Delete existing rows
        SqlUpdate upd = new SqlUpdate(ds, "delete from specialty");
        upd.compile();
        upd.update();
    }

    public void insert(){
        SqlUpdate upd = new SqlUpdate(ds,
                "insert into specialty values (?, ?)");
        upd.declareParameter(new SqlParameter(Types.VARCHAR));
        upd.declareParameter(new SqlParameter(Types.VARCHAR));
        upd.compile();
        upd.update(new Object[] {"RESP",
                "Respiratory Diseasese"} );
        upd.update(new Object[] {"HOMEO",
                "Homeopathy"} );
        upd.update(new Object[] {"INTERN",
                "Internal Medicine"} );
    }

    public void update(){
        SqlUpdate upd = new SqlUpdate(ds,
                "update specialty set sp_code = \"RESPIR\" where sp_code = \"RESP\"");
        upd.compile();
        upd.update();
    }

    public void readOneRow(){
        MyMappingQuery query = new MyMappingQuery(ds);
        List list = query.execute(new Object[] {"HOMEO"} );
        System.out.println("Listing all homeopathy rows");
        ListIterator iter = list.listIterator();
        while (iter.hasNext()) {
            Specialty spec = (Specialty)(iter.next());
            System.out.println("Specialty code = " + spec.getCode() +
                    ", specialty name = " + spec.getDescription());
        }
    }

    public void readAllRows(){
        MyQuery query = new MyQuery(ds);
        List list = query.execute();
        System.out.println("Listing all homeopathy rows");
        ListIterator iter = list.listIterator();
        while (iter.hasNext()) {
            Specialty spec = (Specialty)(iter.next());
            System.out.println("Specialty code = " + spec.getCode() +
                    ", specialty name = " + spec.getDescription());
        }
    }

    public static void main(String[] args) {

        JdbcObjectPackage demo = new JdbcObjectPackage();
        demo.setUp();
        demo.insert();
        demo.update();
        demo.readOneRow();
        demo.readAllRows();

    }

}
