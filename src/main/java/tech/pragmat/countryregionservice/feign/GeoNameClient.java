package tech.pragmat.countryregionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "requestToGeoNameService", url = "${geo.url}")
public interface GeoNameClient {

    @GetMapping("export/dump/countryInfo.txt")
    String getAllCountryInfo();
}
