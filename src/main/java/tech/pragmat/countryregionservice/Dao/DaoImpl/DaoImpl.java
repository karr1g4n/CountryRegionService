package tech.pragmat.countryregionservice.Dao.DaoImpl;

import lombok.extern.slf4j.Slf4j;
import tech.pragmat.countryregionservice.Dao.Dao;
import tech.pragmat.countryregionservice.connection.ConnectionPool;
import tech.pragmat.countryregionservice.model.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class DaoImpl implements Dao<Country, Integer> {

    private final ConnectionPool connectionPool = new ConnectionPool();
    private final String CREATE_QUERY = "insert country(id, country, region) values(?,?,?)";

    @Override
    public boolean update(Country Country) {
        return false;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public void create(Country Country) {
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, Country.getId());
            preparedStatement.setString(2, Country.getCountry());
            preparedStatement.setString(3, Country.getRegion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }

    }

    @Override
    public List<Country> findAll() {
        return null;
    }

    @Override
    public Country getById(Integer integer) {
        return null;
    }
}
