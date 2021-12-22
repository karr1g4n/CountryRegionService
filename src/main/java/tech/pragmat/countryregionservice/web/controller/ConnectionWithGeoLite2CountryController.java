package tech.pragmat.countryregionservice.web.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tech.pragmat.countryregionservice.ribbon.RibbonConfiguration;
import tech.pragmat.countryregionservice.service.CountryRegionService;

@Slf4j
@RestController
@RequestMapping("/connectGeo2LiteService")
@RibbonClient(name = "geo-a-lite", configuration = RibbonConfiguration.class)
public class ConnectionWithGeoLite2CountryController {

    private final CountryRegionService countryRegionService;
    private final RestTemplate restTemplate;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public ConnectionWithGeoLite2CountryController(CountryRegionService countryRegionService, RestTemplate restTemplate) {
        this.countryRegionService = countryRegionService;
        this.restTemplate=restTemplate;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip) {

        log.info("try get region by ip");
        return restTemplate.getForObject("http://geo-lite/Geo2LiteCountry/getCountryByIp?ip=" + ip, String.class);

//        return countryRegionService.getCountryRegion(ip);
    }
}

