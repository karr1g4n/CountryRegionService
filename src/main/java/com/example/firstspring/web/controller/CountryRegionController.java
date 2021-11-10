package com.example.firstspring.web.controller;

//import com.example.firstspring.feign.CountryRegionClient;
import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.service.CountryRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/countryRegion")
public class CountryRegionController {

    private CountryRegionService countryRegionService;

    @Autowired
    private CountryRegionController(CountryRegionService countryRegionService) {
        this.countryRegionService = countryRegionService;

    }

    @PostMapping("/addCountryRegion")
    public CountryRegion addCountryRegion(@RequestBody CountryRegion countryRegion){
        return countryRegionService.addCountryRegion(countryRegion);
    }

    @GetMapping("/updateCountryRegion")
    public CountryRegion updateCountryRegion(@RequestBody CountryRegion countryRegion){
        return countryRegionService.updateCountryRegion(countryRegion);
    }

    @GetMapping("/getAllCountryRegion")
    public List<CountryRegion> getAllCountryRegion(){
        return countryRegionService.getAllCountryRegion();
    }

    @PostMapping("/del/{id}")
    public void del(@PathVariable int id){
        countryRegionService.deleteCountryRegion(id);
    }


}


