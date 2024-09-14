package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.AddScreenRequestDto;
import com.scaler.bookmyshow.dto.AddScreenResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenController {

    @Autowired
    ScreenService screenService;

    @PostMapping("/addScreen")
    public AddScreenResponseDto addScreen(@RequestBody AddScreenRequestDto screenRequestDto){
        AddScreenResponseDto addScreenResponseDto = new AddScreenResponseDto();
        try{
            addScreenResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            addScreenResponseDto.setScreen(screenService.createScreen(screenRequestDto.getName(),screenRequestDto.getStatus(),screenRequestDto.getFeatures(),screenRequestDto.getSeats()));
        }
        catch (Exception e){
            addScreenResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return addScreenResponseDto;
    }
}
