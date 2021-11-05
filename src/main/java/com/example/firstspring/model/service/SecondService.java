package com.example.firstspring.model.service;

import com.example.firstspring.model.entity.Country;
import com.example.firstspring.model.repository.CountryGetByIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondService {

    private final CountryGetByIdRepository countryGetByIdRepository;
    @Autowired
    public SecondService(CountryGetByIdRepository countryGetByIdRepository){
        this.countryGetByIdRepository=countryGetByIdRepository;
    }
    public Country getNameCountry(int id){
        return countryGetByIdRepository.findById(id);
    }
}
