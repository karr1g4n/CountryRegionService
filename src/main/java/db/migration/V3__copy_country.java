package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.repository.CountryRepository;

import java.util.List;

public class V3__copy_country extends BaseJavaMigration {

    @Autowired
    CountryRepository countryRepository;

    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
        List<String> countryList = jdbcTemplate.queryForList("select country from country_region", String.class);
        for (int i = 0; i < countryList.size(); i++) {

            Country country = new Country(i, countryList.get(i));
            jdbcTemplate.update("insert into country(country) values(?)",country.getCountry());
        }
    }
}