package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class GetAverageMovieResponseDto {
    private ResponseStatus responseStatus;
    private double averageRating;
}
