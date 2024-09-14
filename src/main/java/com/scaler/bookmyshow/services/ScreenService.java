package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.ScreenRepository;
import com.scaler.bookmyshow.repositories.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private SeatsRepository seatsRepository;

    public Screen createScreen(String name, ScreenStatus status, List<Feature> features, List<Pair<SeatType, String>> seats){
        Screen screen = new Screen();
        screen.setName(name);
        screen.setStatus(status);
        screen.setFeatures(features);
        screenRepository.save(screen);
        List<Seat> seatList = new ArrayList<>();
        for(Pair<SeatType,String> pair:seats){
            SeatType seatType = pair.getFirst();
            String seatName = pair.getSecond();
            Seat seat = new Seat();
            seat.setName(seatName);
            seat.setSeatType(seatType);
            seat.setScreen(screen);
            seatList.add(seat);
            seatsRepository.save(seat);
        }
        screen.setSeats(seatList);
        return screenRepository.save(screen);
    }
}
