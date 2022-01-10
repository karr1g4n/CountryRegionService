package tech.pragmat.countryregionservice.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.dao.impl.CountryRegionAccessDaoImpl;
import tech.pragmat.countryregionservice.dao.impl.CountryRegionDaoImpl;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

import java.util.List;

@RestController
@RequestMapping("/dao")
public class DaoController {

    CountryRegionDaoImpl countryRegionDao = new CountryRegionDaoImpl();
    CountryRegionAccessDaoImpl countryRegionAccessDao = new CountryRegionAccessDaoImpl();

    @PostMapping("/access")
    public void addAccess() throws Exception {

        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");
        countryRegionAccessDao.create(countryRegionAccess);

    }

    @PostMapping("/country")
    public void addCountry() {

        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");
        CountryRegion countryRegion = new CountryRegion(1, "UK", "world", countryRegionAccess, countryRegionAccess);
        countryRegionDao.create(countryRegion);
    }

    @GetMapping()
    public List<CountryRegion> getAll() throws Exception {

        return countryRegionDao.findAll();
    }
}
