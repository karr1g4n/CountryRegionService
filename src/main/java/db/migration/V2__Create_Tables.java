package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V2__Create_Tables extends BaseJavaMigration {

    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("CREATE TABLE country (id serial PRIMARY KEY UNIQUE ,"
                        + "country varchar (255) UNIQUE) ");

        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create table  region(id serial PRIMARY KEY UNIQUE  ," +
                        "region varchar(255) UNIQUE)");

        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create table  new_country_region(id serial PRIMARY KEY UNIQUE,"
                        + "country_id int ,"
                        + "region_id int ,"
                        + "FOREIGN KEY (country_id ) REFERENCES country(id),"
                        + "FOREIGN KEY (region_id)REFERENCES region(id))");
    }

}
