package com.scaler.bookmyshow.dto;


import lombok.Data;

@Data
public class SignupUserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String userType;
}
