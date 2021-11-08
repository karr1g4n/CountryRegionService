package com.example.firstspring.model.entity;

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
}
