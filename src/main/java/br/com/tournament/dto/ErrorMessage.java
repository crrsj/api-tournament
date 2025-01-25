package br.com.tournament.dto;

import org.springframework.http.HttpStatus;

public record ErrorMessage(
		 HttpStatus status,
		    String message) {
	

}
