package tech.pragmat.countryregionservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "country_region")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;

    private String region;

    @ManyToOne
    private CountryRegionAccess countryAccess;

    @ManyToOne
    private CountryRegionAccess regionAccess;

}
