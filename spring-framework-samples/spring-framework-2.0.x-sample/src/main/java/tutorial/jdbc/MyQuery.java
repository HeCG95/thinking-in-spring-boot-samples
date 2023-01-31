package tutorial.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MyQuery extends SqlQuery {

    public MyQuery(DataSource ds) {
        super(ds, "select sp_code, sp_name from specialty");
    }

    @Override
    protected RowMapper newRowMapper(Object[] objects, Map map) {
        return new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Specialty spec = new Specialty();
                spec.setCode(rs.getString(1));
                spec.setDescription(rs.getString(2));
                return spec;
            }
        };
    }
}
