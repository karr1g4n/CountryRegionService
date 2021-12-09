package tech.pragmat.countryregionservice.service;

import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.feign.CountryRegionClient;

@Service
public class CountyRegionFeignService {

    private final CountryRegionClient countryRegionClient;
    private final CountryRegionService countryRegionService;

    public CountyRegionFeignService(CountryRegionClient countryRegionClient, CountryRegionService countryRegionService) {
        this.countryRegionClient = countryRegionClient;
        this.countryRegionService = countryRegionService;

    }

    public String getRegionName(String ip) {
        String nameCountry = countryRegionClient.getCountryName(ip);
        return countryRegionService.getCountryRegionByName(nameCountry).getRegion();

    }
}
