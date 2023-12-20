package com.app.matrimonial.controller;


import com.app.matrimonial.model.Users;
import com.app.matrimonial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Users newUser) {
        Users updateUser = userService.updateUser(newUser);
        if (updateUser!=null){
            return ResponseEntity.ok("User updated successfully");
        }else{
            return  ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<Users>> getAllExpenses() {
        List<Users> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users loginUser) {
        // Implement login logic using userService or authentication service
        Users isAuthenticated = userService.authenticateUser(loginUser);

        if (isAuthenticated!=null){
            return ResponseEntity.ok(isAuthenticated);
        }else{
            return (ResponseEntity<Users>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/getAllUser/unapproved")
    public ResponseEntity<List<Users>> getAllUnapprovedUsers() {
        List<Users> users = userService.findUnapprovedUsers();
        if (users!=null && users.size()>0) {
            return ResponseEntity.ok(users);
        }else{
            return  ResponseEntity.noContent().build();
        }
    }

}
