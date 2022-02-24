package tech.pragmat.countryregionservice.web.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.pragmat.countryregionservice.model.entity.Region;
import tech.pragmat.countryregionservice.service.RegionService;

import java.util.List;

@RestController
@RequestMapping(value = "/region")
public class RegionController {

    private final RegionService regionService;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public Region addRegionByName(@RequestParam String region) {
        return regionService.addRegion(region);
    }

    @PostMapping("/add/All/Regions")
    public void addAllRegion() {
        regionService.addAllRegion();
    }

    @GetMapping("/get/All/Regions")
    public List<Region> getAllRegion() {
        return regionService.getAllRegion();
    }

    @GetMapping
    public Region getRegionByName(@RequestParam String region) {
        return regionService.getRegionByName(region);
    }

    @DeleteMapping
    public void deleteRegionByName(@RequestParam String region) {
        regionService.deleteRegionByName(region);
    }

}
