package com.example.firstspring.service;

import com.example.firstspring.feign.CountryRegionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyFeignService {

    private CountryRegionClient countryRegionClient;

    @Autowired
    public CountyFeignService(CountryRegionClient countryRegionClient){
        this.countryRegionClient=countryRegionClient;
    }


    public String getCountryName(String ip){
        return countryRegionClient.getCountryName(ip);
    }
}
