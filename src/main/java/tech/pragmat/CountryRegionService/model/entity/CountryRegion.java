package tech.pragmat.CountryRegionService.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "country_region")
public class CountryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;

    private String region;

    public CountryRegion(String countyName, String region) {
        this.country = countyName;
        this.region = region;
    }

    public CountryRegion() {

    }
}
