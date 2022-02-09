package tech.pragmat.countryregionservice;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;
import tech.pragmat.countryregionservice.service.CountryRegionService;
import tech.pragmat.countryregionservice.web.controller.CountryRegionController;

@SpringBootTest
class CountryRegionApplicationTests {

    @Autowired
    private CountryRegionRepository countryRegionRepository;

    @Autowired
    private CountryRegionService countryRegionService;

    @Autowired
    private CountryRegionController countryRegionController;

    private final CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");

    private final CountryRegionAccess countryRegionBlocked = new CountryRegionAccess(1, "blocked");

    @Test
    @Transactional
    public void TestAddCountryRegion() {
        CountryRegion countryRegion = new CountryRegion(253, "A", "world", countryRegionAccess, countryRegionAccess);
        countryRegionService.addCountryRegion(countryRegion);
        Assert.assertEquals(countryRegion.getCountry(), countryRegionService.getCountryRegionByName("A").getCountry());
    }

    @Test
    @Transactional
    public void TestUpdateCountryRegion() {
        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
        countryRegion.setRegion("UA");
        countryRegionController.updateCountryRegion(countryRegion);

        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
    }

    @Test
    @Transactional
    public void TestUpdateCountryAccess() {
        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
        countryRegion.setCountryAccess(countryRegionBlocked);
        countryRegionController.updateCountryAccess("Andorra", "blocked");
        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
    }

    @Test
    @Transactional
    public void TestUpdateRegionAccess() {
        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
        countryRegion.setRegionAccess(countryRegionBlocked);
        countryRegionController.updateRegionAccess("Andorra", "blocked");
        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
    }

    @Test
    @Transactional
    public void TestGetByName() {
        CountryRegion countryRegion = new CountryRegion(1, "Andorra", "world", countryRegionAccess, countryRegionAccess);

        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
    }

    //    @Test
    //    @Transactional
    //    public void TestDeleteByName() {
    //        CountryRegion countryRegion = new CountryRegion(1, "Andorra", "world", countryRegionAccess, countryRegionAccess);
    //        countryRegionController.delCountryByName("Andorra");
    ////        System.out.println(countryRegionService.getCountryRegionByName("Andorra"));
    //        //        Assertions.assertEquals(countryRegion, countryRegionService.getCountryRegionByName("Andorra"));
    //    }

//    @Test
//    @Transactional
//    public void Test() {
//        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
//        countryRegionRepository.delete(countryRegion);
//        Assert.assertEquals(null, countryRegionService.getCountryRegionByName("Andorra"));
//    }


    @Test
    @Transactional
    public void Test() {
        CountryRegion countryRegion = countryRegionService.getCountryRegionByName("Andorra");
        countryRegionRepository.deleteByCountry("Andorra");
        Assert.assertEquals(null, countryRegionService.getCountryRegionByName("Andorra"));
    }
}
