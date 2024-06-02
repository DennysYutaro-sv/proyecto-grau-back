package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Calendar;
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

@Entity
@Table(name = "boletas")
public class Boleta implements Serializable{
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean cancelado;
	
	private String suministro;
	
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	
	private String descripcion;
	
	private String extornador;
	
	private String forma;
	
	private Double monto;
	
	private String motivo;
	//Nombre cliente
	private String cliente;
	//Tipo de pago: mensualidad o tramite
	private String tipo;
	
	private Integer recibo;
	
	private String registrador;
	
	//Metodos setters, getters and others

	@PrePersist public void prePersist() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.HOUR, -5);
		Date date1 = cal.getTime();   
		this.fechaPago = date1;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public String getRegistrador() {
		return registrador;
	}

	public void setRegistrador(String registrador) {
		this.registrador = registrador;
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	
	
	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public String getSuministro() {
		return suministro;
	}

	public void setSuministro(String suministro) {
		this.suministro = suministro;
	}

	public String getExtornador() {
		return extornador;
	}

	public void setExtornador(String extornador) {
		this.extornador = extornador;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getRecibo() {
		return recibo;
	}

	public void setRecibo(Integer recibo) {
		this.recibo = recibo;
	}



	private static final long serialVersionUID = 1L;
}
