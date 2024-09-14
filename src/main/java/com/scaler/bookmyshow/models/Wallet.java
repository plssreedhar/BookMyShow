package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Wallet extends BaseModel{
    @OneToOne
    private User user;
    private int balance;
}
