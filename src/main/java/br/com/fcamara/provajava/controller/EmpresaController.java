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

import br.com.fcamara.provajava.dao.EmpresaDao;
import br.com.fcamara.provajava.exception.EmpresaExistException;
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
	
	@PostMapping()
	public Empresa insert(@RequestBody(required = false) Empresa emp) throws EmpresaExistException {
		return empresaService.insert(emp);
	}
	
	@PutMapping
	public Empresa update(@RequestBody(required = false) Empresa emp) {
		return empresaService.update(emp);
	}
	
	@DeleteMapping
	public void delete(@RequestParam Map<String, Object> params) {
		empresaService.delete(Ints.tryParse((String) Optional.ofNullable(params.get("empnid")).orElse("")));
	}
}
