package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.models.UserType;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User getUser(String email){
        Optional<User> userOptional  = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User with given email does not exist!");
        }
        return userOptional.get();
    }

    @Override
    public User signupUser(String name, String email, String password, String userType) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new Exception("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUserType(UserType.valueOf(userType));
        return userRepository.save(user);
    }

    @Override
    public boolean login(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new Exception("user not registered");
        }
        User user = userOptional.get();
        return bCryptPasswordEncoder.matches(password,user.getPassword());
    }
}
