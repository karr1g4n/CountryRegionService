package tech.pragmat.countryregionservice.dao.impl;

import lombok.extern.slf4j.Slf4j;
import tech.pragmat.countryregionservice.connection.ConnectionPool;
import tech.pragmat.countryregionservice.dao.Dao;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CountryRegionAccessDaoImpl implements Dao<CountryRegionAccess, Integer> {

    private final ConnectionPool connectionPool = new ConnectionPool();
    private static final String CREATE_QUERY = "insert into country_region_access(id, access) values(?,?)";
    private static final String UPDATE_QUERY = "update country_region_access set access = ? where id = ?";
    private static final String DELETE_QUERY = "delete from country_region_access where access = ?";
    private static final String GET_BY_ID_QUERY = "select * from country_region_access where id = ?";
    private static final String FIND_ALL_QUERY = "select * from country_region_access";

    @Override
    public boolean update(CountryRegionAccess countryRegionAccess) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, countryRegionAccess.getAccess());
            preparedStatement.setInt(2, countryRegionAccess.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return false;
    }

    @Override
    public boolean deleteByName(String name) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return false;
    }

    @Override
    public void create(CountryRegionAccess countryRegionAccess) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, countryRegionAccess.getId());
            preparedStatement.setString(2, countryRegionAccess.getAccess());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<CountryRegionAccess> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = connectionPool.getConnection()) {
            List<CountryRegionAccess> countryRegionAccesses = new ArrayList<>();
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                countryRegionAccesses.add(new CountryRegionAccess(resultSet.getInt("id"), resultSet.getString("access")));
            }
            return countryRegionAccesses;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public CountryRegionAccess getById(Integer integer) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CountryRegionAccess countryRegionAccess = null;
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
            preparedStatement.setInt(1, integer);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("id") == integer) {
                    countryRegionAccess = new CountryRegionAccess(resultSet.getInt("id"), resultSet.getString("access"));
                }
            }
            return countryRegionAccess;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }
        return null;
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error(String.valueOf(e));
            }

        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }

        }
    }
}
