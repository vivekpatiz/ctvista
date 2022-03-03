package com.danaherdigital.che_hx.schedular.exceptionhandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
        super();
    }


}