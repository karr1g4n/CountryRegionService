package com.example.firstspring.model.service;


import com.example.firstspring.model.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService {
    private final SecondService secondServer;

    @Autowired
    public FirstService(SecondService secondServer){
        this.secondServer=secondServer;
    }
    public String GetRegion(int id){
      Country country = secondServer.getNameCountry(id);
      return country.getRegion();

    }
}
