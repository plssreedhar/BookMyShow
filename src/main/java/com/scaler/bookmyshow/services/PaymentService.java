package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final WalletService walletService;

    private final TicketService ticketService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, WalletService walletService, TicketService ticketService) {
        this.paymentRepository = paymentRepository;
        this.walletService = walletService;
        this.ticketService = ticketService;
    }

    // if wallet payment is failed, then try other payments later.
    // you can return the booking object now so that it can be shown in summary page
    // when user gets option to choose payment mode you can add rest of payments user made to same booking.

    public List<Payment> payForBookingViaWallet(Booking booking){
        List<Payment> payments = new ArrayList<>();
        Payment walletPayment = payFromWallet(booking);
        triggerTicketGenerationOnPayment(walletPayment);
        payments.add(walletPayment);
        return payments;
    }

    public void payForBookingThroughOtherPayment(Booking booking,PaymentMode paymentMode){
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentDate(new Date());
        paymentViaOtherPayment(payment,booking.getAmount(),paymentMode);
        booking.getPayments().add(payment);
    }

    private void paymentViaOtherPayment(Payment payment,int amount,PaymentMode paymentMode) {
        payment.setPaymentMode(paymentMode);
        try{
            //this method denotes that payment through 3rd party service.
            //pay();
            payment.setAmount(amount);
            payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        }
        catch (RuntimeException e){
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }
        paymentRepository.save(payment);
    }

    public Payment payFromWallet(Booking booking) {
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentDate(new Date());
        try {
            walletService.payForBooking(booking);
            payment.setAmount(booking.getAmount());
            payment.setPaymentMode(PaymentMode.WALLET);
            payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);

        } catch (RuntimeException e) {
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }
        return paymentRepository.save(payment);
    }

    public void triggerTicketGenerationOnPayment(Payment payment){
        if(payment.getPaymentStatus().equals(PaymentStatus.SUCCESSFUL)){
            ticketService.generateTickets(payment);
        }
    }


}
