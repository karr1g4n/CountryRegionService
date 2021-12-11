package tech.pragmat.countryregionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class CountryRegionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryRegionApplication.class, args);
    }
}
