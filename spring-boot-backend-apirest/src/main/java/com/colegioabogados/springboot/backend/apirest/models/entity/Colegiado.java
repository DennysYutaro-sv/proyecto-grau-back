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
	@Column(nullable = false)
	private String apellido;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2)
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 8,max = 8,message = "debe contener 8 numerós")
	@Column(unique = true,nullable = false)
	private String dni;
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date nacimiento;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String departamento;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String provincia;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String distrito;

	private String domicilio;
	
	private String trabajo;
	
	@NotNull(message = "no puede estar vacío")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="universidad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Universidad universidad;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable = false)
	private String telefono;
	
	@Email(message = "no es dirección de correo con formato correcto")
	private String correo;
	
	@NotNull(message = "no puede estar vacío")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="habilidad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Habilidad habilidad;
	
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
	

	private String telefono2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="especialidad_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Especialidad especialidad;
	
	private String otros;
	//15012023 subir imagen a clodinary
	private String imagenId;
	
	private String actualizador;
	
	
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
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

	public Universidad getUniversidad() {
		return universidad;
	}

	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}

	public Habilidad getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
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
	
	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getImagenId() {
		return imagenId;
	}

	public void setImagenId(String imagenId) {
		this.imagenId = imagenId;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
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

	private static final long serialVersionUID = 1L;
}