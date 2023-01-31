package tutorial.jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class MyMappingQuery extends MappingSqlQuery {

    public MyMappingQuery(DataSource ds) {
        super(ds, "select * from specialty where sp_code = ?");
        declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }

    @Override
    protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
        Specialty spec = new Specialty();
        spec.setCode(rs.getString(1));
        spec.setDescription(rs.getString(2));
        return spec;
    }

}
