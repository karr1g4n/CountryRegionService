package tech.pragmat.countryregionservice.web.controller;

import org.springframework.web.bind.annotation.*;
import tech.pragmat.countryregionservice.Dao.DaoImpl.CountryRegionAccessDaoImpl;
import tech.pragmat.countryregionservice.Dao.DaoImpl.CountryRegionDaoImpl;
import tech.pragmat.countryregionservice.model.entity.Country;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

import java.util.List;

@RestController
@RequestMapping("/dao")
public class DaoController {

    private final CountryRegionDaoImpl dao = new CountryRegionDaoImpl();

    @PostMapping("/access")
    public void addAccess() {
        CountryRegionAccessDaoImpl countryRegionAccessDao = new CountryRegionAccessDaoImpl();
        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");
        countryRegionAccessDao.create(countryRegionAccess);

    }

    @PostMapping("/country")
    public void addCountry() {
        CountryRegionDaoImpl countryRegionDao = new CountryRegionDaoImpl();
        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");
        CountryRegion countryRegion = new CountryRegion(1, "UK", "world", countryRegionAccess, countryRegionAccess);

        countryRegionDao.create(countryRegion);
    }


    @GetMapping()
    public List<CountryRegion> getAll() {
        CountryRegionDaoImpl countryRegionDao = new CountryRegionDaoImpl();
        return countryRegionDao.findAll();
    }
}
