package tech.pragmat.countryregionservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CountryRegionAccess")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryRegionAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(unique = true)
    private String access;

}
