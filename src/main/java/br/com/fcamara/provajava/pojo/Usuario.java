package br.com.fcamara.provajava.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	private Integer usonid;
	private String usocnome;
	private String usoclogin;
	private String usocsenha;
	
	public Usuario() {
		
	}

	public Usuario(Integer usonid, String usocnome, String usoclogin, String usocsenha) {
		super();
		this.usonid = usonid;
		this.usocnome = usocnome;
		this.usoclogin = usoclogin;
		this.usocsenha = usocsenha;
	}

	@Id
	public Integer getUsonid() {
		return usonid;
	}

	public void setUsonid(Integer usonid) {
		this.usonid = usonid;
	}

	public String getUsocnome() {
		return usocnome;
	}

	public void setUsocnome(String usocnome) {
		this.usocnome = usocnome;
	}

	public String getUsoclogin() {
		return usoclogin;
	}

	public void setUsoclogin(String usoclogin) {
		this.usoclogin = usoclogin;
	}

	public String getUsocsenha() {
		return usocsenha;
	}

	public void setUsocsenha(String usocsenha) {
		this.usocsenha = usocsenha;
	}
}
