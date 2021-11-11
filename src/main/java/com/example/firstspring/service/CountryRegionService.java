package com.example.firstspring.service;

import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.repository.CountryRegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CountryRegionService {
    private final CountryRegionRepository countryRegionRepository;

    @Autowired
    private CountryRegionService(CountryRegionRepository countryRegionRepository){
        this.countryRegionRepository=countryRegionRepository;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion){
        return countryRegionRepository.save(countryRegion);

    }

    public CountryRegion updateCountryRegion(CountryRegion countryRegion){
      CountryRegion countryRegion1 = countryRegionRepository.findFirstByCountry(countryRegion.getCountry());
      countryRegion1.setRegion(countryRegion.getRegion());
      return   countryRegionRepository.save(countryRegion1);
    }
    
    public List<CountryRegion> getAllCountryRegion(){
        return countryRegionRepository.findAll();
    }

    public CountryRegion getCountryRegionByName(String name){
        return countryRegionRepository.findFirstByCountry(name);
    }

    public CountryRegion deleteCountryRegionByName(String name){
       return countryRegionRepository.deleteByCountry(name);
    }

    public void deleteCountryRegion(int id){
        countryRegionRepository.deleteById(id);
    }






}
