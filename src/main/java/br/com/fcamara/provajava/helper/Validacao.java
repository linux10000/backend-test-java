package br.com.fcamara.provajava.helper;

import br.com.fcamara.provajava.exception.RequiredFieldException;

public class Validacao {

	public static void notNullOrEmpty(String fieldName, String value) throws RequiredFieldException {
		if ( value == null || value.trim().isEmpty() )
			throw new RequiredFieldException(fieldName);
	}
	
	public static void notNull(String fieldName, Object value) throws RequiredFieldException {
		if ( value == null )
			throw new RequiredFieldException(fieldName);
	}
}
