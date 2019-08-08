package br.com.fcamara.provajava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fcamara.provajava.pojo.MovVeiculo;

@Repository
public interface MovVeiculoDao extends JpaRepository<MovVeiculo, Integer> {

}
