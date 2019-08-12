package br.com.fcamara.provajava.pojo;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MOV_VEICULO")
public class MovVeiculo {

	private Integer movnid;
	private Integer movnvei;
	private Integer movnemp;
	private OffsetDateTime movdentrada;
	private OffsetDateTime movdsaida;
	
	public MovVeiculo() {
		
	}

	public MovVeiculo(Integer movnid, Integer movnvei, Integer movnemp, OffsetDateTime movdentrada,
			OffsetDateTime movdsaida) {
		super();
		this.movnid = movnid;
		this.movnvei = movnvei;
		this.movnemp = movnemp;
		this.movdentrada = movdentrada;
		this.movdsaida = movdsaida;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getMovnid() {
		return movnid;
	}

	public void setMovnid(Integer movnid) {
		this.movnid = movnid;
	}

	public Integer getMovnvei() {
		return movnvei;
	}

	public void setMovnvei(Integer movnvei) {
		this.movnvei = movnvei;
	}

	public Integer getMovnemp() {
		return movnemp;
	}

	public void setMovnemp(Integer movnemp) {
		this.movnemp = movnemp;
	}

	public OffsetDateTime getMovdentrada() {
		return movdentrada;
	}

	public void setMovdentrada(OffsetDateTime movdentrada) {
		this.movdentrada = movdentrada;
	}

	public OffsetDateTime getMovdsaida() {
		return movdsaida;
	}

	public void setMovdsaida(OffsetDateTime movdsaida) {
		this.movdsaida = movdsaida;
	}
}
