package com.avg.exceptions;

public class AvgException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public AvgException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public AvgException() {
		super();
	}
}