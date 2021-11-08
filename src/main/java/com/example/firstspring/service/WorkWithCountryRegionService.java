package com.example.firstspring.service;

import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkWithCountryRegionService {
    private CountryRegionRepository countryRegionRepository;

    private  WorkWithGeoLite2Country workWithGeoLite2Country;
//
//    private CountryRegionGetByNameRepository countryRegionGetByNameRepository;

    @Autowired
    private WorkWithCountryRegionService(CountryRegionRepository countryRegionRepository,WorkWithGeoLite2Country workWithGeoLite2Country){
        this.workWithGeoLite2Country=workWithGeoLite2Country;
        this.countryRegionRepository=countryRegionRepository;
//        this.countryRegionGetByNameRepository=countryRegionGetByNameRepository;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion){
        return countryRegionRepository.save(countryRegion);
    }
    
    public List<CountryRegion> getAllCountryRegion(){
        return countryRegionRepository.findAll();
    }

    public void del(int id){
        countryRegionRepository.deleteById(id);
    }

    public String getRegionByIp(String ip){

      String nameOfCountry= workWithGeoLite2Country.getNameOfCountry(ip);
        return nameOfCountry;
    }
}
