package tech.pragmat.countryregionservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "CountryRegionAccess")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryRegionAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String access;
}
