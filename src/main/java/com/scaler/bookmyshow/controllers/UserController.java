package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.*;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public SignupUserResponseDTO signupUser(@RequestBody SignupUserRequestDTO requestDTO){
        SignupUserResponseDTO responseDTO = new SignupUserResponseDTO();
        try{
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            User user = userService.signupUser(requestDTO.getName(),requestDTO.getEmail(),requestDTO.getPassword(),requestDTO.getUserType());
            responseDTO.setUserId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
        }
        catch (Exception e){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try{
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setLoggedIn(userService.login(requestDto.getEmail(),requestDto.getPassword()));
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
