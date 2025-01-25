package br.com.tournament.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.tournament.dto.ErrorMessage;
import br.com.tournament.dto.ValidateFields;

@ControllerAdvice
public class NotFoundExceptions {
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMessage>objectNoptFound(){		
		var errors = new ErrorMessage(HttpStatus.NOT_FOUND, "Object not found");
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
		
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>validateFields(MethodArgumentNotValidException ex){
		var error = ex.getFieldErrors();
		return ResponseEntity.badRequest()
			                 .body(error.stream()
				             .map(ValidateFields::new)
				             .toList());
	}

}
