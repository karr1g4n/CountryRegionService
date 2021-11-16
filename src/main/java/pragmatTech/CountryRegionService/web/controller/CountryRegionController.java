package pragmatTech.CountryRegionService.web.controller;

import pragmatTech.CountryRegionService.model.entity.CountryRegion;
import pragmatTech.CountryRegionService.service.CountryRegionService;
import pragmatTech.CountryRegionService.service.GeoNameClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/countryRegion")
public class CountryRegionController {

    private CountryRegionService countryRegionService;
    private GeoNameClientService geoNameClientService;

    @Autowired
    private CountryRegionController(CountryRegionService countryRegionService, GeoNameClientService geoNameClientService) {
        this.countryRegionService = countryRegionService;
        this.geoNameClientService = geoNameClientService;
    }

    @PostMapping()
    public CountryRegion addCountryRegion(@RequestBody CountryRegion countryRegion) {
        log.info("User try add country and region in DB");
        return countryRegionService.addCountryRegion(countryRegion);
    }

    @PutMapping()
    public CountryRegion updateCountryRegion(@RequestBody CountryRegion countryRegion) {
        log.info("User try update country and region in DB");
        return countryRegionService.updateCountryRegion(countryRegion);
    }

    @PostMapping("/addAll")
    private void addAllCountryRegion() throws IOException {
        log.info("User try add all country and region in DB");
        countryRegionService.addAllCountry();
    }

    @GetMapping("/getAll")
    public List<CountryRegion> getAllCountryRegion() {
        log.info("request to get all countries and region");
        return countryRegionService.getAllCountryRegion();
    }

    @GetMapping("/getByName")
    public CountryRegion getCountryByName(@RequestParam String name) {
        log.info("request to get country and region by name of country");
        return countryRegionService.getCountryRegionByName(name);
    }

    @DeleteMapping()
    public void delCountryByName(@RequestParam String name) {
        log.info("request to delete country and region by name of country");
        countryRegionService.deleteCountryRegionByName(name);
    }

}


