package com.example.firstspring.web.controller;

import com.example.firstspring.service.CountyRegionFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/connectGeo2LiteService")
public class ConnectionWithGeoLite2CountryController {
    private CountyRegionFeignService countyRegionFeignService;

    @Autowired
    public ConnectionWithGeoLite2CountryController(CountyRegionFeignService countyRegionFeignService){
        this.countyRegionFeignService = countyRegionFeignService;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip){
        log.info("try get region by ip");
       return countyRegionFeignService.getRegionName(ip);

    }
}

