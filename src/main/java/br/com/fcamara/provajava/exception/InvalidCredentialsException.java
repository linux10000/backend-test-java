package br.com.fcamara.provajava.exception;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("Invalid Credentials");
	}
}
