package tech.pragmat.countryregionservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.service.NewCountryRegionService;


@RestController
@RequestMapping("/country/region/")
public class NewCountryRegionController {

    private final NewCountryRegionService countryRegionService;

    @Autowired
    public NewCountryRegionController(NewCountryRegionService countryRegionService) {
        this.countryRegionService = countryRegionService;
    }

    @PostMapping("add/All/Country/Region")
    public void addAllCountryRegion() {
        countryRegionService.addAll();
    }


}
