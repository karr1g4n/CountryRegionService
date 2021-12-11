package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;

@Repository
public interface CountryRegionRepository extends JpaRepository<CountryRegion, Integer> {

    CountryRegion findFirstByCountry(String name);

    CountryRegion deleteByCountry(String name);
}
