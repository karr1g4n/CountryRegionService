package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

@Repository
public interface CountryRegionAccessRepository extends JpaRepository<CountryRegionAccess, Integer> {

    CountryRegionAccess findFirstByAccess(String name);
}
