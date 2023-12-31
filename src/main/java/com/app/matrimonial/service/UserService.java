package com.app.matrimonial.service;

import com.app.matrimonial.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    String signUpUser(Users newUser);

    List<Users> findAll();

    boolean authenticateUser(Users loginUser);
}
