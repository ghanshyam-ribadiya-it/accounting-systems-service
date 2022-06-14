package com.accounting.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(SystemException.class)
	public ResponseEntity<Object> handleSystemException(SystemException systemException, WebRequest request) {
		log.info("GlobalExceptionHandler -> handleSystemException method call");
		return new ResponseEntity<Object>(systemException.getApiResponse(), new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
	}
}