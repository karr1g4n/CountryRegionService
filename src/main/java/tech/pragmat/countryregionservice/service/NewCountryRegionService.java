package tech.pragmat.countryregionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.repository.NewCountryRegionRepository;

@Service
public class NewCountryRegionService {

    private final NewCountryRegionRepository newCountryRegionRepository;

    @Autowired
    public NewCountryRegionService(NewCountryRegionRepository newCountryRegionRepository) {
        this.newCountryRegionRepository = newCountryRegionRepository;
    }
}
