package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.exceptions.MovieNotFoundException;
import com.scaler.bookmyshow.exceptions.UserNotFoundException;
import com.scaler.bookmyshow.models.Movie;
import com.scaler.bookmyshow.models.Rating;
import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.repositories.MovieRepository;
import com.scaler.bookmyshow.repositories.RatingRepository;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingsService{

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Rating rateMovie(int userId, int movieId, int rating) throws UserNotFoundException, MovieNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        Optional<Rating> ratingOptional = ratingRepository.findByUserAndMovie(userOptional.get(),movieOptional.get());
        Rating newrating;
        if(ratingOptional.isEmpty()){
            newrating = new Rating();
            newrating.setMovie(movieOptional.get());
            newrating.setUser(userOptional.get());
            newrating.setRating(rating);
        }
        else{
            newrating = ratingOptional.get();
            newrating.setRating(rating);
        }
        return ratingRepository.save(newrating);
    }

    @Override
    public double getAverageRating(int movieId) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        List<Rating> ratingList = ratingRepository.findByMovieId(movieId);
        double averageRating = 0.0;
        for(Rating rating:ratingList){
            averageRating+=rating.getRating();
        }
        averageRating/=ratingList.size();
        return averageRating;
    }
}
