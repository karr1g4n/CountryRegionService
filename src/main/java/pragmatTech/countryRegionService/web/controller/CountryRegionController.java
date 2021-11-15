package pragmatTech.countryRegionService.web.controller;


import pragmatTech.countryRegionService.model.entity.CountryRegion;
import pragmatTech.countryRegionService.service.CountryRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/countryRegion")
public class CountryRegionController {

    private CountryRegionService countryRegionService;

    @Autowired
    private CountryRegionController(CountryRegionService countryRegionService) {
        this.countryRegionService = countryRegionService;
    }

    @PostMapping()
    public CountryRegion addCountryRegion(@RequestBody CountryRegion countryRegion){
        log.info("User try add country and region in DB");
        return countryRegionService.addCountryRegion(countryRegion);
    }

    @PutMapping()
    public CountryRegion updateCountryRegion(@RequestBody CountryRegion countryRegion){
        log.info("User try update country and region in DB");
        return countryRegionService.updateCountryRegion(countryRegion);
    }

    @PostMapping("/addAll")
    private void addAllCountryRegion() throws IOException {
        log.info("User try add all country and region in DB");
        countryRegionService.addAllCountry();
    }

    @GetMapping("/getAll")
    public List<CountryRegion> getAllCountryRegion(){
        log.info("request to get all countries and region");
        return countryRegionService.getAllCountryRegion();
    }

    @GetMapping("/getByName")
    public CountryRegion getCountryByName(@RequestParam String name){
        log.info("request to get country and region by name of country");
        return countryRegionService.getCountryRegionByName(name);
    }

    @DeleteMapping ("/del")
    public void delCountryByName(@RequestParam String name){
        log.info("request to delete country and region by name of country");
        countryRegionService.deleteCountryRegionByName(name);
    }


}


