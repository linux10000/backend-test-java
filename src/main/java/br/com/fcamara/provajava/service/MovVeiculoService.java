package br.com.fcamara.provajava.service;

import br.com.fcamara.provajava.exception.NoVacanciesException;
import br.com.fcamara.provajava.exception.VeiculoEntrouException;
import br.com.fcamara.provajava.exception.VeiculoNaoEntrouException;

public interface MovVeiculoService {

	void registrarEntrada(Integer veinid, Integer empnid) throws VeiculoEntrouException, NoVacanciesException;

	void registrarSaida(Integer veinid, Integer empnid) throws VeiculoNaoEntrouException;

}
