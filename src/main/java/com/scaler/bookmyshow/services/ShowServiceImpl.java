package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.exceptions.*;
import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowServiceImpl implements ShowService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private SeatTypeShowRepository seatTypeShowRepository;
    @Autowired
    private SeatsRepository seatsRepository;

    @Override
    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime, List<Pair<SeatType, Double>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, UserNotFoundException, UnAuthorizedAccessException {
        Show show = new Show();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        Optional<Screen> screenOptional = screenRepository.findById(screenId);
        if(screenOptional.isEmpty()){
            throw new ScreenNotFoundException("Screen not found");
        }
        if(startTime.getTime()>endTime.getTime() || System.currentTimeMillis()>startTime.getTime()){
            throw new UnAuthorizedAccessException("Date wrong");
        }
        User user = userOptional.get();
        Movie movie = movieOptional.get();
        Screen screen = screenOptional.get();

        Set<Feature> featureSet = new HashSet<>(screen.getFeatures());

        for(Feature feature:features){
            if(!featureSet.contains(feature)){
                throw new FeatureNotSupportedByScreen("Screen not suitable");
            }
        }

        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnAuthorizedAccessException("User is not admin");
        }

        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setFeatures(features);

        showRepository.save(show);

        List<Seat> seats = seatsRepository.findByScreen(screen);
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat:seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeat.setShow(show);
            showSeats.add(showSeat);
        }
        showSeatRepository.saveAll(showSeats);

        List<SeatTypeShow> showSeatTypes = new ArrayList<>();
        for(Pair<SeatType, Double> pair:pricingConfig){
            SeatType seatType = pair.getFirst();
            double price = pair.getSecond();
            SeatTypeShow seatTypeShow = new SeatTypeShow();
            seatTypeShow.setShow(show);
            seatTypeShow.setSeatType(seatType);
            seatTypeShow.setPrice(price);
            showSeatTypes.add(seatTypeShow);

        }
        seatTypeShowRepository.saveAll(showSeatTypes);

        return show;
    }
}
