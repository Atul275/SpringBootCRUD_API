package com.webapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public String exceptionHandler(UsernameNotFoundException unfe) {

		return "UserNotFoundException: " + unfe.getMessage();
	}
}
