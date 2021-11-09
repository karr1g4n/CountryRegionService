package com.example.firstspring.web.controller;

//import com.example.firstspring.feign.CountryRegionClient;
import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.service.WorkWithCountryRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/countryRegion")
public class CountryRegionController {

    private WorkWithCountryRegionService workWithCountryRegionService;



    @Autowired
    private CountryRegionController(WorkWithCountryRegionService workWithCountryRegionService) {
        this.workWithCountryRegionService = workWithCountryRegionService;

    }

    @PostMapping("/addCountryRegion")
    public CountryRegion addCountryRegion(@RequestBody CountryRegion countryRegion){
        return workWithCountryRegionService.addCountryRegion(countryRegion);
    }

    @GetMapping("/getAllCountryRegion")
    public List<CountryRegion> getAllCountryRegion(){
        return workWithCountryRegionService.getAllCountryRegion();
    }

    @PostMapping("/del/{id}")
    public void del(@PathVariable int id){
        workWithCountryRegionService.del(id);
    }


}


