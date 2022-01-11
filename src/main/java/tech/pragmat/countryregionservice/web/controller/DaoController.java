package tech.pragmat.countryregionservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.dao.impl.CountryRegionAccessDaoImpl;
import tech.pragmat.countryregionservice.dao.impl.CountryRegionDaoImpl;
import tech.pragmat.countryregionservice.model.entity.CountryRegion;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dao")
public class DaoController {

    CountryRegionDaoImpl countryRegionDao = new CountryRegionDaoImpl();
    CountryRegionAccessDaoImpl countryRegionAccessDao = new CountryRegionAccessDaoImpl();

    @PostMapping("/access")
    public void addAccess() {

        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");
        countryRegionAccessDao.create(countryRegionAccess);

    }

    @DeleteMapping()
    public boolean del() {
        return countryRegionDao.deleteByName("UK");
    }

    @PostMapping("/country")
    public void addCountry() {

        CountryRegionAccess countryRegionAccess = new CountryRegionAccess(1, "access");
        CountryRegion countryRegion = new CountryRegion(1, "UK", "world", countryRegionAccess, countryRegionAccess);
        countryRegionDao.create(countryRegion);
    }

    @GetMapping()
    public List<CountryRegion> getAll() {

        return countryRegionDao.findAll();
    }
}
