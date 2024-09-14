package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class LoginResponseDto {
    private boolean loggedIn;
    private ResponseStatus responseStatus;
}
