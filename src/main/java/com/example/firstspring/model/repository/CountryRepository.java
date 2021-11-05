package com.example.firstspring.model.repository;

import com.example.firstspring.model.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {
    List<Country> findAll();
    Country findBy(int id);
}
