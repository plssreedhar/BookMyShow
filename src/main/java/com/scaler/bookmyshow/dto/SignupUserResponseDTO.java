package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class SignupUserResponseDTO {
    private ResponseStatus responseStatus;
    private String name;
    private String email;
    private int userId;
}
