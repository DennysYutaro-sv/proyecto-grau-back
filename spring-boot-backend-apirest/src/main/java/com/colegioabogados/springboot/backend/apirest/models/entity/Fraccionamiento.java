package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fraccionamientos")
public class Fraccionamiento implements Serializable{
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//0: no 1: si
	private boolean cancelado;
	
	private Double cuotas;
	
	private Double cuotasPendientes;
	
	private String idColegiado;
	
	private String idRecibo;
	
	private Double montoMensual;
	
	private Double montoTotal;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public boolean isCancelado() {
		return cancelado;
	}



	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}



	public Double getCuotas() {
		return cuotas;
	}



	public void setCuotas(Double cuotas) {
		this.cuotas = cuotas;
	}



	public Double getCuotasPendientes() {
		return cuotasPendientes;
	}



	public void setCuotasPendientes(Double cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}



	public String getIdColegiado() {
		return idColegiado;
	}



	public void setIdColegiado(String idColegiado) {
		this.idColegiado = idColegiado;
	}



	public String getIdRecibo() {
		return idRecibo;
	}



	public void setIdRecibo(String idRecibo) {
		this.idRecibo = idRecibo;
	}



	public Double getMontoMensual() {
		return montoMensual;
	}



	public void setMontoMensual(Double montoMensual) {
		this.montoMensual = montoMensual;
	}



	public Double getMontoTotal() {
		return montoTotal;
	}



	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}



	private static final long serialVersionUID = 1L;
}
