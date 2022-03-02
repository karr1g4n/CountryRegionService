package tech.pragmat.countryregionservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.model.entity.NewCountryRegion;
import tech.pragmat.countryregionservice.model.entity.Region;
import tech.pragmat.countryregionservice.repository.NewCountryRegionRepository;
import tech.pragmat.countryregionservice.service.CountryService;
import tech.pragmat.countryregionservice.service.NewCountryRegionService;
import tech.pragmat.countryregionservice.service.RegionService;

@SpringBootTest
@Testcontainers
public class NewCountryRegionTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private NewCountryRegionService newCountryRegionService;

    @Autowired
    private NewCountryRegionRepository newCountryRegionRepository;

    private static final String DOCKER_IMAGE = "postgres:13";
    private static final String DB_NAME = "new_country_region";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "43ahegib";

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer(DOCKER_IMAGE)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withDatabaseName(DB_NAME);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }


    @Test
    @Transactional
    public void AddNewCountryRegion() {
        Country country = countryService.addCountry("Ukraine");
        Region region = regionService.addRegion("EU");
        NewCountryRegion newCountryRegion = newCountryRegionService.addNewCountryRegion("Ukraine", "EU");
        Assertions.assertEquals(newCountryRegion, newCountryRegionRepository.findByCountry(country));
    }

    @Test
    @Transactional
    public void addAllTest() {
        newCountryRegionService.addAll();
    }

    @Test
    @Transactional
    public void GetAllNewCountryRegionTest() {
        countryService.addAllCountry();
        regionService.addAllRegion();
        newCountryRegionService.addAll();
        Assertions.assertEquals(252, newCountryRegionService.getAllNewCountryRegion().size());
    }

    @Test
    @Transactional
    public void GetNewCountryRegionByCountryTest() {
        Country country = countryService.addCountry("Ukraine");
        Region region = regionService.addRegion("EU");
        NewCountryRegion newCountryRegion = newCountryRegionService.addNewCountryRegion("Ukraine", "EU");
        Assertions.assertEquals(newCountryRegion, newCountryRegionService.getNewCountryRegionByName("Ukraine"));
    }

    @Test
    @Transactional
    public void deleteNewCountryRegionTest() {
        Country country = countryService.addCountry("Ukraine");
        Region region = regionService.addRegion("EU");
        NewCountryRegion newCountryRegion = newCountryRegionService.addNewCountryRegion("Ukraine", "EU");
        newCountryRegionService.deleteNewCountryRegion("Ukraine");
        Assertions.assertNull(newCountryRegionService.getNewCountryRegionByName("Ukraine"));
    }


}
