package com.scaler.bookmyshow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat extends BaseModel{
    private String name;
    private int rowVal;
    private int colVal;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    @JsonIgnore
    private Screen screen;
}
