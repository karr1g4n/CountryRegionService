package tech.pragmat.countryregionservice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tech.pragmat.countryregionservice.service.NewCountryRegionService;

@SpringBootTest
@Testcontainers
public class NewCountryRegionTest {

    @Autowired
    private NewCountryRegionService countryRegionService;

    private static final String DOCKER_IMAGE = "postgres:13";
    private static final String DB_NAME = "region";
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
    public void addAll() {
        countryRegionService.addAll();
    }
}
