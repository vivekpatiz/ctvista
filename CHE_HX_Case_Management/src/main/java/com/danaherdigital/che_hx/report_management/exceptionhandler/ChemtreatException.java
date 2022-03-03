package com.danaherdigital.che_hx.report_management.exceptionhandler;

public class ChemtreatException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public ChemtreatException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
