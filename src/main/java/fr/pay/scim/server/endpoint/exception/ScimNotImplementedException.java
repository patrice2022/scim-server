package fr.pay.scim.server.endpoint.exception;

import org.springframework.http.HttpStatus;

public class ScimNotImplementedException extends ScimException {

    public ScimNotImplementedException() {
		super(HttpStatus.NOT_IMPLEMENTED);
	}

	public ScimNotImplementedException(String message) {
		super(HttpStatus.NOT_IMPLEMENTED, message);
	}
}
