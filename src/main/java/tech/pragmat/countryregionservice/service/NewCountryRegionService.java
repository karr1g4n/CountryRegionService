package tech.pragmat.countryregionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.NewCountryRegion;
import tech.pragmat.countryregionservice.model.entity.Region;
import tech.pragmat.countryregionservice.repository.CountryRepository;
import tech.pragmat.countryregionservice.repository.NewCountryRegionRepository;
import tech.pragmat.countryregionservice.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewCountryRegionService {

    private final NewCountryRegionNameClientService newCountryRegionNameClientService;

    private final CountryRepository countryRepository;

    private final RegionRepository regionRepository;

    private final NewCountryRegionRepository newCountryRegionRepository;

    @Autowired
    public NewCountryRegionService(NewCountryRegionNameClientService newCountryRegionNameClientService, CountryRepository countryRepository, RegionRepository regionRepository, NewCountryRegionRepository newCountryRegionRepository) {
        this.newCountryRegionNameClientService = newCountryRegionNameClientService;
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
        this.newCountryRegionRepository = newCountryRegionRepository;
    }

    public void addAll() {
        List<List<String>> countryRegions = newCountryRegionNameClientService.getAllCountries();
        List<Country> countries = new ArrayList<>();
        List<Region> regions = new ArrayList<>();
        for (int i = 0; i < countryRegions.get(0).size(); i++) {
            countries.add(countryRepository.findByCountry(countryRegions.get(0).get(i)));
        }
        for (int i = 0; i < countryRegions.get(1).size(); i++) {
            System.out.println(regionRepository.findByRegion(countryRegions.get(1).get(i)));
            regions.add(regionRepository.findByRegion(countryRegions.get(1).get(i)));
        }
        if (!countries.contains(null) || !regions.contains(null)) {
            for (int i = 0; i < countryRegions.get(0).size(); i++) {
                NewCountryRegion newCountryRegion = new NewCountryRegion();
                newCountryRegion.setCountry(countries.get(i));
                newCountryRegion.setRegion(regions.get(i));
                newCountryRegionRepository.save(newCountryRegion);
            }
        }

    }

    public List<NewCountryRegion> getAllNewCountryRegion() {
        return newCountryRegionRepository.findAll();
    }


}
