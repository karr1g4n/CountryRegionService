package tech.pragmat.countryregionservice.Dao.DaoImpl;

import lombok.extern.slf4j.Slf4j;
import tech.pragmat.countryregionservice.Dao.Dao;
import tech.pragmat.countryregionservice.connection.ConnectionPool;
import tech.pragmat.countryregionservice.model.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DaoImpl implements Dao<Country, Integer> {

    private final ConnectionPool connectionPool = new ConnectionPool();
    private final String CREATE_QUERY = "insert into country(id, country, region) values(?,?,?)";
    private final String FIND_ALL_QUERY = "select * from country_region";
    private final String UPDATE_QUERY = "update country set region = ? where country = ?";
    private final String DELETE_QUERY = "delete from country where country = ?";
    private final String GET_BY_ID_QUERY = "select * from jobs where id = ?";

    @Override
    public boolean update(Country Country) {

        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, Country.getRegion());
            preparedStatement.setString(2, Country.getCountry());
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return true;
    }

    @Override
    public boolean deleteByCountry(String country) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, country);
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
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
        List<Country> countries = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(new Country( resultSet.getInt("id"), resultSet.getString("country"), resultSet.getString("region")));
            }
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return countries;
    }

    @Override
    public Country getById(Integer integer) {
        return null;
    }
}
