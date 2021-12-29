package tech.pragmat.countryregionservice.web.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.model.entity.CountryRegionAccess;
import tech.pragmat.countryregionservice.service.CountryRegionAccessService;

import java.util.List;

@RestController
@RequestMapping("/access")
public class CountryRegionAccessController {

    private final CountryRegionAccessService countryRegionAccessService;

    @SuppressFBWarnings({ "EI_EXPOSE_REP2" })
    public CountryRegionAccessController(CountryRegionAccessService countryRegionAccessService) {
        this.countryRegionAccessService = countryRegionAccessService;
    }

    @PostMapping
    public CountryRegionAccess addAccess(@RequestBody CountryRegionAccess countryRegionAccess) {
        return countryRegionAccessService.addAccess(countryRegionAccess);
    }

    @GetMapping
    public List<CountryRegionAccess> getAllAccess() {
        return countryRegionAccessService.getAllAccess();
    }
}
