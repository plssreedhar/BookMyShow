package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.City;
import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class AddCityResponseDto {
    private City city;
    private ResponseStatus responseStatus;
}
