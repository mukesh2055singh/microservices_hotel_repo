package com.user.userService.entity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public String handleResourceNotFoundException(UserNotFoundException ex) {
		return ex.getMessage();
	}
}
