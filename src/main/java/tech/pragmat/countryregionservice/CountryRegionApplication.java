package tech.pragmat.countryregionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
public class CountryRegionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryRegionApplication.class, args);
    }
}
