package br.com.fcamara.provajava.service;

import br.com.fcamara.provajava.pojo.Empresa;

public interface EmpresaService {

	public Empresa insert(Empresa emp);
	public Empresa update(Empresa emp);
	public void delete(Empresa emp);
}
