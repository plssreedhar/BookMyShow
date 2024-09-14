package com.scaler.bookmyshow.services;


import com.itextpdf.text.DocumentException;
import com.scaler.bookmyshow.models.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PdfService pdfService;

    public void sendBookingToUser(User user, Booking booking) throws MessagingException, DocumentException, FileNotFoundException {
        String email = user.getEmail();
        Movie movie = booking.getShowSeats().get(0).getShow().getMovie();
        List<ShowSeat> showSeatList = booking.getShowSeats();
        String subject = "Tickets Booked!!";
        StringBuilder body = new StringBuilder();
        body.append("Your tickets for ").append(movie.getName()).append(" has been booked.\n Booked seats are ");
        for (Iterator<ShowSeat> it = showSeatList.iterator(); it.hasNext(); ) {
            ShowSeat showSeat = it.next();
            body.append(showSeat.getSeat().getName());
            if(it.hasNext()){
                body.append(", ");
            }
        }
        body.append("Tickets booked at ").append(booking.getBookedAt()).append("\n");
        body.append("Total number of seats ").append(booking.getShowSeats().size()).append("\n");
        body.append("Total amount ").append(booking.getAmount()).append("\n");
//        String fileName = "tickets.pdf";
        sendSimpleMessage(email,subject,body.toString());
    }

    public void sendEmail(String to, String subject, String body,String fileName) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment(fileName,new File(fileName));
        javaMailSender.send(msg);
    }


    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
