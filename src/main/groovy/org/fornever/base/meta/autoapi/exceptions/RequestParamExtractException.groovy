package org.fornever.base.meta.autoapi.exceptions

class RequestParamExtractException extends Exception {

	public RequestParamExtractException() {
		super()
	}

	public RequestParamExtractException(String message) {
		super(message)
	}

	public RequestParamExtractException(Throwable cause) {
		super(cause)
	}

	public RequestParamExtractException(String message, Throwable cause) {
		super(message, cause)
	}

	public RequestParamExtractException(String message, Throwable cause, boolean enableSuppression,
	boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace)
	}
}
