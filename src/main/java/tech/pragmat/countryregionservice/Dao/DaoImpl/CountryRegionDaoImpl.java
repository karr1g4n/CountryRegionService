package tech.pragmat.countryregionservice.Dao.DaoImpl;

import lombok.extern.slf4j.Slf4j;
import tech.pragmat.countryregionservice.Dao.Dao;
import tech.pragmat.countryregionservice.connection.ConnectionPool;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

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
    private final String CREATE_QUERY = "insert into country_region(id, country, region, country_access_id,region_access_id ) values(?,?,?,?,?)";
    private final String FIND_ALL_QUERY = "select * from country_region";
    private final String UPDATE_QUERY = "update country_region set region = ? where country = ?";
    private final String DELETE_QUERY = "delete from country_region where country = ?";


    @Override
    public boolean update(CountryRegion Country) {

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
    public void create(CountryRegion Country) {

        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, Country.getId());
            preparedStatement.setString(2, Country.getCountry());
            preparedStatement.setString(3, Country.getRegion());
            preparedStatement.setInt(4, Country.getCountryAccess().getId());
            preparedStatement.setInt(5,Country.getRegionAccess().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }


    }

    @Override
    public List<CountryRegion> findAll() {
        List<CountryRegion> countries = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            log.info("Connection successful");
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(new CountryRegion(resultSet.getInt("id"), resultSet.getString("country"), resultSet.getString("region"), countryRegionAccessDao.getById(resultSet.getInt("id")),countryRegionAccessDao.getById(resultSet.getInt("id"))));
            }
        } catch (SQLException e) {
            log.error("Connection failed...");
            log.error(String.valueOf(e));
        }
        return countries;
    }

    @Override
    public CountryRegion getById(Integer integer) {
        return null;
    }
}
