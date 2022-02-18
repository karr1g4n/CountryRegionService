package tech.pragmat.countryregionservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.repository.CountryRepository;
import tech.pragmat.countryregionservice.service.CountryNameClientService;
import tech.pragmat.countryregionservice.service.CountryService;

@SpringBootTest
@Testcontainers
@ContextConfiguration
public class CountryServiceTests {

    private static final String DOCKER_IMAGE = "postgres:13";
    private static final String DB_NAME = "country_region";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "43ahegib";

    @Autowired
    public CountryService countryService;

    @Autowired
    public CountryRepository countryRepository;

    @Autowired
    public CountryNameClientService countryNameClientService;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DOCKER_IMAGE)
            .withDatabaseName(DB_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD);

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void addCountryTest() {
        Country country = new Country();
        Assertions.assertNull(country);
    }

    //    @Test
    //    @Transactional
    //    public void addCountryTest() {
    //        String countryName = "UA";
    //        countryService.addCountry(countryName);
    //
    //        Assertions.assertEquals(countryName, countryRepository.findByCountry("UA").getCountry());
    //    }

    //    @Test
    //    @Transactional
    //    public void addTwoCountryWithIntentionalValuesTest() {
    //        String countryName = "UA";
    //        countryService.addCountry(countryName);
    //        countryService.addCountry(countryName);
    //                Assertions.assertEquals(new Country(2,"UA"),countryService.addCountry(countryName));
    //    }
    //
    //    @Test
    //    @Transactional
    //    public void addCountryTestWithNull() {
    //        Country country = countryService.addCountry(null);
    //        Assertions.assertNull(country);
    //    }
    //
    //    @Test
    //    @Transactional
    //    public void addAllCountry() {
    //        countryService.addAllCountry();
    //    }
    //
    //    @Test
    //    @Transactional
    //    public void getAllCountry() {
    //        List<Country> countryList = countryService.getAllCountry();
    //        Assertions.assertEquals(252, countryList.size());
    //    }
    //
    //    @Test
    //    @Transactional
    //    public void getCountyByName() {
    //        countryService.addCountry("UA");
    //        Country country = new Country(1, "UA");
    //        Assertions.assertEquals(country, countryService.getCountryByName("UA"));
    //    }
    //
    //    @Test
    //    @Transactional
    //    public void getCountyByNameNull() {
    //        Assertions.assertNull(countryService.getCountryByName("UA2"));
    //    }
    //
    //    @Test
    //    @Transactional
    //    public void deleteCountryByName() {
    //        countryService.deleteCountryByName("UA");
    //        Assertions.assertNull(countryRepository.findByCountry("UA"));
    //    }
}
