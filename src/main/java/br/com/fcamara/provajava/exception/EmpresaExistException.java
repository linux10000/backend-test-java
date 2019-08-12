package br.com.fcamara.provajava.exception;

public class EmpresaExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmpresaExistException() {
		super("Empresa already exists");
	}
}
