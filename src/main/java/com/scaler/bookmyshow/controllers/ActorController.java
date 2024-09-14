package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.ActorRequestDto;
import com.scaler.bookmyshow.dto.ActorResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @PostMapping("/createActor")
    public ActorResponseDto createActor(@RequestBody ActorRequestDto actorRequestDto) {
        ActorResponseDto actorResponseDto = new ActorResponseDto();
        try{
            actorResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            actorResponseDto.setActor(actorService.createActor(actorRequestDto.getName(),actorRequestDto.getAge(),actorRequestDto.getDescription()));
        }
        catch(Exception e){
            actorResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return actorResponseDto;
    }
}
