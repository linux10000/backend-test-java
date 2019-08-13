package br.com.fcamara.provajava.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fcamara.provajava.dao.VeiculoDao;
import br.com.fcamara.provajava.exception.VeiculoExistException;
import br.com.fcamara.provajava.helper.Validacao;
import br.com.fcamara.provajava.pojo.Veiculo;
import br.com.fcamara.provajava.service.VeiculoService;

@Service
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class VeiculoServiceImpl implements VeiculoService {

	@Lazy(true)
	@Autowired
	private VeiculoDao veiculoDao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public Veiculo insert(Veiculo vei) throws VeiculoExistException {
		Validacao.notNull("veiculo", vei);
		Validacao.notNullOrEmpty("cor", vei.getVeiccor());
		Validacao.notNullOrEmpty("merca", vei.getVeicmarca());
		Validacao.notNullOrEmpty("modelo", vei.getVeicmodelo());
		Validacao.notNullOrEmpty("placa", vei.getVeicplaca());
		Validacao.notNull("tipo", vei.getVeintipo());

		if ( veiculoDao.countByVeicplaca(vei.getVeicplaca()) > 0 ) throw new VeiculoExistException();
		
		return veiculoDao.saveAndFlush(vei);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public Veiculo update(Veiculo vei) {
		Validacao.notNull("veiculo", vei);
		Validacao.notNull("id do veiculo", vei.getVeinid());
		Validacao.notNullOrEmpty("cor", vei.getVeiccor());
		Validacao.notNullOrEmpty("merca", vei.getVeicmarca());
		Validacao.notNullOrEmpty("modelo", vei.getVeicmodelo());
		Validacao.notNullOrEmpty("placa", vei.getVeicplaca());
		Validacao.notNull("tipo", vei.getVeintipo());
		if ( vei.getVeintipo() != 0 && vei.getVeintipo() != 1 ) throw new RuntimeException("informe tipo 0 para carro ou 1 para moto");
		
		Optional<Veiculo> old = veiculoDao.findById(vei.getVeinid());
		Validacao.notNull("veiculo could not be found and ", old.orElse(null));
		
		old.get().setVeiccor(vei.getVeiccor());
		old.get().setVeicmarca(vei.getVeicmarca());
		old.get().setVeicmodelo(vei.getVeicmodelo());
		old.get().setVeintipo(vei.getVeintipo());
		
		return veiculoDao.saveAndFlush(old.get());
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public void delete(Integer veinid) {
		Validacao.notNull("id do veiculo", veinid);
		
		Optional<Veiculo> old = veiculoDao.findById(veinid);
		Validacao.notNull("veiculo could not be found and ", old.orElse(null));
		
		veiculoDao.delete(old.get());
	}
}
