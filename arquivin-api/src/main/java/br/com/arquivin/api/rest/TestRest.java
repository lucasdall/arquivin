package br.com.arquivin.api.rest;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestRest {

	private Logger LOGGER = LoggerFactory.getLogger(TestRest.class); 
	
	@RequestMapping(method = RequestMethod.GET)
	public String test() {
		LOGGER.info(String.format("Teste ok [%s]", new Date()));
		return "<h1>OK</h1>";
	}
}
