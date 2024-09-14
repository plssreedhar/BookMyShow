package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
    private Date startTime;
    private Date endTime;
    @ElementCollection
    private List<Feature> features;
    @OneToMany
    private List<ShowSeat> showSeats;
    @OneToMany
    private List<SeatTypeShow> showSeatTypes;
    @Enumerated(value = EnumType.STRING)
    private ShowState showState;
}
