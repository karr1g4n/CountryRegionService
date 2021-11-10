package com.example.firstspring.service;

import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.repository.CountryRegionGetByNameRepository;
import com.example.firstspring.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkWithCountryRegionService {
    private final CountryRegionRepository countryRegionRepository;
    private CountryRegionGetByNameRepository countryRegionGetByNameRepository;

    @Autowired
    private WorkWithCountryRegionService(CountryRegionRepository countryRegionRepository,CountryRegionGetByNameRepository countryRegionGetByNameRepository){

        this.countryRegionRepository=countryRegionRepository;
        this.countryRegionGetByNameRepository=countryRegionGetByNameRepository;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion){
        return countryRegionRepository.save(countryRegion);
    }

    public CountryRegion updateCountryRegion(CountryRegion countryRegion){
      CountryRegion countryRegion1 = countryRegionGetByNameRepository.findByCountry(countryRegion.getCountry());
      countryRegion1.setRegion(countryRegion.getRegion());
      return   countryRegionRepository.save(countryRegion1);
    }
    
    public List<CountryRegion> getAllCountryRegion(){
        return countryRegionRepository.findAll();
    }

    public void deleteCountryRegion(int id){
        countryRegionRepository.deleteById(id);
    }

}
