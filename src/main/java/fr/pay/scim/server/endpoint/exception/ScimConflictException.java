package fr.pay.scim.server.endpoint.exception;

import org.springframework.http.HttpStatus;

public class ScimConflictException extends ScimException {

    public ScimConflictException() {
		super(HttpStatus.CONFLICT);
	}

	public ScimConflictException(String message) {
		super(HttpStatus.CONFLICT, message);
	}
}