package com.app.matrimonial.service;

import com.app.matrimonial.model.Users;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    String signUpUser(Users newUser);
    Users updateUser(Users newUser);

    List<Users> findAll();

    List<Users> findUnapprovedUsers();

    Users authenticateUser(Users loginUser);
    Users findUserByUsername(String username);

    JsonNode getUsersActivities(String username,Long id,String filter);
}
