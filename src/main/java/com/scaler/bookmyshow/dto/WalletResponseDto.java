package com.scaler.bookmyshow.dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.Wallet;
import lombok.Data;

@Data
public class WalletResponseDto {
    private String message;
    private ResponseStatus responseStatus;
    private Wallet wallet;
}
