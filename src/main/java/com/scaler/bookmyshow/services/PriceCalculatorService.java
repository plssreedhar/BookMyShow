package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.SeatType;
import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.models.ShowSeat;
import com.scaler.bookmyshow.models.SeatTypeShow;
import com.scaler.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceCalculatorService {

    @Autowired
    ShowSeatTypeRepository showSeatTypeRepository;

    public int calculatePrice(List<ShowSeat> showSeats, Show show) {
        int amount=0;

        List<SeatTypeShow> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        Map<SeatType, SeatTypeShow> showSeatTypeMap = showSeatTypes.stream().collect(Collectors.toMap(SeatTypeShow::getSeatType, x->x));
        for(ShowSeat showSeat:showSeats){
            amount+=showSeatTypeMap.get(showSeat.getSeat().getSeatType()).getPrice();
        }
        return amount;
    }
}
