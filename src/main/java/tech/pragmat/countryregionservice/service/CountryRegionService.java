package tech.pragmat.countryregionservice.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CountryRegionService {

    private final CountryRegionRepository countryRegionRepository;

    private final GeoNameClientService geoNameClientService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CountryRegionService(CountryRegionRepository countryRegionRepository, GeoNameClientService geoNameClientService) {
        this.countryRegionRepository = countryRegionRepository;
        this.geoNameClientService = geoNameClientService;
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

    public CountryRegion deleteCountryRegionByName(String name) {
        return countryRegionRepository.deleteByCountry(name);
    }

    public void deleteCountryRegion(int id) {
        countryRegionRepository.deleteById(id);
    }

}
