package br.com.prova.resources.exceptions;

import java.sql.SQLException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.prova.services.exceptions.DataBaseException;
import br.com.prova.services.exceptions.ResourceNotFoundException;
/*Deverá ser implementado um ControllerAdvice para customizar os HTTP Response das requisições (mínimo BAD REQUEST)*/
@RestControllerAdvice
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler({DataBaseException.class,DataIntegrityViolationException.class, ConstraintViolationException.class, PSQLException.class,
		SQLException.class,TransactionSystemException.class})
	public ResponseEntity<StandardError> database(Exception ex, HttpServletRequest request){
		String error = "Data base error";
		
		if (ex instanceof DataIntegrityViolationException) {
			error = ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof DataBaseException) {
			error = ((DataBaseException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof ConstraintViolationException) {
			error = ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		}else if (ex instanceof PSQLException) {
			error = ((PSQLException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof SQLException) {
			error = ((SQLException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof TransactionSystemException) {
			error = ((TransactionSystemException) ex).getCause().getCause().getMessage();
		}
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
