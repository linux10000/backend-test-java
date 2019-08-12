package br.com.fcamara.provajava.service;

import br.com.fcamara.provajava.exception.EmpresaExistException;
import br.com.fcamara.provajava.pojo.Empresa;

public interface EmpresaService {

	public Empresa insert(Empresa emp) throws EmpresaExistException;
	public Empresa update(Empresa emp);
	public void delete(Integer empnid);
}
