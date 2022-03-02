package tech.pragmat.countryregionservice.web.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.service.CountryService;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class  CountryController {

    private final CountryService countryService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public Country addCountryByName(@RequestParam String name) {
        return countryService.addCountry(name);
    }

    @PostMapping("/add/All/Countries")
    public void addAllCountries() {
        countryService.addAllCountry();
    }

    @GetMapping("get/All/Countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountry();
    }

    @GetMapping
    public Country getCountryByName(@RequestParam String name) {
        return countryService.getCountryByName(name);
    }

    @DeleteMapping
    @Transactional
    public void deleteCountryByName(@RequestParam String name) {
        countryService.deleteCountryByName(name);
    }

}
