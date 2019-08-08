package br.com.fcamara.provajava.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fcamara.provajava.exception.InvalidCredentialsException;
import br.com.fcamara.provajava.service.TokenService;

@RestController
@RequestMapping("/rest/auth")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Lazy(true)
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public Map<String, String> autenticar(@RequestBody(required = false) Map<String, String> obj) throws InvalidCredentialsException, NoSuchAlgorithmException {
		if ( obj == null || !obj.containsKey("username") || !obj.containsKey("password") )
			throw new InvalidCredentialsException();
		
		return tokenService.getToken(obj.get("username"), obj.get("password"));
	}
}
