package tech.pragmat.countryregionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "requestToCountryRegionService", url = "${cr.url}")
public interface CountryRegionClient {

    @GetMapping("/getCountryByIp")
    String getCountryName(@RequestParam String ip);
}
