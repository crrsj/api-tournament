package br.com.tournament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
			title = "API - Gaming Tournament.",
			version = "1.0",
			description = " API to manage gaming tournament.",
			contact = @Contact(name = "Carlos Roberto Ribeiro Santos Junior", email = "crrsj1@gmail.com")
		)
	)
public class TournamentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TournamentApplication.class, args);
	}

}
