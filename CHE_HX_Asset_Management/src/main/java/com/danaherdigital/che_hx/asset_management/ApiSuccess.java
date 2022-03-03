//root pacakage where spring boot resides
package com.danaherdigital.che_hx.asset_management;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


/**
 * ApiSuccess class for success response.
 *
 * 
 */
@Data
public class ApiSuccess {

	/** The timestamp. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	/** The data. */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;

	/**
	 * Instantiates a new api success.
	 */
	private ApiSuccess() {
		timestamp = LocalDateTime.now();
	}

	
	/**
	 * Instantiates a new api success.
	 *
	 * @param object the object
	 */
	public ApiSuccess(Object object) {
		this();
		this.data = object;
	}

}
