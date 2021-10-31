package com.paypal.bfs.test.bookingserv.customexceptions;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7963710491155982400L;
	private final String errorMessage;

	public ValidationException(String message, Throwable cause, String errorMessage) {
		super(message, cause);
		this.errorMessage = errorMessage;
	}

	public ValidationException(String message, String errorMessage) {
		super(message);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
