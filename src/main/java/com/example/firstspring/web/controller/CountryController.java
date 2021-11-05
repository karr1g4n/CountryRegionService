package com.example.firstspring.web.controller;

import com.example.firstspring.model.entity.Country;
import com.example.firstspring.model.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {


    private CountryService countryService ;

    @Autowired
    private CountryController(CountryService countryService){
        this.countryService=countryService;
    }

    @PostMapping("/add")
    public Country addCountry(@RequestBody Country country){
        return countryService.addCountry(country);
    }

    @GetMapping("/getAllCountry")
    public List<Country> geAllCountry(){
        return countryService.getAllCountry();
    }

    @GetMapping("/get/{id}")
    public String getCountryByIndex(@PathVariable Long id){

        return countryService.getCountryByIndex(id);
    }
}
