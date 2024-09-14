package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.Movie;
import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class MovieResponseDto {
    private Movie movie;
    private ResponseStatus responseStatus;
}
