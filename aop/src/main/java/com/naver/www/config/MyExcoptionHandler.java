package com.naver.www.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class MyExcoptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String badRequest(IllegalArgumentException e) {
		return e.getMessage();
	}
	
}
