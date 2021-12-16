package tech.pragmat.countryregionservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tech.pragmat.countryregionservice.service.CountyRegionFeignService;

@Slf4j
@RestController
@RequestMapping("/connectGeo2LiteService")
public class ConnectionWithGeoLite2CountryController {

    @Value("${cr.url}")
    String a;

    private final CountyRegionFeignService countyRegionFeignService;

    private final RestTemplate restTemplate;

    public ConnectionWithGeoLite2CountryController(CountyRegionFeignService countyRegionFeignService, RestTemplate restTemplate) {
        this.countyRegionFeignService = countyRegionFeignService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip) {

        log.info("try get region by ip");
//        return countyRegionFeignService.getRegionName(ip);
        return restTemplate.getForObject(a + ip, String.class);

    }
}

