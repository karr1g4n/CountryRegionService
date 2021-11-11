package com.example.firstspring.service;

import com.example.firstspring.feign.CountryRegionClient;
import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyFeignService {

    private CountryRegionClient countryRegionClient;
    private CountryRegionRepository countryRegionRepository;
    private CountryRegionService countryRegionService;

    @Autowired
    public CountyFeignService(CountryRegionClient countryRegionClient,CountryRegionRepository countryRegionRepository,CountryRegionService countryRegionService){
        this.countryRegionClient=countryRegionClient;
        this.countryRegionRepository=countryRegionRepository;
        this.countryRegionService=countryRegionService;
    }

    public String getRegionName(String ip){
        String nameCountry=countryRegionClient.getCountryName(ip);
        return nameCountry;

    }
}
