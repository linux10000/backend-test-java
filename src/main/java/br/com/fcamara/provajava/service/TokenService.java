package br.com.fcamara.provajava.service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import br.com.fcamara.provajava.exception.InvalidCredentialsException;

public interface TokenService {

	Map<String, String> getToken(String username, String password) throws InvalidCredentialsException, NoSuchAlgorithmException;

	
}
