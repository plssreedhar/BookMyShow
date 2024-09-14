package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculator;
    private final PaymentService paymentService;
    private EmailService emailService;

    private static final int maxSeat = 10;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
                          ShowRepository showRepository, ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculator, PaymentService paymentService, EmailService emailService){
        this.bookingRepository=bookingRepository;
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
        this.priceCalculator=priceCalculator;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    @Transactional(isolation=Isolation.SERIALIZABLE)
    public Booking bookTicket(Integer userId, Integer showId, List<Integer> showSeatIds) {

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User with userId:"+userId+" does not exist!");
        }
        User user = userOptional.get();
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("Show with showId:"+showId+" does not exist!");
        }
        Show show = showOptional.get();
        if(showSeatIds.size()>maxSeat){
            throw new RuntimeException("Maximum possible seats exceeded!");
        }
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        boolean seatsAvailable = showSeats.stream().allMatch(x->x.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE));
        if(!seatsAvailable){
            throw new RuntimeException("Some seats are not available. please try again!");
        }
        for(ShowSeat showSeat:showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        showSeats = showSeatRepository.saveAll(showSeats);
        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setUser(user);
        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        int amount = priceCalculator.calculatePrice(showSeats,show);
        booking.setAmount(amount);
        booking.setPayments(paymentService.payForBookingViaWallet(booking));
        booking = bookingRepository.save(booking);
        return booking;
    }
}
