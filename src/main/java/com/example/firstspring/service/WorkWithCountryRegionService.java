package com.example.firstspring.service;

import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkWithCountryRegionService {
    private CountryRegionRepository countryRegionRepository;


    @Autowired
    private WorkWithCountryRegionService(CountryRegionRepository countryRegionRepository){
        this.countryRegionRepository=countryRegionRepository;

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

}
