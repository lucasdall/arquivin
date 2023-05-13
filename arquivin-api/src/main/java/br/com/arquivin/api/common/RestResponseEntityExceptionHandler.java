package br.com.arquivin.api.common;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.arquivin.dto.ValidationDTO;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<List<ValidationDTO>> handleAccessDeniedException(ValidationException ex) {
		return new ResponseEntity<List<ValidationDTO>>(ex.getValidations(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}