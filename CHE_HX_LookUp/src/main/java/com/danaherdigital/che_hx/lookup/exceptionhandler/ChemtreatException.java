package com.danaherdigital.che_hx.lookup.exceptionhandler;

public class ChemtreatException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorMessage;

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	public ChemtreatException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
