package com.example.firstspring.web.controller;

import com.example.firstspring.service.CountyFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/connectGeo2LiteService")
public class ConnectionWithGeoLite2CountryController {
    private CountyFeignService  countyFeignService;

    @Autowired
    public ConnectionWithGeoLite2CountryController(CountyFeignService  countyFeignService){
        this.countyFeignService=countyFeignService;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip){
        log.info("try get region by ip");
       return countyFeignService.getRegionName(ip);

    }
}

