package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {
    private ResponseStatus responseStatus;
    private Show show;
}
