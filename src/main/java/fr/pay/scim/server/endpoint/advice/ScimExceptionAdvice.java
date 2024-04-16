package fr.pay.scim.server.endpoint.advice;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.pay.scim.server.endpoint.entity.error.ScimError;
import fr.pay.scim.server.endpoint.exception.ScimException;

@ControllerAdvice
public class ScimExceptionAdvice {

    
    @ExceptionHandler(ScimException.class)
	public ResponseEntity<ScimError> notFoundExceptionHandler(ScimException ex) {
		 
		ScimError error = new ScimError();
		error.setSchemas(Arrays.asList("urn:ietf:params:scim:api:messages:2.0:Error"));
		error.setStatus(String.valueOf(ex.getStatus().value()));
		error.setDetail(ex.getMessage());
		
		return new ResponseEntity<ScimError>(error, ex.getStatus());
	}
}
