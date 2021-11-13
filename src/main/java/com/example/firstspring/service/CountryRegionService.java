package com.example.firstspring.service;

import com.example.firstspring.OpenCsv.GetAllCountriesFromGeoName;
import com.example.firstspring.model.entity.CountryRegion;
import com.example.firstspring.repository.CountryRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CountryRegionService {
    private final CountryRegionRepository countryRegionRepository;
    private  final GetAllCountriesFromGeoName getAllCountriesFromGeoName;

    @Autowired
    private CountryRegionService(CountryRegionRepository countryRegionRepository,GetAllCountriesFromGeoName getAllCountriesFromGeoName){
        this.countryRegionRepository=countryRegionRepository;
        this.getAllCountriesFromGeoName=getAllCountriesFromGeoName;
    }

    public CountryRegion addCountryRegion(CountryRegion countryRegion){
        return countryRegionRepository.save(countryRegion);
    }


    public void addAllCountry() throws IOException {
        List<String> countriesName=getAllCountriesFromGeoName.getCountryNamesFromGeoName();
        for (int i=0;i<countriesName.size();i++){
            if (countryRegionRepository.findFirstByCountry(countriesName.get(i))==null){
                countryRegionRepository.save(new CountryRegion(countriesName.get(i),"world"));
            }
        }
    }

    public CountryRegion updateCountryRegion(CountryRegion countryRegion){
        CountryRegion countryRegionExisting=countryRegionRepository.findFirstByCountry(countryRegion.getCountry());
        if (countryRegionExisting !=null){
            countryRegionExisting.setRegion(countryRegionExisting.getRegion());
            countryRegionRepository.save(countryRegionExisting);
            return countryRegionExisting;
        }
         return null;
    }
    
    public List<CountryRegion> getAllCountryRegion(){
        return countryRegionRepository.findAll();
    }

    public CountryRegion getCountryRegionByName(String name){
        return countryRegionRepository.findFirstByCountry(name);
    }

    public CountryRegion deleteCountryRegionByName(String name){
       return countryRegionRepository.deleteByCountry(name);
    }

    public void deleteCountryRegion(int id){
        countryRegionRepository.deleteById(id);
    }






}
