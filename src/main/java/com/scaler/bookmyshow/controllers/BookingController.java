package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.BookingRequestDto;
import com.scaler.bookmyshow.dto.BookingResponseDto;
import com.scaler.bookmyshow.models.Booking;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PostMapping("/bookTicket")
    public BookingResponseDto bookTicket(@RequestBody BookingRequestDto requestDto){
        BookingResponseDto responseDto = new BookingResponseDto();
        try{
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            Booking booking = bookingService.bookTicket(requestDto.getUserId(),requestDto.getShowId(),requestDto.getShowSeatIds());
            responseDto.setBookingId(booking.getId());
            responseDto.setAmount(booking.getAmount());
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
