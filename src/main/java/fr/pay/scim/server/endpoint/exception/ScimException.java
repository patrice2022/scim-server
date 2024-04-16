package fr.pay.scim.server.endpoint.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data()
@EqualsAndHashCode(callSuper=true)
public abstract class ScimException extends Exception {

    private HttpStatus status = null;

	public ScimException(HttpStatus status) {
		super();
		this.status = status;
	}

	public ScimException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}
}
