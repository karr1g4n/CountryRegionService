package com.example.firstspring.web.controller;

import com.example.firstspring.service.WorkWithCountryRegionService;
import org.springframework.stereotype.Controller;

@Controller
public class RegionController {
    private WorkWithCountryRegionService workWithCountryRegionService;

    public RegionController(WorkWithCountryRegionService workWithCountryRegionService){
        this.workWithCountryRegionService=workWithCountryRegionService;
    }
    public String getNameCountryBuIp(String ip){
       return workWithCountryRegionService.getRegionByIp(ip);
    }
}
