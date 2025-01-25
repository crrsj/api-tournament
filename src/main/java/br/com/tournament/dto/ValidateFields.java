package br.com.tournament.dto;

import org.springframework.validation.FieldError;

public record ValidateFields(
		String field,
		String message) {
	
	public ValidateFields(FieldError errors) {
		
		this(
			  errors.getField(),
               errors.getDefaultMessage());
		
	}

}
