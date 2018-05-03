package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.example"})
public class SpringBootRestApiExample2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiExample2Application.class, args);
	}
}
