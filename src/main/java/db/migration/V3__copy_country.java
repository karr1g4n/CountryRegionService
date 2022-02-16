package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import tech.pragmat.countryregionservice.model.entity.Country;

import java.util.List;

public class V3__copy_country extends BaseJavaMigration {

    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
        List<String> countryList = jdbcTemplate.queryForList("select country from country_region", String.class);
        for (int i = 0; i < countryList.size(); i++) {
            Country country = new Country(i+1, countryList.get(i));
            jdbcTemplate.update("insert into country(id,country) values(?,?)", country.getId(), country.getCountry());
        }
    }
}