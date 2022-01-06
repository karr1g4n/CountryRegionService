package tech.pragmat.countryregionservice.model.entity;

public class Country {
    private int id;
    private String country;
    private String region;

    public Country(int id, String country, String region) {
        this.id = id;
        this.country = country;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
