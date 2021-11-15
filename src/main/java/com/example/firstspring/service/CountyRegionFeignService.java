package com.example.firstspring.service;

import com.example.firstspring.feign.CountryRegionClient;
import com.example.firstspring.feign.GeoNameClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyRegionFeignService {

    private CountryRegionClient countryRegionClient;
    private CountryRegionService countryRegionService;


    @Autowired
    public CountyRegionFeignService(CountryRegionClient countryRegionClient, CountryRegionService countryRegionService, GeoNameClient geoNameClient){
        this.countryRegionClient=countryRegionClient;
        this.countryRegionService=countryRegionService;

    }

    public String getRegionName(String ip){
        String nameCountry=countryRegionClient.getCountryName(ip);
        return countryRegionService.getCountryRegionByName(nameCountry).getRegion();

    }
}
