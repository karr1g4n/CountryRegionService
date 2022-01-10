package tech.pragmat.countryregionservice.Dao.DaoImpl;

import lombok.extern.slf4j.Slf4j;
import tech.pragmat.countryregionservice.Dao.Dao;
import tech.pragmat.countryregionservice.connection.ConnectionPool;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CountryRegionAccessDaoImpl implements Dao<CountryRegionAccess, Integer> {

    private final ConnectionPool connectionPool = new ConnectionPool();
    private final String CREATE_QUERY = "insert into country_region_access(id, access) values(?,?)";
    private final String FIND_ALL_QUERY = "select * from country_region_access";
    private final String UPDATE_QUERY = "update country_region_access set access = ? where id = ?";
    private final String DELETE_QUERY = "delete from country_region_access where access = ?";
    private final String GET_BY_ID_QUERY = "select * from country_region_access where id = ?";


    @Override
    public boolean update(CountryRegionAccess countryRegionAccess) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, countryRegionAccess.getAccess());
            preparedStatement.setInt(2, countryRegionAccess.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return false;
    }

    @Override
    public boolean deleteByCountry(String name) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return false;
    }

    @Override
    public void create(CountryRegionAccess countryRegionAccess) {
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, countryRegionAccess.getId());
            preparedStatement.setString(2, countryRegionAccess.getAccess());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }

    }

    @Override
    public List<CountryRegionAccess> findAll() {
        try (Connection connection = connectionPool.getConnection()) {
            List<CountryRegionAccess> countryRegionAccesses = new ArrayList<>();
            log.info("Connection successful");
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countryRegionAccesses.add(new CountryRegionAccess(resultSet.getInt("id"), resultSet.getString("access")));
            }
            return countryRegionAccesses;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return null;
    }

    @Override
    public CountryRegionAccess getById(Integer integer) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("id") == integer) {
                    CountryRegionAccess countryRegionAccess = new CountryRegionAccess(resultSet.getInt("id"), resultSet.getString("access"));
                    return countryRegionAccess;
                }
            }

        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return null;
    }
}
