package com.app.matrimonial.service;

import com.app.matrimonial.model.Users;
import org.apache.catalina.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String signUpUser(Users newUser);

}
