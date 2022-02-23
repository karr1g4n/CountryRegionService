package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.pragmat.countryregionservice.model.entity.NewCountryRegion;

@Repository
public interface NewCountryRegionRepository extends JpaRepository<NewCountryRegion, Integer> {

}
