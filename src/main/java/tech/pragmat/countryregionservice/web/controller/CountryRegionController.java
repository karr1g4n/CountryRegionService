package tech.pragmat.countryregionservice.web.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;
import tech.pragmat.countryregionservice.service.CountryRegionService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/countryRegion")
public class CountryRegionController {

    private final CountryRegionService countryRegionService;

    @Autowired
    private CountryRegionRepository countryRegionRepository;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CountryRegionController(CountryRegionService countryRegionService) {
        this.countryRegionService = countryRegionService;
    }

    @PostMapping()
    public CountryRegion addCountryRegion(@RequestBody CountryRegion countryRegion) {
        log.info("User try add country and region in DB");
        return countryRegionService.addCountryRegion(countryRegion);
    }

    @PutMapping("/updateCountryRegion")
    public CountryRegion updateCountryRegion(@RequestBody CountryRegion countryRegion) {
        log.info("User try update country and region in DB");
        return countryRegionService.updateCountryRegion(countryRegion);
    }

    @PutMapping("/updateCountry/access")
    public CountryRegion updateCountryAccess(@RequestParam String countryName, String accessName) {
        log.info("try update country access");
        return countryRegionService.updateCountryAccess(countryName, accessName);
    }

    @PutMapping("/updateRegion/access")
    public CountryRegion updateRegionAccess(@RequestParam String countryName, String accessName) {
        log.info("try update region access");
        return countryRegionService.updateRegionAccess(countryName, accessName);
    }

    @PostMapping("/addAll")
    public void addAllCountryRegion() throws IOException {
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
    @Transactional
    public void delCountryByName(@RequestParam String name) {
        log.info("request to delete country and region by name of country");
        countryRegionRepository.deleteCountryRegionByCountry(name);
    }
}



