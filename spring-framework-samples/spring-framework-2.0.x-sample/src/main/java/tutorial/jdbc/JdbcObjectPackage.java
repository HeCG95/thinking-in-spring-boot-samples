package tutorial.jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

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

    public static void main(String[] args) {

        JdbcObjectPackage demo = new JdbcObjectPackage();
        demo.setUp();
        demo.insert();

    }

}
