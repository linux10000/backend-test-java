package br.com.fcamara.provajava.exception;

import java.time.OffsetDateTime;

public class VeiculoEntrouException extends Exception {

	private static final long serialVersionUID = 1L;

	public VeiculoEntrouException(Integer veinid, Integer empnid, OffsetDateTime data) {
		super("veiculo " + veinid + " já possui entrada na empresa " + empnid + " na data de " + data);
	}
}
