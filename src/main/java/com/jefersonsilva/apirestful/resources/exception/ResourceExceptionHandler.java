package com.jefersonsilva.apirestful.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jefersonsilva.apirestful.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		Long timestamp = System.currentTimeMillis();
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Objeto não encontrado!";
		String message = e.getMessage();
		String path = request.getRequestURI();
		
		StandardError stdError = new StandardError(timestamp, status.value(), error, message, path);
		
		return ResponseEntity.status(status).body(stdError);
	}
}
