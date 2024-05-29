package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="agremiados")
public class Agremiado  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=20)
	private String usurio;
	
	@Column(unique = true,nullable = false)
	private String acolegiatura;
	
	@Column(nullable = false)
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsurio() {
		return usurio;
	}

	public void setUsurio(String usurio) {
		this.usurio = usurio;
	}

	public String getAcolegiatura() {
		return acolegiatura;
	}

	public void setAcolegiatura(String acolegiatura) {
		this.acolegiatura = acolegiatura;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private static final long serialVersionUID = 1L;
}
