//root pacakage where spring boot resides
package com.danaherdigital.che_hx.uom.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.danaherdigital.che_hx.uom.controller.UnitOfMeasureController;
import com.danaherdigital.che_hx.uom.utils.ApplicationConstants;



/**
 * The Class RestExceptionHandler.
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = { UnitOfMeasureController.class })
public class RestExceptionHandler {




	/**
	 * Handle chemtreat exception.
	 *
	 * @param ex      the exception
	 * @param headers the headers
	 * @param status  the status
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(ChemtreatException.class)
	protected ResponseEntity<Object> handleChemtreatException(ChemtreatException ex) {
		ApiError apiError = new ApiError(ex);

		apiError.setMessage(ApplicationConstants.INVALID_INPUT);

		return buildResponseEntity(apiError, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Builds the response entity.
	 *
	 * @param apiError   the api error
	 * @param httpStatus the http status
	 * @return the response entity
	 */
	ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
		return new ResponseEntity<>(apiError, httpStatus);
	}

}