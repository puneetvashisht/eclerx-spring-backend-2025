package com.pv.rest_api_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiAppApplication.class, args);
	}

}
