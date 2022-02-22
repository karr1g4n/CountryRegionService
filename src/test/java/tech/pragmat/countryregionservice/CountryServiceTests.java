package tech.pragmat.countryregionservice;

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
import tech.pragmat.countryregionservice.repository.CountryRepository;
import tech.pragmat.countryregionservice.service.CountryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Testcontainers
public class CountryServiceTests {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    private static final String DOCKER_IMAGE = "postgres:13";
    private static final String DB_NAME = "country";
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
    void addCountryTest() {
        Country country = countryService.addCountry("Ukraine");
        assertEquals(country, countryRepository.findByCountry("Ukraine"));
    }

    @Test
    @Transactional
    void adTwoIdenticalCountryTest() {
        countryService.addCountry("Ukraine");
        Country country1 = countryService.addCountry("Ukraine");
        assertNull(country1);
    }

    @Test
    @Transactional
    public void addCountryWithEmptyName() {
        assertNull(countryService.addCountry(""));
    }

    @Test
    @Transactional
    public void addCountryNameWithNumber() {
        assertNull(countryService.addCountry("Ukraine1"));
    }

    @Test
    @Transactional
    public void addCountryNameShorterThanFive() {
        assertNull(countryService.addCountry("UA"));
    }

    @Test
    @Transactional
    public void addAllCountry() {
        countryService.addAllCountry();
        assertEquals(252, countryService.getAllCountry().size());
    }

    @Test
    @Transactional
    public void getCountryByRightName() {
        Country country = countryService.addCountry("Ukraine");
        assertEquals(country, countryService.getCountryByName("Ukraine"));
    }

    @Test
    @Transactional
    public void getCountryByNullName() {
        countryService.addCountry(null);
        assertNull(countryService.getCountryByName("Ukraine"));
    }

    @Test
    @Transactional
    public void deleteCountryByRightName() {
        countryService.addCountry("Ukraine");
        countryService.deleteCountryByName("Ukraine");
        assertNull(countryService.getCountryByName("Ukraine"));
    }

}