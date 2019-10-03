package com.example.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException {

	public AlreadyExistException(String msg) {
		super(msg);
	}
	
	public AlreadyExistException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
