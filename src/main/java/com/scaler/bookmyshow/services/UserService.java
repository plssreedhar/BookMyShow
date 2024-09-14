package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     User signupUser(String name, String email, String password, String userType) throws Exception;

     boolean login(String email, String password) throws Exception;

}
