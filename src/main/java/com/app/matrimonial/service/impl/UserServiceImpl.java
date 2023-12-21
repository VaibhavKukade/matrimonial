package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.UserRepository;
import com.app.matrimonial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public Users updateUser(Users newUser) {
        Users user=userRepository.save(newUser);
        return  user;
    }


    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> findUnapprovedUsers(){
        return userRepository.findUnapprovedUsers();
    }

    @Override
    public Users findUserByUsername(String username){
        try {
            return userRepository.findByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Users authenticateUser(Users loginUser) {
        Users userFromDB = userRepository.findByEmail(loginUser.getEmail());

        if (userFromDB != null && userFromDB.getPassword().equals(loginUser.getPassword())
                && userFromDB.getApproved() != null && userFromDB.getApproved()) {
            return userFromDB;
        } else {
            return null;
        }
    }
}