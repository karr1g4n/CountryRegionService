package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.pragmat.countryregionservice.model.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
}
