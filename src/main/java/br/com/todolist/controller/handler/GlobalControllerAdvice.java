package br.com.todolist.controller.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.todolist.exception.IdNotFoundException;
import br.com.todolist.exception.InvalidEntityException;
import br.com.todolist.exception.TitleNotFoundException;

@Order(1)
@RestControllerAdvice
public class GlobalControllerAdvice {

	
	 private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
	        return new ResponseEntity<>(apiError, apiError.getStatus());
	    }

	    @ExceptionHandler({InvalidEntityException.class})
	    protected ResponseEntity<Object> handleBadRequest(
	            InvalidEntityException ex) {
	        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
	        apiError.setMessage(ex.getMessage());
	        return buildResponseEntity(apiError);
	    }

	    @ExceptionHandler({IdNotFoundException.class, TitleNotFoundException.class})
	    protected ResponseEntity<Object> handleNotFound(
	            Exception ex) {
	        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
	        apiError.setMessage(ex.getMessage());
	        return buildResponseEntity(apiError);
	    }

	    @ExceptionHandler({Exception.class})
	    protected ResponseEntity<Object> handleInternalServerError(
	            Exception ex) {
	        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
	        apiError.setMessage(ex.getMessage());
	        return buildResponseEntity(apiError);
	    }
	
}