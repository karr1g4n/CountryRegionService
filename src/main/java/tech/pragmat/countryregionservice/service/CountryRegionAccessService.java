package tech.pragmat.countryregionservice.service;

import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;
import tech.pragmat.countryregionservice.repository.CountryRegionAccessRepository;

import java.util.List;

@Service
public class CountryRegionAccessService {

    private final CountryRegionAccessRepository countryRegionAccessRepository;

    public CountryRegionAccessService(CountryRegionAccessRepository countryRegionAccessRepository) {
        this.countryRegionAccessRepository = countryRegionAccessRepository;
    }

    public CountryRegionAccess addAccess(CountryRegionAccess countryRegionAccess) {
        return countryRegionAccessRepository.save(countryRegionAccess);
    }

    public CountryRegionAccess getAccessByName(String name) {
        return countryRegionAccessRepository.findFirstByAccess(name);
    }

    public List<CountryRegionAccess> getAllAccess() {
        return countryRegionAccessRepository.findAll();
    }
}
