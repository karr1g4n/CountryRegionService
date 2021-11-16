package pragmatTech.CountryRegionService.service;

import pragmatTech.CountryRegionService.model.entity.CountryRegion;
import pragmatTech.CountryRegionService.repository.CountryRegionRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CountryRegionService {

    private final CountryRegionRepository countryRegionRepository;

    private GeoNameClientService geoNameClientService;


    private CountryRegionService(CountryRegionRepository countryRegionRepository, GeoNameClientService geoNameClientService) {
        this.countryRegionRepository = countryRegionRepository;
        this.geoNameClientService = geoNameClientService;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion) {
        return countryRegionRepository.save(countryRegion);
    }

    public void addAllCountry() throws IOException {
        List<String> countriesName = geoNameClientService.getAllCountries();
        for (int i = 0; i < countriesName.size(); i++) {
            if (countryRegionRepository.findFirstByCountry(countriesName.get(i)) == null) {
                countryRegionRepository.save(new CountryRegion(countriesName.get(i), "world"));
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
