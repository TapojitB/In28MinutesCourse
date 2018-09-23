/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

/**
 * @author TapojitBhattacharya
 *
 */
@ControllerAdvice
@RestController
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				"details is: " + ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllUserNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				"details is: " + ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Error",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

}
