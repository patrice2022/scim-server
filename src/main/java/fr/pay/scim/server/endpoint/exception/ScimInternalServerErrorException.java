package fr.pay.scim.server.endpoint.exception;

import org.springframework.http.HttpStatus;

public class ScimInternalServerErrorException extends ScimException {

    public ScimInternalServerErrorException() {
		super(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ScimInternalServerErrorException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}