package pragmatTech.CountryRegionService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "requestToGeoNameService", url = "https://download.geonames.org/")
public interface GeoNameClient {

    @GetMapping("export/dump/countryInfo.txt")
    String getAllCountryInfo();
}
