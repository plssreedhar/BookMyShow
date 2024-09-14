package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class City extends BaseModel{
    private String name;
    private String country;
//    private List<Movie> movieList;
//    private List<Theatre> theatreList;
}
