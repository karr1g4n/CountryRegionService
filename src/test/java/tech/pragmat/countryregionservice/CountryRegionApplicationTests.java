package tech.pragmat.countryregionservice;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;
import tech.pragmat.countryregionservice.service.CountryRegionService;
import tech.pragmat.countryregionservice.web.controller.ConnectionWithGeoLite2CountryController;
import tech.pragmat.countryregionservice.web.controller.CountryRegionController;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CountryRegionApplicationTests {

    @Autowired
    private CountryRegionRepository countryRegionRepository;

    @Autowired
    private CountryRegionService countryRegionService;

    @Autowired
    private CountryRegionController countryRegionController;

    private ConnectionWithGeoLite2CountryController connectionWithGeoLite2CountryController;


    @Test
    @Transactional
    public void TestAddCountryRegion() {
        CountryRegion countryRegion = new CountryRegion(253, "A", "world");
        countryRegionController.addCountryRegion(countryRegion);
        Assertions.assertEquals(countryRegion.getCountry(), countryRegionService.getCountryRegionByName("A").getCountry());
    }

//    @Test
//    @Transactional
//    public void TestUpdateCountryRegion() {
//        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
//        countryRegion.setRegion("UA");
//        countryRegionController.updateCountryRegion(countryRegion);
//        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
//    }
//
//    @Test
//    @Transactional
//    public void TestUpdateCountryAccess() {
//        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
//        countryRegion.setCountryAccess(countryRegionBlocked);
//        countryRegionController.updateCountryAccess("Andorra", "blocked");
//        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
//    }
//
//    @Test
//    @Transactional
//    public void TestUpdateRegionAccess() {
//        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
//        countryRegion.setRegionAccess(countryRegionBlocked);
//        countryRegionController.updateRegionAccess("Andorra", "blocked");
//        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
//    }
//
//    @Test
//    @Transactional
//    public void TestGetByName() {
//        CountryRegion countryRegion = new CountryRegion(1, "Andorra", "world", countryRegionAccess, countryRegionAccess);
//        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
//    }
//
//    @Test
//    @Transactional
//    public void TestDeleteByName() {
//        countryRegionController.delCountryByName("Andorra");
//        Assertions.assertNull(countryRegionRepository.findByCountry("Andorra"));
//    }
//
//    @Test
//    public void getCountryByIp() {
//        ConnectionWithGeoLite2CountryController mockConnection = mock(ConnectionWithGeoLite2CountryController.class);
//        when(mockConnection.get("103.13.64.0")).thenReturn("Afghanistan");
//        Assertions.assertEquals(mockConnection.get("103.13.64.0"), countryRegionService.getCountryRegionByName("Afghanistan").getCountry());
//    }

}
