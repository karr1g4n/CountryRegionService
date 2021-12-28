package tech.pragmat.countryregionservice.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "country_region")
@NoArgsConstructor
public class CountryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;

    private String region;

    @ManyToOne
    @JoinColumn(name = "country_access_id")
    private CountryRegionAccess countryAccess;

    public CountryRegion(String countyName, String region,CountryRegionAccess countryAccess) {
        this.country = countyName;
        this.region = region;
        this.countryAccess=countryAccess;
    }
}
