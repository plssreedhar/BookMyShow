package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Booking;
import com.scaler.bookmyshow.models.Payment;
import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.models.Wallet;
import com.scaler.bookmyshow.repositories.UserRepository;
import com.scaler.bookmyshow.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    public Wallet rechargeWallet(int userId, int amount) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User does not exist!");
        }
        User user = userOptional.get();
        Wallet wallet;
        Optional<Wallet> walletOptional = walletRepository.findByUserId(userId);
        if(walletOptional.isEmpty()){
            wallet = new Wallet();
            wallet.setBalance(0);
            wallet.setUser(user);
        }
        else {
            wallet = walletOptional.get();
        }
        wallet.setBalance(wallet.getBalance()+amount);
        return walletRepository.save(wallet);
    }

    public void payForBooking(Booking booking) {
        User user = booking.getUser();
        Optional<Wallet> walletOptional = walletRepository.findByUserId(user.getId());
        if(walletOptional.isEmpty()){
            throw new RuntimeException("Wallet does not exist for user with id: "+user.getId());
        }
        Wallet wallet = walletOptional.get();
        if(wallet.getBalance()<booking.getAmount()){
            throw new RuntimeException("Insufficient Wallet Balacne!");
        }
        wallet.setBalance(wallet.getBalance()- booking.getAmount());
        walletRepository.save(wallet);
    }
}
