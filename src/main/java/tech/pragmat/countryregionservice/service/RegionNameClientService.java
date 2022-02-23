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
public class RegionNameClientService {

    private    GeoNameClient geoNameClient;

    public RegionNameClientService(GeoNameClient geoNameClient) {
        this.geoNameClient = geoNameClient;
    }

    public  List<String> getAllRegions() {
        List<String> regionNames = new ArrayList<>();
        try {
            StringReader stringReader = new StringReader(geoNameClient.getAllCountryInfo());
            Iterable<CSVRecord> names = CSVFormat.RFC4180.withDelimiter('\t').parse(stringReader);

            for (CSVRecord name : names) {
                String countryName = "";
                if (name.size() > 8 && !"#".equals(name.get(0)) && !name.get(8).equals("Continent")) {

                    countryName = name.get(8);
                    System.out.println(name.get(8));
                }
//                if (!countryName.isEmpty()) {
//                    countryNames.add(countryName);
//                }
            }
//            return countryNames;
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return null;

    }

}
