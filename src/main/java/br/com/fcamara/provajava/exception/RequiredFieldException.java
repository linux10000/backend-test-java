package br.com.fcamara.provajava.exception;

import com.google.common.base.Optional;

public class RequiredFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequiredFieldException(String fieldName) {
		super(Optional.fromNullable(fieldName).or("") + " is required");
	}
}
