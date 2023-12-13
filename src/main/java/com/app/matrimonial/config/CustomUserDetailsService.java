package com.app.matrimonial.config;

import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userInfo = repository.findUserbyUsername(username);
        if (userInfo.isPresent()){
            return userInfo.get();
        }else{
            return null;
        }

    }
}
