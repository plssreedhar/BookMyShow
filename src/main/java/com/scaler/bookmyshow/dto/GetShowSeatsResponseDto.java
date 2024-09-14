package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.ShowSeat;
import lombok.Data;

import java.util.List;

@Data
public class GetShowSeatsResponseDto {
    private ResponseStatus responseStatus;
    List<ShowSeat> seatList;
}
