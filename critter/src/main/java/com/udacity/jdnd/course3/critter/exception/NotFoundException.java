package com.udacity.jdnd.course3.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3561203230373905569L;

	public NotFoundException() {
		super("Not found!");
	}

	public NotFoundException(String message) {
		super(message);
	}
}