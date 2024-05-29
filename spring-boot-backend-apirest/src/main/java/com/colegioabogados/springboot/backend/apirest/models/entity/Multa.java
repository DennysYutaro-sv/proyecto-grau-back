package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "multas")
public class Multa implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String colegiatura;
	
	@Column(nullable = false)
	private String nombre;
	
	//---- Datos de la multa ----
	@Column(nullable = false)
	private String tramiteid;
	
	//no pagado:0, pagado:1
	@Column(nullable = false)
	private boolean pagado;
	
	//no cancelado:0, cancelado:1
	@Column(nullable = false)
	private boolean cancelado;
	/*
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private Double precio;
	
	//tipo:multa
	@Column(nullable = false)
	private String tipo;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	*/
	//----------------------------
	
	
	
	//---- Metodos getters, setters and others -----
	/*
	@PrePersist public void prePersist() {
		this.createAt = new Date();
	}
	*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColegiatura() {
		return colegiatura;
	}

	public void setColegiatura(String colegiatura) {
		this.colegiatura = colegiatura;
	}

	public String getTramiteid() {
		return tramiteid;
	}

	public void setTramiteid(String tramiteid) {
		this.tramiteid = tramiteid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	*/
	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	private static final long serialVersionUID = 1L;
}
