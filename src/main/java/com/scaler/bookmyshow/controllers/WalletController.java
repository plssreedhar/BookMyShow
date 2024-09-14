package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dto.WalletRechargeRequestDto;
import com.scaler.bookmyshow.dto.WalletResponseDto;
import com.scaler.bookmyshow.models.ResponseStatus;
import com.scaler.bookmyshow.models.Wallet;
import com.scaler.bookmyshow.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/rechargeWallet")
    public WalletResponseDto rechargeWallet(@RequestBody WalletRechargeRequestDto requestDto){
        WalletResponseDto responseDto = new WalletResponseDto();
        try{
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            Wallet wallet = walletService.rechargeWallet(requestDto.getUserId(),requestDto.getAmount());
            responseDto.setWallet(wallet);
            responseDto.setMessage("Wallet Recharge successful");
        }
        catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }
}
