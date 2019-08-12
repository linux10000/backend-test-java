package br.com.fcamara.provajava.exception;

public class VeiculoNaoEntrouException extends Exception {

	private static final long serialVersionUID = 1L;

	public VeiculoNaoEntrouException(Integer veinid, Integer empnid) {
		super("veiculo " + veinid + " não possui entrada aberta na empresa " + empnid );
	}
}
