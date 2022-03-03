//root package where spring boot resides
package com.danaherdigital.che_hx.time_series.exceptionhandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;



/**
 * The Class ApiError.
 */
@Data
@Slf4j
public class ApiError {

	/** The timestamp. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	/** The message. */
	private String message;

	/** The sub errors. */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ApiSubError> subErrors;

	/**
	 * Instantiates a new api error.
	 */
	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	public void print() {
		log.info("print method");
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param ex the ex
	 */
	ApiError( Throwable ex) {
		this();
		this.message = "Unexpected error";
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param message the message
	 * @param ex the ex
	 */
	ApiError( String message, Throwable ex) {
		this();
		this.message = message;
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param message the message
	 */
	ApiError( String message) {
		this();
		this.message = message;
	}

	/**
	 * Adds the sub error.
	 *
	 * @param subError the sub error
	 */
	private void addSubError(ApiSubError subError) {
		if (subErrors == null) {
			subErrors = new ArrayList<>();
		}
		subErrors.add(subError);
	}

	/**
	 * Adds the validation error.
	 *
	 * @param object the object
	 * @param field the field
	 * @param rejectedValue the rejected value
	 * @param message the message
	 */
	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object, field, rejectedValue, message));
	}

	/**
	 * Adds the validation error.
	 *
	 * @param object the object
	 * @param message the message
	 */
	private void addValidationError(String object, String message) {
		addSubError(new ApiValidationError(object, message));
	}

	/**
	 * Adds the validation error.
	 *
	 * @param fieldError the field error
	 */
	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}

	/**
	 * Adds the validation errors.
	 *
	 * @param fieldErrors the field errors
	 */
	void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}

	/**
	 * Adds the validation error.
	 *
	 * @param objectError the object error
	 */
	private void addValidationError(ObjectError objectError) {
		this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}

	/**
	 * Adds the validation error.
	 *
	 * @param globalErrors the global errors
	 */
	void addValidationError(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addValidationError);
	}

	/**
	 * Adds the validation error.
	 *
	 * @param cv the cv
	 */
	private void addValidationError(ConstraintViolation<?> cv) {
		this.addValidationError(cv.getRootBeanClass().getSimpleName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
	}

	/**
	 * Adds the validation errors.
	 *
	 * @param constraintViolations the constraint violations
	 */
	void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addValidationError);
	}

	/**
	 * The Class ApiSubError.
	 */
	interface ApiSubError {

	}

	/**
	 * To string.
	 *
	 * @return the java.lang. string
	 */

	/**
	 * To string.
	 *
	 * @return the java.lang. string
	 */
	@Data

	/**
	 * Hash code.
	 *
	 * @return the int
	 */

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@EqualsAndHashCode(callSuper = false)

	/**
	 * Instantiates a new api validation error.
	 *
	 * @param object the object
	 * @param field the field
	 * @param rejectedValue the rejected value
	 * @param message the message
	 */

	/**
	 * Instantiates a new api validation error.
	 *
	 * @param object the object
	 * @param field the field
	 * @param rejectedValue the rejected value
	 * @param message the message
	 */
	@AllArgsConstructor
	class ApiValidationError implements ApiSubError {

		/** The object. */
		private String object;

		/** The field. */
		private String field;

		/** The rejected value. */
		private Object rejectedValue;

		/** The message. */
		private String message;

		/**
		 * Instantiates a new api validation error.
		 *
		 * @param object the object
		 * @param message the message
		 */
		ApiValidationError(String object, String message) {
			this.object = object;
			this.message = message;
		}
	}
}

