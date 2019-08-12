package br.com.fcamara.provajava.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fcamara.provajava.dao.EmpresaDao;
import br.com.fcamara.provajava.exception.EmpresaExistException;
import br.com.fcamara.provajava.helper.Validacao;
import br.com.fcamara.provajava.pojo.Empresa;
import br.com.fcamara.provajava.service.EmpresaService;

@Service
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class EmpresaServiceImpl implements EmpresaService {
	
	@Lazy(true)
	@Autowired
	private EmpresaDao empresaDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public Empresa insert(Empresa emp) throws EmpresaExistException {
		Validacao.notNull("empresa", emp);
		Validacao.notNullOrEmpty("nome da empresa", emp.getEmpcnome());
		Validacao.notNullOrEmpty("cnpj da empresa", emp.getEmpccnpj());
		Validacao.notNullOrEmpty("endereço da empresa", emp.getEmpcendereco());
		Validacao.notNullOrEmpty("telefone da empresa", emp.getEmpctelefone());
		Validacao.notNull("quantidade de vagas para carros na empresa", emp.getEmpnvagacarros());
		Validacao.notNull("quantidade de vagas para motos empresa", emp.getEmpnvagamotos());
		
		if ( empresaDao.countByEmpccnpj(emp.getEmpccnpj()) > 0 ) throw new EmpresaExistException();
		
		return empresaDao.saveAndFlush(emp);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public Empresa update(Empresa emp) {
		Validacao.notNull("empresa", emp);
		Validacao.notNull("id da empresa", emp.getEmpnid());
		Validacao.notNullOrEmpty("nome da empresa", emp.getEmpcnome());
		Validacao.notNullOrEmpty("cnpj da empresa", emp.getEmpccnpj());
		Validacao.notNullOrEmpty("endereço da empresa", emp.getEmpcendereco());
		Validacao.notNullOrEmpty("telefone da empresa", emp.getEmpctelefone());
		Validacao.notNull("quantidade de vagas para carros na empresa", emp.getEmpnvagacarros());
		Validacao.notNull("quantidade de vagas para motos empresa", emp.getEmpnvagamotos());
				
		Optional<Empresa> old = empresaDao.findById(emp.getEmpnid());
		Validacao.notNull("empresa could not be found and ", old.orElse(null));
		
		old.get().setEmpcendereco(emp.getEmpcendereco());
		old.get().setEmpcnome(emp.getEmpcnome());
		old.get().setEmpctelefone(emp.getEmpctelefone());
		old.get().setEmpnvagacarros(emp.getEmpnvagacarros());
		old.get().setEmpnvagamotos(emp.getEmpnvagamotos());
		
		return empresaDao.saveAndFlush(old.get());
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public void delete(Integer empnid) {
		Validacao.notNull("id da empresa", empnid);
		
		Optional<Empresa> old = empresaDao.findById(empnid);
		Validacao.notNull("empresa could not be found and ", old.orElse(null));
		
		empresaDao.delete(old.get());
	}

}
