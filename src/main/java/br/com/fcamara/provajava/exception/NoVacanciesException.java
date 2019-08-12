package br.com.fcamara.provajava.exception;

public class NoVacanciesException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoVacanciesException() {
		super("Não há mais vagas para esse tipo de veículo");
	}
}
