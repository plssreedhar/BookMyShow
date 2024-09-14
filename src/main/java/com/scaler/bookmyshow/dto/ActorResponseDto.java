package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.Actor;
import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Data;

@Data
public class ActorResponseDto {
    private Actor actor;
    private ResponseStatus responseStatus;
}
