package com.in28minutos.microservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
	public ExceptionResponse handleUserNotFoundException(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				ex.getCause().getMessage());
		return exceptionResponse;
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().getAllErrors().toString());
		
		return ResponseEntity.badRequest().body(exceptionResponse);
	}
	
//	@ExceptionHandler(UserNotFoundException.class)
//	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
//		
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//				request.getDescription(false));
//		
//		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
//	}
}


