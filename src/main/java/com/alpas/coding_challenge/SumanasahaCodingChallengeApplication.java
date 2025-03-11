package com.alpas.coding_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition( info = @Info( version = "V1.0.0", title = "Price Service API" ) )
@SpringBootApplication
public class SumanasahaCodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SumanasahaCodingChallengeApplication.class, args);
	}

}
