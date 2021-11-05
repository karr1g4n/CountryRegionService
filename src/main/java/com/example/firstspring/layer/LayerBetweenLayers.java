package com.example.firstspring.layer;

import com.example.firstspring.model.entity.Country;
import com.example.firstspring.model.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LayerBetweenLayers {
    private final CountryService countryService ;

    @Autowired
    private LayerBetweenLayers(CountryService countryService){
        this.countryService=countryService;
    }

    public Country addCountry(Country country){
      return countryService.addCountry(country);
    }

    public List<Country> getAllCountry(){
        return countryService.getAllCountry();
    }

    public String getCountryById(int id){
        return countryService.getCountryById(id);
    }
}
