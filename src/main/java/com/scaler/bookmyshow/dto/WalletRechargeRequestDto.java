package com.scaler.bookmyshow.dto;

import lombok.Data;

@Data
public class WalletRechargeRequestDto {
    private int userId;
    private int amount;
}
