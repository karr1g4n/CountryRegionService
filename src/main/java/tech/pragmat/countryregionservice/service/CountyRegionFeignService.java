package tech.pragmat.countryregionservice.service;

import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.feign.CountryRegionClient;
import tech.pragmat.countryregionservice.feign.GeoNameClient;

@Service
public class CountyRegionFeignService {

    private final CountryRegionClient countryRegionClient;
    private final CountryRegionService countryRegionService;

    public CountyRegionFeignService(CountryRegionClient countryRegionClient, CountryRegionService countryRegionService, GeoNameClient geoNameClient) {
        this.countryRegionClient = countryRegionClient;
        this.countryRegionService = countryRegionService;

    }

    public String getRegionName(String ip) {
        String nameCountry = countryRegionClient.getCountryName(ip);
        return countryRegionService.getCountryRegionByName(nameCountry).getRegion();

    }
}
