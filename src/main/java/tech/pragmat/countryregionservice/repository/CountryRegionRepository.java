package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;

@Repository
public interface CountryRegionRepository extends JpaRepository<CountryRegion, Integer> {

    CountryRegion findByCountry(String name);

    void deleteFirstCountryRegionByCountry(String name);

   void deleteCountryRegionByCountry(String name);
}
