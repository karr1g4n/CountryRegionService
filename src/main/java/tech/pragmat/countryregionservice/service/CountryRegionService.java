package tech.pragmat.countryregionservice.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;
import tech.pragmat.countryregionservice.ribbon.RibbonConfiguration;

import java.io.IOException;
import java.util.List;

@Service
//@RibbonClient(name = "geo-a-lite", configuration = RibbonConfiguration.class)
public class CountryRegionService {

    private final CountryRegionRepository countryRegionRepository;

    private final GeoNameClientService geoNameClientService;

//    @Value("${cr.url}")
//    String geoLiteURL;

//    private final RestTemplate restTemplate;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CountryRegionService(CountryRegionRepository countryRegionRepository, GeoNameClientService geoNameClientService) {
        this.countryRegionRepository = countryRegionRepository;
        this.geoNameClientService = geoNameClientService;
//        this.restTemplate = restTemplate;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion) {
        return countryRegionRepository.save(countryRegion);
    }

    public void addAllCountry() throws IOException {
        List<String> countriesName = geoNameClientService.getAllCountries();
        for (String s : countriesName) {
            if (countryRegionRepository.findFirstByCountry(s) == null) {
                countryRegionRepository.save(new CountryRegion(s, "world"));
            }
        }
    }

    public CountryRegion updateCountryRegion(CountryRegion countryRegion) {
        CountryRegion countryRegionExisting = countryRegionRepository.findFirstByCountry(countryRegion.getCountry());
        if (countryRegionExisting != null) {
            countryRegionExisting.setRegion(countryRegionExisting.getRegion());
            countryRegionRepository.save(countryRegionExisting);
            return countryRegionExisting;
        }
        return null;
    }

    public List<CountryRegion> getAllCountryRegion() {
        return countryRegionRepository.findAll();
    }

    public CountryRegion getCountryRegionByName(String name) {

        return countryRegionRepository.findFirstByCountry(name);
    }

    public String getCountryRegion(String ip) {
//        String countryName = restTemplate.getForObject("http://geo-lite/Geo2LiteCountry/getCountryByIp" + "?ip=" + ip, String.class);
//        return getCountryRegionByName(countryName).getRegion();
        return  null;
    }

    public CountryRegion deleteCountryRegionByName(String name) {
        return countryRegionRepository.deleteByCountry(name);
    }

    public void deleteCountryRegion(int id) {
        countryRegionRepository.deleteById(id);
    }

}
