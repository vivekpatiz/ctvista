//root pacakage where spring boot resides
package com.danaherdigital.che_hx.calcengine.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.danaherdigital.che_hx.calcengine.controller.CalcController;
import com.danaherdigital.che_hx.calcengine.controller.DesignCalController;
import com.danaherdigital.che_hx.calcengine.utils.ApplicationConstants;
import java.security.InvalidParameterException;
import org.springframework.web.client.HttpServerErrorException;
import lombok.extern.slf4j.Slf4j;



/**
 * The Class RestExceptionHandler.
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = {CalcController.class,DesignCalController.class})
@Validated
@Slf4j
public class RestExceptionHandler  {


	/**
	 * Handle method argument not valid.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<Object> invalidParam(InvalidParameterException ex) {
		log.info("error in calc engine");
		ApiError apiError = new ApiError(ex);
		apiError.setMessage("Invalid input");
		return buildResponseEntity(apiError,HttpStatus.BAD_REQUEST);
	}


@ExceptionHandler(HttpServerErrorException.class)
	protected ResponseEntity<Object> handleInteralServerError(HttpServerErrorException ex) {
		ApiError apiError = new ApiError(ex);
		return buildResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle chemtreat exception.
	 *
	 * @param ex the exception
	 * @param headers the headers
	 * @param status the status
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
	 * @param apiError the api error
	 * @param httpStatus the http status
	 * @return the response entity
	 */
	 ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
		return new ResponseEntity<>(apiError, httpStatus);
	}

}