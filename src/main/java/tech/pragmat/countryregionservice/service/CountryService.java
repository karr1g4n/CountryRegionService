package tech.pragmat.countryregionservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.repository.CountryRepository;

import java.util.List;

@Service
@Slf4j
public class CountryService {

    private final CountryRepository countryRepository;

    private final CountryNameClientService countryNameClientService;

    public CountryService(CountryRepository countryRepository, CountryNameClientService countryNameClientService) {
        this.countryRepository = countryRepository;
        this.countryNameClientService = countryNameClientService;
    }

    public Country addCountry(String name) {
        if (name != null) {
            return countryRepository.save(new Country(1, name));
        } else {
            return null;
        }
    }

    public void addAllCountry() {
        try {
            List<String> countriesName = countryNameClientService.getAllCountries();

            for (String s : countriesName) {
                if (countryRepository.findByCountry(s) == null) {
                    countryRepository.save(new Country(0, s));
                }
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public Country getCountryByName(String name) {
        return countryRepository.findByCountry(name);
    }

    public void deleteCountryByName(String name) {
        countryRepository.deleteCountryByCountry(name);
    }

}
