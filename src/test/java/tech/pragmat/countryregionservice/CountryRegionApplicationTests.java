package tech.pragmat.countryregionservice;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.pragmat.countryregionservice.dao.impl.CountryRegionDaoImpl;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

@SpringBootTest
class CountryRegionApplicationTests {

    private final CountryRegionDaoImpl countryRegionDao = new CountryRegionDaoImpl();

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
        System.out.println("Code executes only once");
    }

    @Test
    public void DBContainsCountryRegion() {
        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "assert");
        CountryRegion countryRegion = new CountryRegion(1, "Andorra", "world", countryRegionAccess, countryRegionAccess);

        CountryRegion countryRegion1 = countryRegionDao.getById(1);


        System.out.println(countryRegion1.toString());
        Assertions.assertEquals(countryRegion, countryRegionDao.getById(1));
    }
}
