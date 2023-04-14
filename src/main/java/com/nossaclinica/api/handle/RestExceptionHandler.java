package com.nossaclinica.api.handle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidExceptio(MethodArgumentNotValidException ex){
		
		Map<String, String> erros = getErros(ex.getAllErrors());
		
		return ResponseEntity.badRequest().body(erros);
	}

	private Map<String, String> getErros(List<ObjectError> allErrors) {
		Map<String, String> erros = new HashMap<>();
		
		for(ObjectError e : allErrors) {
			erros.put(((FieldError) e).getField(), e.getDefaultMessage());
		}		
		return erros;
	}
	
}
