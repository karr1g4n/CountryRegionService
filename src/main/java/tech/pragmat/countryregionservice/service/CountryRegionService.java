package tech.pragmat.countryregionservice.service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${access.name}")
    private String accessName;

    @Value("${default.region.name}")
    private String defaultRegion;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public CountryRegionService(CountryRegionRepository countryRegionRepository, GeoNameClientService geoNameClientService, CountryRegionClient countryRegionClient,
                                CountryRegionAccessService countryRegionAccessService) {
        this.countryRegionRepository = countryRegionRepository;
        this.geoNameClientService = geoNameClientService;
        this.countryRegionClient = countryRegionClient;
        this.countryRegionAccessService = countryRegionAccessService;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion) {
        CountryRegionAccess countryAccess = countryRegionAccessService.getAccessByName(accessName);
        CountryRegionAccess regionAccess = countryRegionAccessService.getAccessByName(accessName);
        if (countryRegion != null && countryAccess != null && regionAccess != null) {
            return countryRegionRepository.save(new CountryRegion(0, countryRegion.getCountry(), countryRegion.getRegion(), countryAccess, regionAccess));
        } else {
            return null;
        }
    }

    public void addAllCountry() throws IOException {

        List<String> countriesName = geoNameClientService.getAllCountries();
        CountryRegionAccess countryAccess = countryRegionAccessService.getAccessByName(accessName);
        CountryRegionAccess regionAccess = countryRegionAccessService.getAccessByName(accessName);
        for (String s : countriesName) {
            if (countryRegionRepository.findByCountry(s) == null && countryAccess != null && regionAccess != null) {
                countryRegionRepository.save(new CountryRegion(0, s, defaultRegion, countryAccess, regionAccess));
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

    public CountryRegion updateCountryAccess(String countryName, String accessName) {
        CountryRegion countryRegion = countryRegionRepository.findByCountry(countryName);
        CountryRegionAccess regionAccess = countryRegionAccessService.getAccessByName(accessName);

        if (countryRegion != null && regionAccess != null) {
            countryRegion.setCountryAccess(regionAccess);
            return countryRegionRepository.save(countryRegion);
        } else {
            return null;
        }
    }

    public CountryRegion updateRegionAccess(String countryName, String accessName) {
        CountryRegion countryRegion = countryRegionRepository.findByCountry(countryName);
        CountryRegionAccess regionAccess = countryRegionAccessService.getAccessByName(accessName);

        if (countryRegion != null && regionAccess != null) {
            countryRegion.setRegionAccess(regionAccess);
            return countryRegionRepository.save(countryRegion);
        } else {
            return null;
        }
    }

    public List<CountryRegion> getAllCountryRegion() {
        return countryRegionRepository.findAll();
    }

    public CountryRegion getCountryRegionByName(String name) {

        return countryRegionRepository.findByCountry(name);
    }

    public String getCountryRegion(String ip) {
        String countryName = countryRegionClient.getCountryName(ip);
        CountryRegion countryRegion = getCountryRegionByName(countryName);
        if (countryRegion.getCountryAccess().getAccess().equals(accessName) && countryRegion.getRegionAccess().getAccess().equals(accessName)) {
            return getCountryRegionByName(countryName).getRegion();
        } else {
            return "Country " + countryRegion.getCountry() + " is " + countryRegion.getCountryAccess().getAccess() + " Region is " + countryRegion.getRegionAccess().getAccess();
        }

    }

    public void deleteCountryRegionByName(String name) {
         countryRegionRepository.deleteFirstCountryRegionByCountry(name);
    }

}
