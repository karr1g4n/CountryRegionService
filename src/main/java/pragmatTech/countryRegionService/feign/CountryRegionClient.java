package pragmatTech.countryRegionService.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vjkasdfha", url = "http://localhost:9090/Geo2LiteCountry")
public interface CountryRegionClient {

    @GetMapping("/getCountryByIp")
    String getCountryName(@RequestParam String ip);
}
