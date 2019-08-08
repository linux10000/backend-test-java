package br.com.fcamara.provajava.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.fcamara.provajava.pojo.Veiculo;
import br.com.fcamara.provajava.service.VeiculoService;

@Service
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class VeiculoServiceImpl implements VeiculoService {

	@Override
	public void insert(Veiculo emp) {
		
	}

	@Override
	public void update(Veiculo emp) {
		
	}

	@Override
	public void delete(Veiculo emp) {
		
	}
}
