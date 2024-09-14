package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.City;
import com.scaler.bookmyshow.models.Theatre;
import com.scaler.bookmyshow.repositories.CityRepository;
import com.scaler.bookmyshow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    CityRepository cityRepository;

    public Theatre createTheatre(String name, String address, String city){
        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setAddress(address);
        Optional<City> cityOptional = cityRepository.findByName(city);
        if(cityOptional.isEmpty()){
            throw new RuntimeException("City is invalid!");
        }
        theatre.setCity(cityOptional.get());
        return theatreRepository.save(theatre);
    }
}
