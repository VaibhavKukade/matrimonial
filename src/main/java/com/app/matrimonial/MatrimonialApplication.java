package com.app.matrimonial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource
public class MatrimonialApplication{

	public static void main(String[] args) {
		SpringApplication.run(MatrimonialApplication.class, args);
		System.out.println("Application Started");
	}

}
