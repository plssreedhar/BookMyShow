package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Screen extends BaseModel{
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private ScreenStatus status;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Feature> features;
    @ManyToOne
    private Theatre theatre;
    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Seat> seats;
}
