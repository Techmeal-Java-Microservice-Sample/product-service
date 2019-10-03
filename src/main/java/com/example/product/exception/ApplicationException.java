package com.example.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationException extends RuntimeException {

	public ApplicationException(String msg) {
		super(msg);
	}
	
	public ApplicationException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	
}
