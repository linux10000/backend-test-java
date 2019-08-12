package br.com.fcamara.provajava.exception;

public class VeiculoExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public VeiculoExistException() {
		super("Veiculo already exists");
	}
}
