package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Actor;
import com.scaler.bookmyshow.models.Movie;
import com.scaler.bookmyshow.repositories.ActorRepository;
import com.scaler.bookmyshow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;


    public Movie createMovie(String name, String description, String genre, List<Integer> actorIds){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setGenre(genre);
        List<Actor> actors = new ArrayList<>();
        for(Integer actorId : actorIds){
            Optional<Actor> actor = actorRepository.findById(actorId);
            actor.ifPresent(actors::add);
        }
        movie.setActors(actors);
        return movieRepository.save(movie);
    }
}
