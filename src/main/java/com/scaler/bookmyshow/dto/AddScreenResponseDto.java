package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.Screen;
import lombok.Data;

@Data
public class AddScreenResponseDto {
    private Screen screen;
    private ResponseStatus responseStatus;
}
