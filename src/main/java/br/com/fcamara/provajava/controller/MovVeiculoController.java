package br.com.fcamara.provajava.controller;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.primitives.Ints;

import br.com.fcamara.provajava.exception.NoVacanciesException;
import br.com.fcamara.provajava.exception.VeiculoEntrouException;
import br.com.fcamara.provajava.exception.VeiculoNaoEntrouException;
import br.com.fcamara.provajava.service.MovVeiculoService;

@RestController
@RequestMapping("/rest/movimento")
public class MovVeiculoController {
	
	@Lazy(true)
	@Autowired
	private MovVeiculoService movVeiculoService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovVeiculoController.class);
	
	@PostMapping(path = "entrada")
	public void registrarEntrada(@RequestBody(required = false) Map<String, Object> params) throws VeiculoEntrouException, NoVacanciesException {
		movVeiculoService.registrarEntrada(
			Ints.tryParse((String) Optional.ofNullable(params.get("veinid")).orElse("")), 
			Ints.tryParse((String) Optional.ofNullable(params.get("empnid")).orElse(""))
		);	
	}
	
	@PostMapping(path = "saida")
	public void registrarSaida(@RequestBody(required = false) Map<String, Object> params) throws VeiculoNaoEntrouException {
		movVeiculoService.registrarSaida(
			Ints.tryParse((String) Optional.ofNullable(params.get("veinid")).orElse("")), 
			Ints.tryParse((String) Optional.ofNullable(params.get("empnid")).orElse(""))
		);
	}
}
