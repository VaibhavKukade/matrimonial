package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.UserRepository;
import com.app.matrimonial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
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

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean authenticateUser(Users loginUser) {
        Users userFromDB = userRepository.findByUsername(loginUser.getUsername());

        // Check if the user exists and the password matches
        return userFromDB != null && userFromDB.getPassword().equals(loginUser.getPassword());
    }
}