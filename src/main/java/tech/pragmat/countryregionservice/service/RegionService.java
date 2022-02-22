package tech.pragmat.countryregionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.pragmat.countryregionservice.model.entity.Region;
import tech.pragmat.countryregionservice.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region addRegion(String name) {
        if (validateRegion(name)) {
            Region region = new Region();
            region.setRegion(name);
            return regionRepository.save(region);
        } else {
            return null;
        }
    }

    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }

    public Region getRegionByName(String name) {
        return regionRepository.findByRegion(name);
    }

    public void deleteRegionByName(String name) {
        regionRepository.deleteRegionByRegion(name);
    }

    private boolean validateRegion(String name) {
        if (name != null && name.length() >= 5 && regionRepository.findByRegion(name) == null) {
            for (int i = 0; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
