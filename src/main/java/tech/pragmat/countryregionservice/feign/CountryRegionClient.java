package tech.pragmat.countryregionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${cr.name}")
public interface CountryRegionClient {

    @GetMapping("/Geo2LiteCountry/getCountryByIp")
    String getCountryName(@RequestParam String ip);
}
