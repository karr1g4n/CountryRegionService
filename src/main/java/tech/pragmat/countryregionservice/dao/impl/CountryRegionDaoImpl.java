package tech.pragmat.countryregionservice.dao.impl;

import lombok.extern.slf4j.Slf4j;
import tech.pragmat.countryregionservice.connection.ConnectionPool;
import tech.pragmat.countryregionservice.dao.Dao;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CountryRegionDaoImpl implements Dao<CountryRegion, Integer> {

    private final CountryRegionAccessDaoImpl countryRegionAccessDao = new CountryRegionAccessDaoImpl();

    private final ConnectionPool connectionPool = new ConnectionPool();
    private static final String CREATE_QUERY = "insert into country_region(id, country, region, country_access_id,region_access_id ) values(?,?,?,?,?)";
    private static final String FIND_ALL_QUERY = "select * from country_region";
    private static final String UPDATE_QUERY = "update country_region set region = ? where country = ?";
    private static final String DELETE_QUERY = "delete from country_region where country = ?";

    @Override
    public boolean update(CountryRegion countryRegion) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, countryRegion.getRegion());
            preparedStatement.setString(2, countryRegion.getCountry());
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return true;
    }

    @Override
    public boolean deleteByCountry(String countryName) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.getConnection()) {
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, countryName);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return false;
    }

    @Override
    public void create(CountryRegion countryRegion) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, countryRegion.getId());
            preparedStatement.setString(2, countryRegion.getCountry());
            preparedStatement.setString(3, countryRegion.getRegion());
            preparedStatement.setInt(4, countryRegion.getCountryAccess().getId());
            preparedStatement.setInt(5, countryRegion.getRegionAccess().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
        }

    }

    @Override
    public List<CountryRegion> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CountryRegion> countries = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(new CountryRegion(resultSet.getInt("id"), resultSet.getString("country"), resultSet.getString("region"),
                        countryRegionAccessDao.getById(resultSet.getInt("id")), countryRegionAccessDao.getById(resultSet.getInt("id"))));
            }
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        } finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
        }

        return countries;
    }

    @Override
    public CountryRegion getById(Integer integer) {
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
