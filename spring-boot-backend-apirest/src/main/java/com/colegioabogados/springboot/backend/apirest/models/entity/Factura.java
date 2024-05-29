package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String serie;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(name = "numero_boleta",unique = true,nullable = false)
	private String numeroBoleta;
	

	@Column(name = "numero_transaccion")
	private String numeroTransaccion;
	
	@JsonIgnoreProperties(value = {"facturas","hibernateLazyInitializer","handler"}, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Colegiado colegiado;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(name = "forma_pago",nullable = false)
	private String formaPago;
	
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	
	private String resolucion;
	
	private String observacion;
	
	@Column(nullable = false)
	private String responsable;
	
	private String extornador;
	
	private String filial;
	
	//Tipo de factura
	//1: cuota
	//2: tramite
	//3: multa
	//4: curso
	@Column(nullable = false)
	private String tipo;
	
	//Estado de factura
	//0: no cancelado
	//1: cancelado
	private Boolean cancelado;
	
	private String razon;
	
	@Column(name = "fecha_hasta")
	@Temporal(TemporalType.DATE)
	private Date fechaHasta;
	
	private Double descuento=0.0;
	
	public static final long HOUR = 3600*1000; // in milli-seconds.
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;
	
	public Factura() {
		items = new ArrayList<>();
	}
	
	//---- Metodos getters, setters and others -----
	
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

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public Colegiado getColegiado() {
		return colegiado;
	}

	public void setColegiado(Colegiado colegiado) {
		this.colegiado = colegiado;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Boolean getCancelado() {
		return cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
	public Double getTotal() {
		Double total = 0.00;
		for(ItemFactura item: items) {
			total+=item.getImporte();
		}
		total = total - this.descuento;
		return total;
	}

	public String getExtornador() {
		return extornador;
	}

	public void setExtornador(String extornador) {
		this.extornador = extornador;
	}
	
	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}
	
	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	private static final long serialVersionUID = 1L;
}
