package com.example.firstspring.web.controller;

import com.example.firstspring.service.CountyFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connectionController")
public class ConnectionWithGeoLite2CountryController {
    private CountyFeignService  countyFeignService;

    @Autowired
    public ConnectionWithGeoLite2CountryController(CountyFeignService  countyFeignService){
        this.countyFeignService=countyFeignService;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip){
       return countyFeignService.getCountryName(ip);

    }
}

