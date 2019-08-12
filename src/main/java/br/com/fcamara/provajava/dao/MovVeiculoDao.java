package br.com.fcamara.provajava.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fcamara.provajava.pojo.MovVeiculo;

@Repository
public interface MovVeiculoDao extends JpaRepository<MovVeiculo, Integer> {

	@Query(" Select mov from MovVeiculo mov " + 
			" where mov.movnemp = :empnid " + 
			"  and mov.movnvei = :veinid " + 
			"  and mov.movdsaida is null " +
			" order by mov.movdentrada desc ")
	List<MovVeiculo> findMissingExit(Integer veinid, Integer empnid, Pageable pageable);
	
	@Query(" Select count(mov) from MovVeiculo mov, Veiculo vei " + 
			" where vei.veinid = mov.movnvei " +
			"  and mov.movnemp = :empnid " + 
			"  and mov.movdsaida is null " +
			"  and vei.veintipo = :tipo " )
	Integer countParkedVeiculosByTypeAndEmpresa(Integer empnid, Integer tipo);
}
