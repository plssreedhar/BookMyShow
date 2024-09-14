package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class BookingResponseDto {
    private int bookingId;
    private ResponseStatus responseStatus;
    private String message;
    private int amount;
}
