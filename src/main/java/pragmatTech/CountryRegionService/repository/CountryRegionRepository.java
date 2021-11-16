package pragmatTech.CountryRegionService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pragmatTech.CountryRegionService.model.entity.CountryRegion;

import java.util.List;

@Repository
public interface CountryRegionRepository extends CrudRepository<CountryRegion, Integer> {

    List<CountryRegion> findAll();

    CountryRegion findFirstByCountry(String name);

    CountryRegion deleteByCountry(String name);
}
