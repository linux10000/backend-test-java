package br.com.fcamara.provajava.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.primitives.Ints;

import br.com.fcamara.provajava.dao.MovVeiculoDao;
import br.com.fcamara.provajava.exception.NoVacanciesException;
import br.com.fcamara.provajava.exception.VeiculoEntrouException;
import br.com.fcamara.provajava.exception.VeiculoNaoEntrouException;
import br.com.fcamara.provajava.service.MovVeiculoService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/movimento")
public class MovVeiculoController {
	
	@Lazy(true)
	@Autowired
	private MovVeiculoService movVeiculoService;
	@Lazy(true)
	@Autowired
	private MovVeiculoDao movVeiculoDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MovVeiculoController.class);
	
	@PostMapping(path = "/entrada")
	public void registrarEntrada(@RequestBody(required = false) Map<String, Object> params) throws VeiculoEntrouException, NoVacanciesException {
		movVeiculoService.registrarEntrada(
			Ints.tryParse((String) Optional.ofNullable(params.get("veinid")).orElse("")), 
			Ints.tryParse((String) Optional.ofNullable(params.get("empnid")).orElse(""))
		);	
	}
	
	@PostMapping(path = "/saida")
	public void registrarSaida(@RequestBody(required = false) Map<String, Object> params) throws VeiculoNaoEntrouException {
		movVeiculoService.registrarSaida(
			Ints.tryParse((String) Optional.ofNullable(params.get("veinid")).orElse("")), 
			Ints.tryParse((String) Optional.ofNullable(params.get("empnid")).orElse(""))
		);
	}
	
	@GetMapping(path = "/sumario")
	public List<Map<String, Object>> getSumario() {
		return movVeiculoDao.getGeneralSummary();
	}
	
	@GetMapping(path = "/sumario-por-hora")
	public List<Map<String, Object>> getSumarioPorHora() {
		return movVeiculoDao.getSummaryByHour();
	}
}
