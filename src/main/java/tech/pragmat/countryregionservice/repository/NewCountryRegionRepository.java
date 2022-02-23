package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewCountryRegionRepository extends JpaRepository<NewCountryRegionRepository, Integer> {

}
