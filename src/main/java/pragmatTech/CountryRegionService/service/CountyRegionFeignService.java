package pragmatTech.CountryRegionService.service;

import pragmatTech.CountryRegionService.feign.CountryRegionClient;
import pragmatTech.CountryRegionService.feign.GeoNameClient;
import org.springframework.stereotype.Service;

@Service
public class CountyRegionFeignService {

    private CountryRegionClient countryRegionClient;
    private CountryRegionService countryRegionService;


    public CountyRegionFeignService(CountryRegionClient countryRegionClient, CountryRegionService countryRegionService, GeoNameClient geoNameClient){
        this.countryRegionClient=countryRegionClient;
        this.countryRegionService=countryRegionService;

    }

    public String getRegionName(String ip){
        String nameCountry=countryRegionClient.getCountryName(ip);
        return countryRegionService.getCountryRegionByName(nameCountry).getRegion();

    }
}
