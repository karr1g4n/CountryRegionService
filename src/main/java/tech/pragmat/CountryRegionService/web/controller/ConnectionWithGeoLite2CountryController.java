package tech.pragmat.CountryRegionService.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.CountryRegionService.service.CountyRegionFeignService;

@Slf4j
@RestController
@RequestMapping("/connectGeo2LiteService")
public class ConnectionWithGeoLite2CountryController {

    private final CountyRegionFeignService countyRegionFeignService;

    public ConnectionWithGeoLite2CountryController(CountyRegionFeignService countyRegionFeignService) {
        this.countyRegionFeignService = countyRegionFeignService;
    }

    @GetMapping("/getRegionByIp")
    public String get(@RequestParam String ip) {
        log.info("try get region by ip");
        return countyRegionFeignService.getRegionName(ip);

    }
}

