package com.example.firstspring.web.controller;

import com.example.firstspring.model.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/country")
public class CountryController {

    private final FirstService firstServer ;

    @Autowired
    private CountryController(FirstService firstServer){
        this.firstServer = firstServer;
    }

    @GetMapping("/get/{id}")
    public String getCountryByIndex(@PathVariable int id){
        return firstServer.GetRegion(id);
    }
}
