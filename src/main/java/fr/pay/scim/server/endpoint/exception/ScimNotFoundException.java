package fr.pay.scim.server.endpoint.exception;

import org.springframework.http.HttpStatus;

public class ScimNotFoundException extends ScimException {

    public ScimNotFoundException() {
		super(HttpStatus.NOT_FOUND);
	}

	public ScimNotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}