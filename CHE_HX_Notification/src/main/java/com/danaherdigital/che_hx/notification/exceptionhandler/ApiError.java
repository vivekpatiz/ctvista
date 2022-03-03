//root package where spring boot resides
package com.danaherdigital.che_hx.notification.exceptionhandler;

import java.time.LocalDateTime;



import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
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
	ApiError(Throwable ex) {
		this();
		this.message = "Unexpected error";
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param message the message
	 * @param ex      the ex
	 */
	ApiError(String message, Throwable ex) {
		this();
		this.message = message;
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param message the message
	 */
	ApiError(String message) {
		this();
		this.message = message;
	}








}
