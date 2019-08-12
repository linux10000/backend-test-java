package br.com.fcamara.provajava.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.fcamara.provajava.dao.EmpresaDao;
import br.com.fcamara.provajava.dao.MovVeiculoDao;
import br.com.fcamara.provajava.dao.VeiculoDao;
import br.com.fcamara.provajava.exception.NoVacanciesException;
import br.com.fcamara.provajava.exception.VeiculoEntrouException;
import br.com.fcamara.provajava.exception.VeiculoNaoEntrouException;
import br.com.fcamara.provajava.helper.Validacao;
import br.com.fcamara.provajava.pojo.Empresa;
import br.com.fcamara.provajava.pojo.MovVeiculo;
import br.com.fcamara.provajava.pojo.Veiculo;
import br.com.fcamara.provajava.service.MovVeiculoService;

@Service
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class MovVeiculoServiceImpl implements MovVeiculoService {
	
	@Lazy(true)
	@Autowired
	private MovVeiculoDao movVeiculoDao;
	@Lazy(true)
	@Autowired
	private EmpresaDao empresaDao;
	@Lazy(true)
	@Autowired
	private VeiculoDao veiculoDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public void registrarEntrada(Integer veinid, Integer empnid) throws VeiculoEntrouException, NoVacanciesException {
		Validacao.notNull("id do veiculo", veinid);
		Validacao.notNull("id do empresa", empnid);
		
		//nao pode inserir entrada se ja existe uma sem saida
		List<MovVeiculo> movs = movVeiculoDao.findMissingExit(veinid, empnid, PageRequest.of(0, 1));
		if ( movs.size() > 0 ) throw new VeiculoEntrouException(veinid, empnid, movs.get(0).getMovdentrada());
		
		Optional<Empresa> emp = empresaDao.findById(empnid);
		Validacao.notNull("empresa could not be found and ", emp.orElse(null));
		
		Optional<Veiculo> vei = veiculoDao.findById(veinid);
		Validacao.notNull("veiculo could not be found and ", vei.orElse(null));
		
		Integer parked = movVeiculoDao.countParkedVeiculosByTypeAndEmpresa(empnid, vei.get().getVeintipo());
		Integer vacancies = vei.get().getVeintipo() == 0 ? emp.get().getEmpnvagacarros() : emp.get().getEmpnvagamotos();
		
		if ( parked >= vacancies ) throw new NoVacanciesException();
		
		movVeiculoDao.saveAndFlush(new MovVeiculo(null, veinid, empnid, OffsetDateTime.now(), null));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class, transactionManager = "transactionManagerPrincipal")
	@Override
	public void registrarSaida(Integer veinid, Integer empnid) throws VeiculoNaoEntrouException {
		Validacao.notNull("id do veiculo", veinid);
		Validacao.notNull("id do empresa", empnid);
		
		//soh pode inserir saida se ja existe um mov sem saida
		List<MovVeiculo> movs = movVeiculoDao.findMissingExit(veinid, empnid, PageRequest.of(0, 1));
		if ( movs.size() == 0 ) throw new VeiculoNaoEntrouException(veinid, empnid);
		
		movs.get(0).setMovdsaida(OffsetDateTime.now());
		movVeiculoDao.saveAndFlush(movs.get(0));
	}
}
