package com.colegioabogados.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "colegiados")
public class Colegiado implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Pattern(regexp = "[0-9]*",message="debe contener solo números")
	@Column(unique = true,nullable = false)
	private String colegiatura;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2)
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 6,max = 20,message = "debe contener de 6 a 20 carácteres")
	@Column(nullable = false)
	private String dni;
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date nacimiento;
	
	//@NotEmpty(message = "no puede estar vacío")
	//@Column(nullable = false)
	private String departamento;
	
	//@NotEmpty(message = "no puede estar vacío")
	//@Column(nullable = false)
	private String provincia;
	
	//@NotEmpty(message = "no puede estar vacío")
	//@Column(nullable = false)
	private String distrito;
	
	private String nroMedidor;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = true)
	private String telefono;
	
	@Email(message = "no es dirección de correo con formato correcto")
	@Null(message = "El correo no puede ser especificado")
	@Column(nullable = true)
	private String correo;
	
	@Column(name = "fecha_fallecimiento")
	//@Temporal(TemporalType.DATE)
	private String fechaFallecimiento;
	
	private String lm;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String sexo;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "fecha_colegiatura")
	@Temporal(TemporalType.DATE)
	private Date fechaColegiatura;
	
	@JsonIgnoreProperties(value = {"colegiado","hibernateLazyInitializer","handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colegiado", cascade = CascadeType.ALL)
	private List<Factura> facturas;
	
	private String otros;
	//15012023 subir imagen a clodinary
	private String imagenId;
	
	private String actualizador;
	
	//Nuevos parametros 
	private String agua;
	@Column(nullable = true)
	private Boolean estado;
	
	private String limpieza;
	
	private Boolean medidor;
	
	private String registrador;
	
	@NotNull(message = "no puede estar vacío")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="direccion_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Direccion direccion;

	public Colegiado() {
		this.facturas = new ArrayList<>();
	}
	
	//--------- Metodos setter, getters y otros-------------

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getLm() {
		return lm;
	}

	public void setLm(String lm) {
		this.lm = lm;
	}
	

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaColegiatura() {
		return fechaColegiatura;
	}

	public void setFechaColegiatura(Date fechaColegiatura) {
		this.fechaColegiatura = fechaColegiatura;
	}
	
	public String getFechaFallecimiento() {
		return fechaFallecimiento;
	}

	public void setFechaFallecimiento(String fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public String getImagenId() {
		return imagenId;
	}

	public void setImagenId(String imagenId) {
		this.imagenId = imagenId;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}
	
	public String getActualizador() {
		return actualizador;
	}

	public void setActualizador(String actualizador) {
		this.actualizador = actualizador;
	}
	
	

	public String getAgua() {
		return agua;
	}

	public void setAgua(String agua) {
		this.agua = agua;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getLimpieza() {
		return limpieza;
	}

	public void setLimpieza(String limpieza) {
		this.limpieza = limpieza;
	}

	public Boolean getMedidor() {
		return medidor;
	}

	public void setMedidor(Boolean medidor) {
		this.medidor = medidor;
	}

	public String getRegistrador() {
		return registrador;
	}

	public void setRegistrador(String registrador) {
		this.registrador = registrador;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public String getNroMedidor() {
		return nroMedidor;
	}

	public void setNroMedidor(String nroMedidor) {
		this.nroMedidor = nroMedidor;
	}

	private static final long serialVersionUID = 1L;
}
