package com.scaler.bookmyshow.repositories;

import com.scaler.bookmyshow.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer> {
    Optional<Wallet> findByUserId(int userId);
}
