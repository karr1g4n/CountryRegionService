package tech.pragmat.CountryRegionService.service;

import org.springframework.stereotype.Service;
import tech.pragmat.CountryRegionService.feign.CountryRegionClient;
import tech.pragmat.CountryRegionService.feign.GeoNameClient;

@Service
public class CountyRegionFeignService {

    private CountryRegionClient countryRegionClient;
    private CountryRegionService countryRegionService;

    public CountyRegionFeignService(CountryRegionClient countryRegionClient, CountryRegionService countryRegionService, GeoNameClient geoNameClient) {
        this.countryRegionClient = countryRegionClient;
        this.countryRegionService = countryRegionService;

    }

    public String getRegionName(String ip) {
        String nameCountry = countryRegionClient.getCountryName(ip);
        return countryRegionService.getCountryRegionByName(nameCountry).getRegion();

    }
}
