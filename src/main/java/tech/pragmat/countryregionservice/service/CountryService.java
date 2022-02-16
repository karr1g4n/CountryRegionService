package tech.pragmat.countryregionservice.service;

import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.repository.CountryRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    private final GeoNameClientService geoNameClientService;

    public CountryService(CountryRepository countryRepository, GeoNameClientService geoNameClientService) {
        this.countryRepository = countryRepository;
        this.geoNameClientService = geoNameClientService;
    }

    public Country addCountry(String name) {
        if (name != null) {
            return countryRepository.save(new Country(0, name));
        } else {
            return null;
        }
    }

    public void addAllCountry() throws IOException {

        List<String> countriesName = geoNameClientService.getAllCountries();

        for (String s : countriesName) {
            if (countryRepository.findByCountry(s) == null) {
                countryRepository.save(new Country(0, s));
            }
        }
    }
}
