package com.example.firstspring.OpenCsv;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCountriesFromGeoName {
    String fileName = "D:\\countryInfo\\countryinfo.txt";
    Reader fileReader;
    List<String> countryNames = new ArrayList<>();
    public GetAllCountriesFromGeoName() throws FileNotFoundException {
        fileReader = new FileReader(fileName);
    }

    public List<String> getCountryNamesFromGeoName() throws IOException {
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withDelimiter('\t').parse(fileReader);
        for (CSVRecord record : records) {
            String countryName = record.get(4);
            if (!countryName.isEmpty()) {
                countryNames.add(countryName);
            }
        }
        return countryNames;
    }
}

