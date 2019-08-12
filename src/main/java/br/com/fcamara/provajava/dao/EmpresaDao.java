package br.com.fcamara.provajava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fcamara.provajava.pojo.Empresa;

@Repository
public interface EmpresaDao extends JpaRepository<Empresa, Integer> {

	@Query(" Select count(emp) from Empresa emp where emp.empccnpj = :empccnpj ")
	Integer countByEmpccnpj(String empccnpj);
}
