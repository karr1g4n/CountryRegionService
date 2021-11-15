package pragmatTech.countryRegionService.model.entity;
import lombok.*;
import javax.persistence.*;



@Data
@Entity(name = "country_region")
public class CountryRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;

    private String region;

    public CountryRegion(String countyName, String region) {
        this.country=countyName;
        this.region=region;
    }
    public CountryRegion() {

    }
}
