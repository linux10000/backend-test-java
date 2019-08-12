package br.com.fcamara.provajava.service;

import br.com.fcamara.provajava.exception.VeiculoExistException;
import br.com.fcamara.provajava.pojo.Veiculo;

public interface VeiculoService {

	public Veiculo insert(Veiculo emp) throws VeiculoExistException;
	public Veiculo update(Veiculo emp);
	public void delete(Integer veinid);
}
