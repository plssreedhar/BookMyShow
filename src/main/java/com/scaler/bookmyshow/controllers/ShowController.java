package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.CreateShowRequestDTO;
import com.scaler.bookmyshow.dto.CreateShowResponseDTO;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/createShow")
    public CreateShowResponseDTO createShow(@RequestBody CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try{
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            Show show = showService.createShow(requestDTO.getUserId(), requestDTO.getMovieId(), requestDTO.getScreenId(), requestDTO.getStartTime(), requestDTO.getEndTime(), requestDTO.getPricingConfig(), requestDTO.getFeatures());
            responseDTO.setShow(show);
        }
        catch (Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
