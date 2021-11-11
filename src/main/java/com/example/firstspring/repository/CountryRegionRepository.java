package com.example.firstspring.repository;

import com.example.firstspring.model.entity.CountryRegion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Name;
import java.util.List;

@Repository
public interface CountryRegionRepository extends CrudRepository<CountryRegion,Integer> {
    List<CountryRegion> findAll();
    CountryRegion findFirstByCountry(String name);
    CountryRegion deleteByCountry(String name);
}
