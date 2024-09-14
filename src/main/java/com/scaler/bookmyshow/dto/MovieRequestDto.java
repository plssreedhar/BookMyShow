package com.scaler.bookmyshow.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieRequestDto {
    private String name;
    private String description;
    private String genre;
    private List<Integer> actorIds;
}
