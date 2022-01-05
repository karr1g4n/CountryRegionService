package tech.pragmat.countryregionservice.model.entity;

import lombok.*;

import javax.persistence.*;


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
