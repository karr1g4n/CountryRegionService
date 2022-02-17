package tech.pragmat.countryregionservice.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.feign.CountryRegionClient;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CountryRegionService {

    private final CountryRegionRepository countryRegionRepository;

    private final CountryNameClientService countryNameClientService;

    private final CountryRegionClient countryRegionClient;

    @Value("${default.region.name}")
    private String defaultRegion;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CountryRegionService(CountryRegionRepository countryRegionRepository, CountryNameClientService countryNameClientService, CountryRegionClient countryRegionClient) {
        this.countryRegionRepository = countryRegionRepository;
        this.countryNameClientService = countryNameClientService;
        this.countryRegionClient = countryRegionClient;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion) {

        if (countryRegion != null) {
            return countryRegionRepository.save(new CountryRegion(0, countryRegion.getCountry(), countryRegion.getRegion()));
        } else {
            return null;
        }
    }

    public void addAllCountry() throws IOException {

        List<String> countriesName = countryNameClientService.getAllCountries();

        for (String s : countriesName) {
            if (countryRegionRepository.findByCountry(s) == null) {
                countryRegionRepository.save(new CountryRegion(0, s, defaultRegion));
            }
        }
    }

    public CountryRegion updateCountryRegion(CountryRegion countryRegion) {
        CountryRegion countryRegionExisting = countryRegionRepository.findByCountry(countryRegion.getCountry());
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

        return countryRegionRepository.findByCountry(name);
    }

    public String getCountryRegion(String ip) {
        String countryName = countryRegionClient.getCountryName(ip);
        return countryRegionRepository.findByCountry(countryName).getRegion();
    }

    public void deleteCountryRegionByName(String name) {
        countryRegionRepository.deleteFirstCountryRegionByCountry(name);
    }

}
