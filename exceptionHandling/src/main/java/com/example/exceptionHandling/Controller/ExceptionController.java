package com.example.exceptionHandling.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.exceptionHandling.Exceptions.*;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler
	public ResponseEntity<Object> notFoundException(ProductNotFoundException pe){
		return new ResponseEntity<>("Product not Found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<Object> alreadyExistsException(ProductAlreadyExists pe){
		return new ResponseEntity<>("Product Already Exists",HttpStatus.NOT_FOUND);
	}

}
