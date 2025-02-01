package com.weclear.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class BackendApplication {

	@PostConstruct
	public void init() {
		System.out.println("DB URL: " + System.getenv("SPRING_DATASOURCE_URL"));
		System.out.println("DB Username: " + System.getenv("SPRING_DATASOURCE_USERNAME"));
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
