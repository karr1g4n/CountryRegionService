package tech.pragmat.countryregionservice.web.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.service.CountryRegionService;

@Slf4j
@RestController
@RequestMapping("/connectGeo2LiteService")
public class ConnectionWithGeoLite2CountryController {

    private final CountryRegionService countryRegionService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public ConnectionWithGeoLite2CountryController(CountryRegionService countryRegionService) {
        this.countryRegionService = countryRegionService;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip) {

        log.info("try get region by ip");
        return countryRegionService.getCountryRegion(ip);
    }
}

