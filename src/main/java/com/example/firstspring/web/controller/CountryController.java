package com.example.firstspring.web.controller;

import com.example.firstspring.layer.LayerBetweenLayers;
import com.example.firstspring.model.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final  LayerBetweenLayers serviceBetweenWebAndBack ;

    @Autowired
    private CountryController(LayerBetweenLayers serviceBetweenWebAndBack){
        this.serviceBetweenWebAndBack=serviceBetweenWebAndBack;
    }

    @PostMapping("/add")
    public Country addCountry(@RequestBody Country country){
        return serviceBetweenWebAndBack.addCountry(country);
    }

    @GetMapping("/getAllCountry")
    public List<Country> geAllCountry(){
        return serviceBetweenWebAndBack.getAllCountry();
    }

    @GetMapping("/get/{id}")
    public String getCountryByIndex(@PathVariable Long id){
        return serviceBetweenWebAndBack.getCountryById(id);
    }
}
