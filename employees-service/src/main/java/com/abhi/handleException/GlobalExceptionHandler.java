package com.abhi.handleException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abhi.exceptions.DepartmentNotFoundException;
import com.abhi.exceptions.EmployeeNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler (value = {DepartmentNotFoundException.class , EmployeeNotFoundException.class})
	public ResponseEntity<String> handleDepartmentNotFoundException(RuntimeException ex ){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}
