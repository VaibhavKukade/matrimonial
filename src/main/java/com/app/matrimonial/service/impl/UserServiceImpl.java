package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.UserRepository;
import com.app.matrimonial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public String signUpUser(Users newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return "Username is already taken";
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            return "Email is already registered";
        }

        userRepository.save(newUser);
        return "User registered successfully";
    }
}