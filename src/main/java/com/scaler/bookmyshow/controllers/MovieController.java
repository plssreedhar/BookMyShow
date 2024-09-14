package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.MovieRequestDto;
import com.scaler.bookmyshow.dto.MovieResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/createMovie")
    public MovieResponseDto createMovie(@RequestBody MovieRequestDto movieRequestDto) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        try{
            movieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            movieResponseDto.setMovie(movieService.createMovie(movieRequestDto.getName(),movieRequestDto.getDescription(),movieRequestDto.getGenre(),movieRequestDto.getActorIds()));
        }
        catch(Exception e){
            movieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return movieResponseDto;
    }
}
