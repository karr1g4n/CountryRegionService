package com.example.firstspring.model.repository;

import com.example.firstspring.model.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryGetByIdRepository extends CrudRepository<Country,Long> {
    Country findById(int id);
}
