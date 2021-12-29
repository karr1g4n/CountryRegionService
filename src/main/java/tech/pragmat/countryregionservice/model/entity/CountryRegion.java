package tech.pragmat.countryregionservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "country_region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;

    private String region;

    @ManyToOne
    @JoinColumn(name = "country_access_id")
    private CountryRegionAccess countryAccess;

    @ManyToOne
    @JoinColumn(name = "region_access_id")
    private CountryRegionAccess regionAccess;
}
