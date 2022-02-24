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
import tech.pragmat.countryregionservice.model.entity.Region;
import tech.pragmat.countryregionservice.repository.RegionRepository;
import tech.pragmat.countryregionservice.service.RegionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Testcontainers
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionRepository regionRepository;

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
    @Transactional
    void addRegionTest() {
        Region region = regionService.addRegion("Восточная Европа");
        assertEquals(region, regionRepository.findByRegion("Восточная Европа"));
    }

    @Test
    @Transactional
    void addTwoIdenticalRegionTest() {
        regionService.addRegion("Восточная Европа");
        Region region = regionService.addRegion("Восточная Европа");
        assertNull(region);
    }

    @Test
    @Transactional
    public void addRegionWithEmptyName() {
        assertNull(regionService.addRegion(""));
    }

    @Test
    @Transactional
    public void addRegionNameWithNumber() {
        assertNull(regionService.addRegion("Восточная Европа1"));
    }

    @Test
    @Transactional
    public void addRegionNameShorterThanFive() {
        assertNull(regionService.addRegion("Вост"));
    }

    @Test
    @Transactional
    public void getRegionByRightName() {
        Region region = regionService.addRegion("Восточная Европа");
        assertEquals(region, regionService.getRegionByName("Восточная Европа"));
    }

    @Test
    @Transactional
    public void getRegionByNullName() {
        regionService.addRegion(null);
        assertNull(regionService.getRegionByName("Восточная Европа"));
    }

    @Test
    @Transactional
    public void deleteRegionByRightName() {
        regionService.addRegion("Восточная Европа");
        regionService.deleteRegionByName("Восточная Европа");
        assertNull(regionService.getRegionByName("Восточная Европа"));
    }


    @Test
    @Transactional
    public void addAllRegions() {
        regionService.addAllRegion();
    }

}



