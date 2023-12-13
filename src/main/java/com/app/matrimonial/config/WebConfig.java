package com.app.matrimonial.config;

import com.app.matrimonial.service.impl.CustomUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfiguration {

    private final CustomUserService userService;

    public WebConfig(CustomUserService customUserService) {
        this.userService = customUserService;
    }

    



}
