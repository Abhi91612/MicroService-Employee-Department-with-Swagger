package com.abhi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	OpenAPI getOpenApi() {
		return new OpenAPI().info(new Info().title("Employee with Department"
				+ " Micro Service Documentation  by Abhishek Kumar").description("Developed and maintained by "
						+ "Abhishek Kumar, this API integrates seamlessly with other internal systems, "
						+ "enabling smooth data flow and consistent operations across multiple services."
						+ "Key Features:\r\n"
						+ "\r\n"
						+ "Full CRUD operations for employee records\r\n"
						+ "\r\n"
						+ "Seamless integration with MySQL database\r\n"
						+ "\r\n"
						+ "Comprehensive API documentation using OpenAPI 3.0\r\n"
						+ "\r\n"
						+ "Version: 1.0.0\r\n"
						+ "Contact: Abhishek Kumar – abhishek3083365@gmail.com"));
	}
}
