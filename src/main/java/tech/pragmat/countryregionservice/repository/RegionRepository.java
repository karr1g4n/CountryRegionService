package tech.pragmat.countryregionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.pragmat.countryregionservice.model.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    Region findByRegion(String name);

    void deleteRegionByRegion(String name);
}
