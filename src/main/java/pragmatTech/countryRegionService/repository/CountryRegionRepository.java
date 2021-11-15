package pragmatTech.countryRegionService.repository;

import pragmatTech.countryRegionService.model.entity.CountryRegion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRegionRepository extends CrudRepository<CountryRegion,Integer> {
    List<CountryRegion> findAll();
    CountryRegion findFirstByCountry(String name);
    CountryRegion deleteByCountry(String name);
}
