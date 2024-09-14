package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.Theatre;
import lombok.Data;

@Data
public class AddTheatreResponseDto {
    private Theatre theatre;
    private ResponseStatus responseStatus;
}
