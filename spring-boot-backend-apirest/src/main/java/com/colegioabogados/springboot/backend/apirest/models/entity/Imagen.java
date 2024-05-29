package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imagenes")
public class Imagen implements Serializable{

	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String colegiadoId; 
	
	private String nombre;
	
	private String url;
	
	private String cloudinaryId;
	
	public Imagen() {
		
	}
	
	public Imagen(String nombre,String url,String cloudinaryId ) {
		this.nombre = nombre;
		this.url = url;
		this.cloudinaryId = cloudinaryId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColegiadoId() {
		return colegiadoId;
	}

	public void setColegiadoId(String colegiadoId) {
		this.colegiadoId = colegiadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCloudinaryId() {
		return cloudinaryId;
	}

	public void setCloudinaryId(String cloudinaryId) {
		this.cloudinaryId = cloudinaryId;
	}

	private static final long serialVersionUID = 1L;
}
