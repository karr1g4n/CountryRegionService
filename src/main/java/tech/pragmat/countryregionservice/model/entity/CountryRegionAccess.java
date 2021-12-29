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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryRegionAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = true, unique = true)
    private int id;

    @Column(name = "access", unique = true)
    private String access;
}
