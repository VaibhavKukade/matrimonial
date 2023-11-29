package com.app.matrimonial.controller;


import com.app.matrimonial.model.Users;
import com.app.matrimonial.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/userController")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Users newUser) {
        String signUpMessage = userService.signUpUser(newUser);
        if (signUpMessage.equals("User registered successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(signUpMessage);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signUpMessage);
        }
    }
}
