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
        if (validateCountry(name)) {
            Country country = new Country();
            country.setCountry(name);
            return countryRepository.save(country);
        } else {
            return null;
        }
    }

    public void addAllCountry() {

        List<String> countriesName = countryNameClientService.getAllCountries();

        for (String s : countriesName) {
            if (countryRepository.findByCountry(s) == null) {
                countryRepository.save(new Country(0, s));
            }
        }

    }

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public Country getCountryByName(String name) {
        return countryRepository.findByCountry(name);
    }

    public void deleteCountryByName(String name) {
        countryRepository.deleteByCountry(name);
    }

    private boolean validateCountry(String name) {
        if (name != null && name.length() >= 5 && countryRepository.findByCountry(name) == null) {
            for (int i = 0; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
