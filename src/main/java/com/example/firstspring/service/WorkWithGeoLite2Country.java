package com.example.firstspring.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Map;

@Service
public class WorkWithGeoLite2Country {
    public String getNameOfCountry(String ip){
        String url = "D:\\GeoLite2\\GeoLite2-Country.mmdb";
        FileInputStream database;
        try {
            database = new FileInputStream(url);
            DatabaseReader reader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = reader.country(ipAddress);
            Country country = response.getCountry();
            return country.getName();
        } catch (Exception e) {
            return "not found";
        }
    }
}
//    public static void main(String[] args) {
//        String ip = "54.73.154.147";
//        System.out.println(getCountryByIP(ip));
//    }


