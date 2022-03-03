//root pacakage where spring boot resides
package com.danaherdigital.che_hx.schedular.exceptionhandler;

import java.util.Objects;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;





/**
 * The Class RestExceptionHandler.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



	/**
	 * Handle Exception, handle generic Exception.class
	 *
	 * @param ex            the Exception
	 * @param request the request
	 * @return the ApiError object
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ApiError apiError = new ApiError(ex);
		String message = "Not Found";
		if (!Objects.isNull(ex.getMessage())) {
			message = ex.getMessage();
		}
		apiError.setMessage(message);
		return buildResponseEntity(apiError, HttpStatus.NOT_FOUND);
	}



	/**
	 * Builds the response entity.
	 *
	 * @param apiError the api error
	 * @param httpStatus the http status
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
		return new ResponseEntity<>(apiError, httpStatus);
	}

}