package br.com.fcamara.provajava.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.primitives.Ints;

import br.com.fcamara.provajava.dao.VeiculoDao;
import br.com.fcamara.provajava.exception.VeiculoExistException;
import br.com.fcamara.provajava.pojo.Veiculo;
import br.com.fcamara.provajava.service.VeiculoService;

@RestController
@RequestMapping("/rest/veiculo")
public class VeiculoController {
	
	private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
	
	@Lazy(true)
	@Autowired
	private VeiculoDao veiculoDao;
	
	@Lazy(true)
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping(path = "/all")
	public List<Veiculo> listarTodos() {
		return veiculoDao.findAll();
	}
	
	@PostMapping
	public Veiculo insert(@RequestBody(required = false) Veiculo emp) throws VeiculoExistException {
		return veiculoService.insert(emp);
	}
	
	@PutMapping
	public Veiculo update(@RequestBody(required = false) Veiculo emp) {
		return veiculoService.update(emp);
	}
	
	@DeleteMapping
	public void delete(@RequestParam Map<String, Object> params) {
		veiculoService.delete(Ints.tryParse((String) Optional.ofNullable(params.get("veinid")).orElse("")));
	}
}
