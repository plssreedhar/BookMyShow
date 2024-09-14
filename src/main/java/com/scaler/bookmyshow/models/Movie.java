package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Movie extends BaseModel{
    private String name;
    @ManyToMany
    private List<Actor> actors;
    private String description;
    private String year;
    private String genre;
}
