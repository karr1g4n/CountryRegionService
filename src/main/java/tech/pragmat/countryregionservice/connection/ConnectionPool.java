package tech.pragmat.countryregionservice.connection;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Slf4j
public class ConnectionPool {

    private DataSource dataSource;

    public ConnectionPool() {
        try {
            ResourceBundle dbConfig = ResourceBundle.getBundle("credits");
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(dbConfig.getString("db.connection.url"));
            basicDataSource.setDriverClassName(dbConfig.getString("db.connection.driver"));
            basicDataSource.setUsername(dbConfig.getString("db.connection.username"));
            basicDataSource.setPassword(dbConfig.getString("db.connection.password"));
            dataSource = basicDataSource;
        } catch (Exception e) {
            log.error("Error connection " + e);
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Error connection " + e);
            throw new SQLException();
        }

    }
}
