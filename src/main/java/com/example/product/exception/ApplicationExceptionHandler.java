package com.example.product.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler({NotFoundException.class})
	protected ResponseEntity<ErrorDetails> handleException(NotFoundException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ApplicationException.class})
	protected ResponseEntity<ErrorDetails> handleApplicationException(ApplicationException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({AlreadyExistException.class})
	protected ResponseEntity<ErrorDetails> handleAlreadyExistException(AlreadyExistException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@Order(Ordered.LOWEST_PRECEDENCE)
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<ErrorDetails> handleException(ApplicationException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = getErrorDetails(ex, request);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ErrorDetails getErrorDetails(Exception ex, WebRequest request) {
		return new ErrorDetails.Builder()
								.details(request.getDescription(false))
								.message(ex.getMessage())
								.timestamp(new Date())
								.build();
		
	}
}

