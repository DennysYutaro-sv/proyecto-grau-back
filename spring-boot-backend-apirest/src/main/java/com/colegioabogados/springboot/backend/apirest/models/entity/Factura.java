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
	@Column(name = "numero_boleta",nullable = true)
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
	
	//Nuevos parametros para municipalidad
	private Double deuda=0.0;
	private Double deudaAgua=0.0;
	private Double deudaLimpieza=0.0;
	//Estado 0 sin pagar 1 pagado
	private Boolean estado;
	private String fechaEmision;
	private String fechaUltima;
	private String forma;
	private Boolean fraccionamiento;
	private Double montoFraccionamiento;
	private Double montoMensual;
	private Double montoTotal;
	
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
	
	

	public Double getDeuda() {
		return deuda;
	}

	public void setDeuda(Double deuda) {
		this.deuda = deuda;
	}

	public Double getDeudaAgua() {
		return deudaAgua;
	}

	public void setDeudaAgua(Double deudaAgua) {
		this.deudaAgua = deudaAgua;
	}

	public Double getDeudaLimpieza() {
		return deudaLimpieza;
	}

	public void setDeudaLimpieza(Double deudaLimpieza) {
		this.deudaLimpieza = deudaLimpieza;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaUltima() {
		return fechaUltima;
	}

	public void setFechaUltima(String fechaUltima) {
		this.fechaUltima = fechaUltima;
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public Boolean getFraccionamiento() {
		return fraccionamiento;
	}

	public void setFraccionamiento(Boolean fraccionamiento) {
		this.fraccionamiento = fraccionamiento;
	}

	public Double getMontoFraccionamiento() {
		return montoFraccionamiento;
	}

	public void setMontoFraccionamiento(Double montoFraccionamiento) {
		this.montoFraccionamiento = montoFraccionamiento;
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
