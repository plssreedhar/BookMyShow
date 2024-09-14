package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.AddTheatreRequestDto;
import com.scaler.bookmyshow.dto.AddTheatreResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/createTheatre")
    public AddTheatreResponseDto addTheatre(@RequestBody AddTheatreRequestDto addTheatreRequestDto){
        AddTheatreResponseDto addTheatreResponseDto = new AddTheatreResponseDto();
        try{
            addTheatreResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            addTheatreResponseDto.setTheatre(theatreService.createTheatre(addTheatreRequestDto.getName(),addTheatreRequestDto.getAddress(),addTheatreRequestDto.getCity()));
        }
        catch (Exception e){
            addTheatreResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return addTheatreResponseDto;
    }
}
