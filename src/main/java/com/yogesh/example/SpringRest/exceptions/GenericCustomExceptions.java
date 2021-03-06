package com.yogesh.example.SpringRest.exceptions;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yogesh.example.SpringRest.bean.ExceptionResponse;

@RestControllerAdvice
public class GenericCustomExceptions extends ResponseEntityExceptionHandler{
	
	@Autowired
	MessageSource messageSources;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request){
		ExceptionResponse exRes = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		return new ResponseEntity(exRes,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex,WebRequest request){
		ExceptionResponse exRes = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
		return new ResponseEntity(exRes,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Locale locale = request.getLocale();
		String errorMessage = messageSources.getMessage(ex.getBindingResult().getFieldError().getDefaultMessage(), null, locale);
		ExceptionResponse res = new ExceptionResponse("Validation Error",errorMessage, new Date()); //get the default validation message which is mentioned in the field
		return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
	}
}
