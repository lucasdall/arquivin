package br.com.arquivin.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquivin.model.enuns.CompanyMainColor;
import br.com.arquivin.model.enuns.HeaderColor;

@RestController
@RequestMapping(value = "enum")
public class EnumRest {

	@RequestMapping(value = "/headerColor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<HeaderColor[]> headerColor() {
		return new ResponseEntity<>(HeaderColor.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/companyMainColor", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CompanyMainColor[]> companyMainColor() {
		return new ResponseEntity<>(CompanyMainColor.values(), HttpStatus.OK);
	}

}
