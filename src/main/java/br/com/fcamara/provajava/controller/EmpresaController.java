package br.com.fcamara.provajava.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.fcamara.provajava.dao.EmpresaDao;
import br.com.fcamara.provajava.pojo.Empresa;
import br.com.fcamara.provajava.service.EmpresaService;

@RestController
@RequestMapping("/rest/empresa")
public class EmpresaController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
	
	@Lazy(true)
	@Autowired
	private EmpresaDao empresaDao;
	
	@Lazy(true)
	@Autowired
	private EmpresaService empresaService;

	@GetMapping(path = "/all")
	public List<Empresa> listarTodas() {
		return empresaDao.findAll();
	}
	
	@PostMapping
	public Empresa insert(@RequestBody(required = false) Empresa emp) {
		return empresaService.insert(emp);
	}
	
	@PutMapping
	public void update(Empresa emp) {
		empresaService.update(emp);
	}
	
	@DeleteMapping
	public void delete(Empresa emp) {
		empresaService.delete(emp);
	}
}
