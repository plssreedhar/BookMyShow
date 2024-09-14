package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "actors")
public class Actor extends BaseModel{
    private String name;
    private int age;
    private String description;
    @ManyToMany
    private List<Movie> movies;
}
