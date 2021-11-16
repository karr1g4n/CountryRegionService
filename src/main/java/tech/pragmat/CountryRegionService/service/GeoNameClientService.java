package tech.pragmat.CountryRegionService.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import tech.pragmat.CountryRegionService.feign.GeoNameClient;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeoNameClientService {

    private GeoNameClient geoNameClient;
    private List<String> countryNames = new ArrayList<>();

    public GeoNameClientService(GeoNameClient geoNameClient) {
        this.geoNameClient = geoNameClient;
    }

    public List<String> getAllCountries() throws IOException {
        StringReader stringReader = new StringReader(geoNameClient.getAllCountryInfo());
        Iterable<CSVRecord> names = CSVFormat.RFC4180.withDelimiter('\t').parse(stringReader);

        for (CSVRecord name : names) {
            String countryName = "";
            if (name.size() > 4 && !"#".equals(name.get(0))) {

                countryName = name.get(4);
            }
            if (!countryName.isEmpty()) {
                countryNames.add(countryName);
            }
        }
        return countryNames;
    }
}
