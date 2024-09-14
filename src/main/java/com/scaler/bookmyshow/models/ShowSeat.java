package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Seat seat;
    @ManyToOne
    private Show show;
    @Enumerated(value = EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
}
