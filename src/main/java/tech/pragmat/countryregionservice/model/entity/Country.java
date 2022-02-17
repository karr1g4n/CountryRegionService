package tech.pragmat.countryregionservice.model.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressFBWarnings("EI_EXPOSE_REP")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;

}
