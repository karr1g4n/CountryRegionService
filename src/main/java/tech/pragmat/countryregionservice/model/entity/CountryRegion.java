package tech.pragmat.countryregionservice.model.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressFBWarnings("EI_EXPOSE_REP")
@Entity(name = "country_region")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String country;

    private String region;

    @ManyToOne
    private CountryRegionAccess countryAccess;

    @ManyToOne
    private CountryRegionAccess regionAccess;

}
