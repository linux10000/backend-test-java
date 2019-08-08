package br.com.fcamara.provajava.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEICULO")
public class Veiculo {

	private Integer veinid;
	private String veicmarca;
	private String veicmodelo;
	private String veiccor;
	private String veicplaca;
	private Integer veintipo;
	
	public Veiculo() {
		
	}

	public Veiculo(Integer veinid, String veicmarca, String veicmodelo, String veiccor, String veicplaca,
			Integer veintipo) {
		super();
		this.veinid = veinid;
		this.veicmarca = veicmarca;
		this.veicmodelo = veicmodelo;
		this.veiccor = veiccor;
		this.veicplaca = veicplaca;
		this.veintipo = veintipo;
	}

	@Id
	public Integer getVeinid() {
		return veinid;
	}

	public void setVeinid(Integer veinid) {
		this.veinid = veinid;
	}

	public String getVeicmarca() {
		return veicmarca;
	}

	public void setVeicmarca(String veicmarca) {
		this.veicmarca = veicmarca;
	}

	public String getVeicmodelo() {
		return veicmodelo;
	}

	public void setVeicmodelo(String veicmodelo) {
		this.veicmodelo = veicmodelo;
	}

	public String getVeiccor() {
		return veiccor;
	}

	public void setVeiccor(String veiccor) {
		this.veiccor = veiccor;
	}

	public String getVeicplaca() {
		return veicplaca;
	}

	public void setVeicplaca(String veicplaca) {
		this.veicplaca = veicplaca;
	}

	public Integer getVeintipo() {
		return veintipo;
	}

	public void setVeintipo(Integer veintipo) {
		this.veintipo = veintipo;
	}
}
