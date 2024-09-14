package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.GetAverageMovieRequestDto;
import com.scaler.bookmyshow.dto.GetAverageMovieResponseDto;
import com.scaler.bookmyshow.dto.RateMovieRequestDto;
import com.scaler.bookmyshow.dto.RateMovieResponseDto;
import com.scaler.bookmyshow.models.Rating;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingsController {

    @Autowired
    RatingsService ratingsService;

    @PostMapping("/rateMovie")
    public RateMovieResponseDto rateMovie(@RequestBody RateMovieRequestDto requestDto){
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try{
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            Rating rating = ratingsService.rateMovie(requestDto.getUserId(),requestDto.getMovieId(),requestDto.getRating());
            responseDto.setRating(rating);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    @PostMapping("/getAverageRating")
    public GetAverageMovieResponseDto getAverageMovieRating(@RequestBody GetAverageMovieRequestDto requestDto){
        GetAverageMovieResponseDto responseDto = new GetAverageMovieResponseDto();
        try{
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            double averageRating = ratingsService.getAverageRating(requestDto.getMovieId());
            responseDto.setAverageRating(averageRating);
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
