package tech.pragmat.countryregionservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import tech.pragmat.countryregionservice.feign.GeoNameClient;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CountryNameClientService {

    private final GeoNameClient geoNameClient;

    public CountryNameClientService(GeoNameClient geoNameClient) {
        this.geoNameClient = geoNameClient;
    }

    public List<String> getAllCountries() {
        List<String> countryNames = new ArrayList<>();
        try {
            StringReader stringReader = new StringReader(geoNameClient.getAllCountryInfo());
            Iterable<CSVRecord> names = CSVFormat.RFC4180.withDelimiter('\t').parse(stringReader);

            for (CSVRecord name : names) {
                String countryName = "";
                if (name.size() > 4 && !"#".equals(name.get(0)) && !name.get(4).equals("Country")) {

                    countryName = name.get(4);
                }
                if (!countryName.isEmpty()) {
                    countryNames.add(countryName);
                }
            }
            return countryNames;
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return countryNames;

    }
}
