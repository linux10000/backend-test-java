package br.com.fcamara.provajava.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fcamara.provajava.pojo.Veiculo;
import br.com.fcamara.provajava.service.VeiculoService;

@RestController
@RequestMapping("/rest/veiculo")
public class VeiculoController {
	
	private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
	
	@Lazy(true)
	@Autowired
	private VeiculoService veiculoService;

	
	@PostMapping
	public void insert(Veiculo emp) {
		veiculoService.insert(emp);
	}
	
	@PutMapping
	public void update(Veiculo emp) {
		veiculoService.update(emp);
	}
	
	@DeleteMapping
	public void delete(Veiculo emp) {
		veiculoService.delete(emp);
	}
}
