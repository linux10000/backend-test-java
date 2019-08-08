package br.com.fcamara.provajava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fcamara.provajava.pojo.Veiculo;

@Repository
public interface VeiculoDao extends JpaRepository<Veiculo, Integer> {

}
