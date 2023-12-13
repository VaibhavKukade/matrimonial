package com.app.matrimonial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@Configuration
@ImportResource
public class MatrimonialApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MatrimonialApplication.class, args);
		System.out.println("Application Started");
	}

}
