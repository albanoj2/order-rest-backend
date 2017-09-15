package com.dzone.albanoj2.example.rest.domain;

public class IllegalOrderStateException extends RuntimeException {

	private static final long serialVersionUID = -3112452343672953856L;

	public IllegalOrderStateException() {
		super();
	}

	public IllegalOrderStateException(String message) {
		super(message);
	}
}
