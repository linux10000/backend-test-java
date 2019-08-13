package br.com.fcamara.provajava.dao;

import java.util.List;
import java.util.Map;

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
	
	@Query(value = " select emp.empnid, emp.empcnome, " +
			"count(mov.movdentrada) as entradas, " + 
			"count(mov.movdsaida) as saidas " + 
			" from mov_veiculo mov " + 
			"        ,empresa emp " + 
			"where emp.empnid = mov.movnemp " + 
			"group by emp.empnid, emp.empcnome", nativeQuery = true)
	List<Map<String, Object>> getGeneralSummary();
	
	//essa query deveria ser feito em uma stored procedure pela questao da performace
	@Query(value = " select a.movnemp, emp.empcnome, a.hora || 'h' as data, " + 
			"          ( select count(*) " + 
			"            from mov_veiculo me " + 
			"            where me.movnemp = a.movnemp " + 
			"                and  to_char(me.movdentrada, 'DD/MM/YYYY HH24') = a.hora " + 
			"          ) as entradas, " + 
			"          ( select count(*) " + 
			"            from mov_veiculo ms " + 
			"            where ms.movnemp = a.movnemp " + 
			"                and ms.movdsaida is not null " + 
			"                and  to_char(ms.movdsaida, 'DD/MM/YYYY HH24') = a.hora " + 
			"          ) as saidas " + 
			" " + 
			"from ( " + 
			"     select distinct to_char(mov.movdentrada, 'DD/MM/YYYY HH24') as hora, mov.movnemp " + 
			"     from mov_veiculo mov " + 
			"     union " + 
			"     select distinct to_char(mov.movdsaida, 'DD/MM/YYYY HH24') as hora, mov.movnemp " + 
			"     from mov_veiculo mov " + 
			"     where  mov.movdsaida is not null    " + 
			") a,  " + 
			"empresa emp " + 
			"where emp.empnid = a.movnemp " + 
			"order by a.movnemp, PARSEDATETIME(a.hora, 'DD/MM/YYYY HH') desc " + 
			"", nativeQuery = true)
	List<Map<String, Object>> getSummaryByHour();
}
