package com.in28minutos.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4321648907479768440L;

	public PostNotFoundException(String message) {
		super(message);
	}
	
}
