package com.success.apigataway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatawayApplication.class, args);
	}

}
