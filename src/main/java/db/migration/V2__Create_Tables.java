package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V2__Create_Tables extends BaseJavaMigration {

    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create table  country(id SERIAL not null primary key unique," +
                        "country varchar(255))");

        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create table  region(id SERIAL not null primary key unique," +
                        "region varchar(255))");


        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create table  new_country_region(id SERIAL not null primary key unique," +
                        "country int ," +
                        "region int ," +
                        "FOREIGN KEY (country) REFERENCES country (id)," +
                        "FOREIGN KEY (region) REFERENCES region (id))");


    }

}
