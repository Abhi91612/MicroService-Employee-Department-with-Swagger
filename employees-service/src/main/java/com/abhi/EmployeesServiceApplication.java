package com.abhi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;



@SpringBootApplication
@OpenAPIDefinition
public class EmployeesServiceApplication {
	 

	    

	public static void main(String[] args) {
		SpringApplication.run(EmployeesServiceApplication.class, args);
	}
	@Bean
	ModelMapper getModelMapper() {
		System.out.println("Bean created..");
		return new ModelMapper();
	}
	@Bean 
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	



}
