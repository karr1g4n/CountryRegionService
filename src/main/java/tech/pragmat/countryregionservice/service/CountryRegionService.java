package tech.pragmat.countryregionservice.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.feign.CountryRegionClient;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;
import tech.pragmat.countryregionservice.repository.CountryRegionRepository;

import java.io.IOException;
import java.util.List;

@Service
public class CountryRegionService {

    private final CountryRegionRepository countryRegionRepository;

    private final GeoNameClientService geoNameClientService;

    private final CountryRegionClient countryRegionClient;

    private final CountryRegionAccessService countryRegionAccessService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CountryRegionService(CountryRegionRepository countryRegionRepository, GeoNameClientService geoNameClientService, CountryRegionClient countryRegionClient,
                                CountryRegionAccessService countryRegionAccessService) {
        this.countryRegionRepository = countryRegionRepository;
        this.geoNameClientService = geoNameClientService;
        this.countryRegionClient = countryRegionClient;
        this.countryRegionAccessService = countryRegionAccessService;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion) {
        CountryRegionAccess countryRegionAccess = countryRegionAccessService.getAccessByName("access");
        if (countryRegion != null && countryRegionAccess != null) {
            return countryRegionRepository.save(new CountryRegion(countryRegion.getCountry(), countryRegion.getRegion(), countryRegionAccess));
        } else {
            return null;
        }
    }

    public void addAllCountry() throws IOException {
        List<String> countriesName = geoNameClientService.getAllCountries();
        CountryRegionAccess countryRegionAccess = countryRegionAccessService.getAccessByName("access");
        for (String s : countriesName) {
            if (countryRegionRepository.findFirstByCountry(s) == null && countryRegionAccess != null) {
                countryRegionRepository.save(new CountryRegion(s, "world", countryRegionAccess));
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

    public CountryRegion updateCountryRegionAccess(String countryName, String accessName) {
        CountryRegion countryRegion = countryRegionRepository.findFirstByCountry(countryName);
        CountryRegionAccess countryRegionAccess = countryRegionAccessService.getAccessByName(accessName);

        if (countryRegion != null && countryRegionAccess != null) {
            countryRegion.setCountryAccess(countryRegionAccess);
            return countryRegionRepository.save(countryRegion);
        }
        else {
            return null;
        }
    }

    public List<CountryRegion> getAllCountryRegion() {
        return countryRegionRepository.findAll();
    }

    public CountryRegion getCountryRegionByName(String name) {

        return countryRegionRepository.findFirstByCountry(name);
    }

    public String getCountryRegion(String ip) {
        String countryName = countryRegionClient.getCountryName(ip);
        return getCountryRegionByName(countryName).getRegion();
    }

    public CountryRegion deleteCountryRegionByName(String name) {
        return countryRegionRepository.deleteByCountry(name);
    }

    public void deleteCountryRegion(int id) {
        countryRegionRepository.deleteById(id);
    }

}
