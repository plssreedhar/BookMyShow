package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.City;
import com.scaler.bookmyshow.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City addCity(String cityName, String country){
        City city= new City();
        city.setName(cityName);
        city.setCountry(country);
        return cityRepository.save(city);
    }
}
