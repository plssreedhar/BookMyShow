package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
public class AddScreenRequestDto {

    private String name;
    private ScreenStatus status;
    private List<Feature> features;
    private int theatreId;
    private List<Pair<SeatType,String>> seats;
}
