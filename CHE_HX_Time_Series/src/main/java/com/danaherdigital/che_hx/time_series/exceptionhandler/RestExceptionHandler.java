//root pacakage where spring boot resides
package com.danaherdigital.che_hx.time_series.exceptionhandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;



/**
 * The Class RestExceptionHandler.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


	/**
	 * Handle MissingServletRequestParameterException. Triggered when a 'required'
	 * request parameter is missing.
	 *
	 * @param ex
	 *            MissingServletRequestParameterException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new ApiError( error, ex),null);
	}

	/**
	 * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is
	 * invalid as well.
	 *
	 * @param ex
	 *            HttpMediaTypeNotSupportedException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		return buildResponseEntity(
				new ApiError(builder.substring(0, builder.length() - 2), ex),null);
	}

	/**
	 * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid
	 * validation.
	 *
	 * @param ex
	 *            the MethodArgumentNotValidException that is thrown when @Valid
	 *            validation fails
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(ex);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError,HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles javax.validation.ConstraintViolationException. Thrown when @Validated
	 * fails.
	 *
	 * @param ex
	 *            the ConstraintViolationException
	 * @return the ApiError object
	 */
	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
		ApiError apiError = new ApiError(ex);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getConstraintViolations());
		return buildResponseEntity(apiError,HttpStatus.BAD_REQUEST);
	}




	/**
	 * Handle HttpMessageNotReadableException. Happens when request JSON is
	 * malformed.
	 *
	 * @param ex
	 *            HttpMessageNotReadableException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError( error, ex),status);
	}

	/**
	 * Handle HttpMessageNotWritableException.
	 *
	 * @param ex
	 *            HttpMessageNotWritableException
	 * @param headers
	 *            HttpHeaders
	 * @param status
	 *            HttpStatus
	 * @param request
	 *            WebRequest
	 * @return the ApiError object
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Error writing JSON output";
		return buildResponseEntity(new ApiError( error, ex),null);
	}

	/**
	 * Handle javax.persistence.EntityNotFoundException
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
		return buildResponseEntity(new ApiError( ex),null);
	}

	/**
	 * Handle DataIntegrityViolationException, inspects the cause for different DB
	 * causes.
	 *
	 * @param ex            the DataIntegrityViolationException
	 * @param request the request
	 * @return the ApiError object
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new ApiError( "Database error", ex.getCause()),null);
		}
		return buildResponseEntity(new ApiError( ex),null);
	}

	/**
	 * Handle Exception, handle generic Exception.class
	 *
	 * @param ex            the Exception
	 * @param request the request
	 * @return the ApiError object
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ApiError apiError = new ApiError(ex);
		apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		return buildResponseEntity(apiError,null);
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
		if (ex.getMessage() != null ) {
			apiError.setMessage(ex.getMessage());

		}
		return buildResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Builds the response entity.
	 *
	 * @param apiError the api error
	 * @param httpStatus the http status
	 * @return the response entity
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus httpStatus) {
		log.error("Error : ");
		return new ResponseEntity<>(apiError, httpStatus);
	}

}