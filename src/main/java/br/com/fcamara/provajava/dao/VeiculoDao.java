package br.com.fcamara.provajava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fcamara.provajava.pojo.Veiculo;

@Repository
public interface VeiculoDao extends JpaRepository<Veiculo, Integer> {

	@Query(" Select count(vei) from Veiculo vei where vei.veicplaca = :veicplaca ")
	Integer countByVeicplaca(String veicplaca);
}
