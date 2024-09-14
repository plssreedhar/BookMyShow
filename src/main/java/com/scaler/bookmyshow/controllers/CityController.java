package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.AddCityRequestDto;
import com.scaler.bookmyshow.dto.AddCityResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/createCity")
    public AddCityResponseDto addCity(@RequestBody AddCityRequestDto addCityRequestDto){
        AddCityResponseDto addCityResponseDto = new AddCityResponseDto();
        try{
            addCityResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            addCityResponseDto.setCity(cityService.addCity(addCityRequestDto.getCityName(),addCityRequestDto.getCountry()));
        }
        catch (Exception e){
            addCityResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return addCityResponseDto;
    }
}
