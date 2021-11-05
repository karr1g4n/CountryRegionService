package com.example.firstspring.model.service;

import com.example.firstspring.model.entity.Country;
import com.example.firstspring.model.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final  CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository){
        this.countryRepository=countryRepository;
    }

    public Country addCountry(Country country){
        return countryRepository.save(country);
    }

    public List<Country> getAllCountry(){
        List<Country> countryList=countryRepository.findAll();
        return countryList;
    }

    public String getCountryById(int id){
        Country country=countryRepository.findBy(id);
        if (country!=null){
            return country.getCountry();
        }else {
            return null;
        }


    }
}
