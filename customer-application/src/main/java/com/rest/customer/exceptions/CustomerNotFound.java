package com.rest.customer.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException {

	public CustomerNotFound(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;
}