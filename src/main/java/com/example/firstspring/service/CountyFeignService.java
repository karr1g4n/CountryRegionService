package com.example.firstspring.service;

import com.example.firstspring.feign.CountryRegionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyFeignService {

    private CountryRegionClient countryRegionClient;
    private CountryRegionService countryRegionService;

    @Autowired
    public CountyFeignService(CountryRegionClient countryRegionClient,CountryRegionService countryRegionService){
        this.countryRegionClient=countryRegionClient;
        this.countryRegionService=countryRegionService;
    }

    public String getRegionName(String ip){
        String nameCountry=countryRegionClient.getCountryName(ip);
        return countryRegionService.getCountryRegionByName(nameCountry).getRegion();

    }
}
