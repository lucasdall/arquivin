package br.com.arquivin.api.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquivin.api.service.EmailService;
import br.com.arquivin.api.service.UserService;

@RestController
@RequestMapping(value = "login")
public class LoginRest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/lostPassword/{email:.+}", method = RequestMethod.POST)
	public void lostPassword(@PathVariable("email") String email) {
		emailService.sendLostPasswordEmail(email);
	}
	
	@RequestMapping(value="/changePassword/{token}", method = RequestMethod.POST)
	public void changePassword(@PathVariable("token") String token, @RequestBody String newPwd) {
		userService.changePwdByToken(token, newPwd);
	}

	@RequestMapping(value="/newUserByToken/{token}", method = RequestMethod.POST)
	public void newUserByToken(@PathVariable("token") String token, @RequestBody String newPwd) {
		userService.newUserByToken(token, newPwd);
	}
}
