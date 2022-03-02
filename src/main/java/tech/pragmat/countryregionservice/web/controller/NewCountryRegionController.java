package tech.pragmat.countryregionservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.pragmat.countryregionservice.model.entity.NewCountryRegion;
import tech.pragmat.countryregionservice.service.NewCountryRegionService;

import java.util.List;


@RestController
@RequestMapping("/country/region/")
public class NewCountryRegionController {

    private final NewCountryRegionService countryRegionService;

    @Autowired
    public NewCountryRegionController(NewCountryRegionService countryRegionService) {
        this.countryRegionService = countryRegionService;
    }

    @PostMapping()
    public NewCountryRegion addNewCountryRegion(@RequestParam String countryName, String regionName) {
        return countryRegionService.addNewCountryRegion(countryName, regionName);
    }

    @PostMapping("add/All/Countries/Regions")
    public void addAllNewCountryRegion() {
        countryRegionService.addAll();
    }

    @GetMapping("get/All/Countries/Regions")
    public List<NewCountryRegion> getAllNewCountryRegion() {
        return countryRegionService.getAllNewCountryRegion();
    }

    @GetMapping()
    public NewCountryRegion getCountryRegion(@RequestParam String countryName) {
        return countryRegionService.getNewCountryRegionByName(countryName);
    }

    @DeleteMapping()
    @Transactional
    public void DeleteNewCountryRegion(@RequestParam String countryName) {
        countryRegionService.deleteNewCountryRegion(countryName);
    }

}
