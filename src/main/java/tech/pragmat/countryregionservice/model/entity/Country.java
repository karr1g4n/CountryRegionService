package tech.pragmat.countryregionservice.model.entity;

public class Country {
    private int id;
    private String country;
    private String region;
    private CountryRegionAccess countryRegionAccess;

    public Country(int id, String country, String region, CountryRegionAccess countryRegionAccess) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.countryRegionAccess = countryRegionAccess;
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

    public CountryRegionAccess getCountryRegionAccess() {
        return countryRegionAccess;
    }

    public void setCountryRegionAccess(CountryRegionAccess countryRegionAccess) {
        this.countryRegionAccess = countryRegionAccess;
    }
}
