package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.GetShowSeatsResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowSeatController {

    @Autowired
    ShowSeatService showSeatService;

    @GetMapping("/getShowSeats")
    public GetShowSeatsResponseDto getShowSeats(@RequestParam int showId) {
        GetShowSeatsResponseDto getShowSeatsResponseDto = new GetShowSeatsResponseDto();
        try{
            getShowSeatsResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            getShowSeatsResponseDto.setSeatList(showSeatService.getSeatsForShow(showId));
        }
        catch(Exception e){
            getShowSeatsResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return getShowSeatsResponseDto;
    }

}
