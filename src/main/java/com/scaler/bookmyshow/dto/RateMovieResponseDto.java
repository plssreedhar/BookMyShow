package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.Rating;
import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class RateMovieResponseDto {
    private ResponseStatus responseStatus;
    private Rating rating;
}
