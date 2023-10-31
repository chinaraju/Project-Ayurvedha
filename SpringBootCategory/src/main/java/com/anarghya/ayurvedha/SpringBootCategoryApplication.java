package com.anarghya.ayurvedha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class SpringBootCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCategoryApplication.class, args);
	}

}
