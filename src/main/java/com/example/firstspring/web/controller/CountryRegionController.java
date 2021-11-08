package com.example.firstspring.web.controller;

import com.example.firstspring.model.entity.CountryRegion;
//import com.example.firstspring.service.FirstService;
import com.example.firstspring.service.WorkWithCountryRegionService;
import com.example.firstspring.service.WorkWithGeoLite2Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/countryRegion")
public class CountryRegionController {

    private WorkWithCountryRegionService workWithCountryRegionService;

    private RegionController regionController;

    @Autowired
    private CountryRegionController(WorkWithCountryRegionService workWithCountryRegionService,RegionController regionController) {
        this.workWithCountryRegionService = workWithCountryRegionService;
        this.regionController=regionController;
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

//    @PostMapping("/getRegionByIp")
//    public String getRegionByIp(@RequestParam String ip){
//
//    }
}


