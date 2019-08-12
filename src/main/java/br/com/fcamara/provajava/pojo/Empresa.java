package br.com.fcamara.provajava.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "empresa")
public class Empresa {

	private Integer empnid;
	private String empcnome;
	private String empccnpj;
	private String empcendereco;
	private String empctelefone;
	private Integer empnvagacarros;
	private Integer empnvagamotos;
	
	public Empresa() {
		
	}

	public Empresa(Integer empnid, String empcnome, String empccnpj, String empcendereco, String empctelefone, Integer empnvagacarros,
			Integer empnvagamotos) {
		super();
		this.empnid = empnid;
		this.empcnome = empcnome;
		this.empccnpj = empccnpj;
		this.empcendereco = empcendereco;
		this.empctelefone = empctelefone;
		this.empnvagacarros = empnvagacarros;
		this.empnvagamotos = empnvagamotos;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getEmpnid() {
		return empnid;
	}

	public void setEmpnid(Integer empnid) {
		this.empnid = empnid;
	}

	public String getEmpcnome() {
		return empcnome;
	}

	public void setEmpcnome(String empcnome) {
		this.empcnome = empcnome;
	}

	public String getEmpcendereco() {
		return empcendereco;
	}

	public void setEmpcendereco(String empcendereco) {
		this.empcendereco = empcendereco;
	}

	public String getEmpctelefone() {
		return empctelefone;
	}

	public void setEmpctelefone(String empctelefone) {
		this.empctelefone = empctelefone;
	}

	public Integer getEmpnvagacarros() {
		return empnvagacarros;
	}

	public void setEmpnvagacarros(Integer empnvagacarros) {
		this.empnvagacarros = empnvagacarros;
	}

	public Integer getEmpnvagamotos() {
		return empnvagamotos;
	}

	public void setEmpnvagamotos(Integer empnvagamotos) {
		this.empnvagamotos = empnvagamotos;
	}

	public String getEmpccnpj() {
		return empccnpj;
	}

	public void setEmpccnpj(String empccnpj) {
		this.empccnpj = empccnpj;
	}
}
