package com.example.firstspring.service;

import com.example.firstspring.feign.CountryRegionClient;
import com.example.firstspring.model.entity.CountryRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountyFeignService {

    private CountryRegionClient countryRegionClient;

    private CountryRegionService countryRegionService;

    @Autowired
    public CountyFeignService(CountryRegionClient countryRegionClient, CountryRegionService countryRegionService) {
        this.countryRegionClient = countryRegionClient;

        this.countryRegionService = countryRegionService;
    }


//    public String getCountryName(String ip){
//        String nameCountry=countryRegionClient.getCountryName(ip);
//        String regionCountry=countryRegionClient.getRegionName(ip);
//        System.out.println(regionCountry);
//        CountryRegion countryRegion=countryRegionGetByNameRepository.findByCountry(nameCountry);
//        if (countryRegion==null){
//            System.out.println("null");
//        }
//        return countryRegionClient.getCountryName(ip);
//    }

    public String getRegionName(String ip) {
        String nameCountry = countryRegionClient.getCountryName(ip);
        String regionCountry = countryRegionClient.getRegionName(ip);
        System.out.println(regionCountry);
        CountryRegion countryRegion = null;
        if (countryRegion == null) {
            CountryRegion countryRegion1 = new CountryRegion();
            countryRegion1.setCountry(nameCountry);
            countryRegion1.setRegion(regionCountry);
            countryRegionService.addCountryRegion(countryRegion1);
            return countryRegion1.getRegion();
        } else {
            return countryRegion.getRegion();
        }
    }
}
