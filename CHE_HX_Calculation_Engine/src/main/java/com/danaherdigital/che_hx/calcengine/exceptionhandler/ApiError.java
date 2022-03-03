//root package where spring boot resides
package com.danaherdigital.che_hx.calcengine.exceptionhandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;




/**
 * The Class ApiError.
 */
@Data
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

}

