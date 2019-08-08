package br.com.fcamara.provajava.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.fcamara.provajava.dao.EmpresaDao;
import br.com.fcamara.provajava.helper.Validacao;
import br.com.fcamara.provajava.pojo.Empresa;
import br.com.fcamara.provajava.service.EmpresaService;

@Service
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class EmpresaServiceImpl implements EmpresaService {
	
	@Lazy(true)
	@Autowired
	private EmpresaDao empresaDao;

	@Override
	public Empresa insert(Empresa emp) {
		Validacao.notNull("empresa", emp);
		Validacao.notNullOrEmpty("nome da empresa", emp.getEmpcnome());
		Validacao.notNullOrEmpty("endereço da empresa", emp.getEmpcendereco());
		Validacao.notNullOrEmpty("telefone da empresa", emp.getEmpctelefone());
		Validacao.notNull("quantidade de vagas para carros na empresa", emp.getEmpnvagacarros());
		Validacao.notNull("quantidade de vagas para motos empresa", emp.getEmpnvagamotos());
		
		return empresaDao.saveAndFlush(emp);
	}

	@Override
	public Empresa update(Empresa emp) {
		Validacao.notNull("empresa", emp);
		Validacao.notNull("id da empresa", emp.getEmpnid());
		Validacao.notNullOrEmpty("nome da empresa", emp.getEmpcnome());
		Validacao.notNullOrEmpty("endereço da empresa", emp.getEmpcendereco());
		Validacao.notNullOrEmpty("telefone da empresa", emp.getEmpctelefone());
		Validacao.notNull("quantidade de vagas para carros na empresa", emp.getEmpnvagacarros());
		Validacao.notNull("quantidade de vagas para motos empresa", emp.getEmpnvagamotos());
				
		Optional<Empresa> old = empresaDao.findById(emp.getEmpnid());
		Validacao.notNull("empresa could not be found and ", old.get());
		
		old.get().setEmpcendereco(emp.getEmpcendereco());
		old.get().setEmpcnome(emp.getEmpcnome());
		old.get().setEmpctelefone(emp.getEmpctelefone());
		old.get().setEmpnvagacarros(emp.getEmpnvagacarros());
		old.get().setEmpnvagamotos(emp.getEmpnvagamotos());
		
		return empresaDao.saveAndFlush(old.get());
	}

	@Override
	public void delete(Empresa emp) {
		Validacao.notNull("empresa", emp);
		Validacao.notNull("id da empresa", emp.getEmpnid());
		
		Optional<Empresa> old = empresaDao.findById(emp.getEmpnid());
		Validacao.notNull("empresa could not be found and ", old.get());
		
		empresaDao.delete(old.get());
	}

}
