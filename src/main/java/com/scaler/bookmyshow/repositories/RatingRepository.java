package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.models.Movie;
import com.scaler.bookmyshow.models.Rating;
import com.scaler.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    List<Rating> findByMovieId(int movieId);

    Optional<Rating> findByUserAndMovie(User user, Movie movie);
}
