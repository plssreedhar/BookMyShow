package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Booking;
import com.scaler.bookmyshow.models.BookingStatus;
import com.scaler.bookmyshow.models.Payment;
import com.scaler.bookmyshow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    EmailService emailService;

    public void generateTickets(Payment payment) {
        Booking booking = payment.getBooking();
        booking.setBookingStatus(BookingStatus.SUCCESSFUL);
        User user = booking.getUser();
        try {
            emailService.sendBookingToUser(user, booking);
        }
        catch (Exception e){
            System.out.println("Ticket Generation failed. Sending refund for amount deducted! Exception is "+e.getMessage());
        }
    }
}
