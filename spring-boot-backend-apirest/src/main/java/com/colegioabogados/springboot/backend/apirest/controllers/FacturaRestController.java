package com.colegioabogados.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.colegioabogados.springboot.backend.apirest.models.entity.Factura;
import com.colegioabogados.springboot.backend.apirest.models.entity.Tramite;
import com.colegioabogados.springboot.backend.apirest.models.services.IColegiadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	@Autowired
	private IColegiadoService colegiadoService;
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return colegiadoService.findFacturaById(id);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/facturas/boleta/{term}")
	@ResponseStatus(HttpStatus.OK)
	public Factura obtenerFacturaPorBoleta(@PathVariable String term) {
		return colegiadoService.obtenerFacturaPorNroBoleta(term);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/facturas/filtrar-tramites/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Tramite> filtrarTramites(@PathVariable String term){
		return colegiadoService.findTramiteByNombre(term);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/facturas/filtrar-cursos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Tramite> filtrarCursos(@PathVariable String term){
		return colegiadoService.findTramiteByCurso(term);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/facturas/validar/{term}")
	@ResponseStatus(HttpStatus.OK)
	public Long validarNroFactura(@PathVariable String term){
		return colegiadoService.verficarNumeroBoleta(term);
	}
	
	//SVDY 08012023 Validar que factuta tipo cuota sea la ultima sin extornar
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/facturas/validar-extorno/{term}")
	@ResponseStatus(HttpStatus.OK)
	public Long validarNroExtorno(@PathVariable String term){
		return colegiadoService.verficarBoletaExtorno(term);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		Long n = colegiadoService.obtenerUltimaIdFactura()+1;
		factura.setNumeroBoleta(factura.getSerie()+"-"+n);
		return colegiadoService.saveFactura(factura);
	}
	//Cancelar factura actualizar estado
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura updateFactura(@RequestBody Factura factura,@PathVariable Long id){
		Factura facturaActual = colegiadoService.findFacturaById(id);
		try {
			facturaActual.setCancelado(true);
			facturaActual.setExtornador(factura.getExtornador());
			facturaActual.setRazon(factura.getRazon());
			//SVDY 07022023
			//facturaActual.setNumeroBoleta(factura.getNumeroBoleta());
			
			 return colegiadoService.saveFactura(facturaActual);
			
		} catch (DataAccessException e) {
			return null;
		}

	}
	
	//Listar facturas por dia
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_DECANO"})
	@GetMapping("/facturas/dia/{term1}/{term2}")
	@ResponseStatus(HttpStatus.OK)
	public List<Factura> listarFacturaPorDias(@PathVariable String term1,@PathVariable String term2) {
		return colegiadoService.listarFacturasPorDia(term1, term2);
	}

	//Tramite
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/tramites")
	@ResponseStatus(HttpStatus.OK)
	public List<Tramite> traerTramites() {
		return colegiadoService.findAllTramites();
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/tramites/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Tramite traer(@PathVariable Long id) {
		return colegiadoService.findTramiteById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/tramites")
	@ResponseStatus(HttpStatus.CREATED)
	public Tramite crearTramite(@RequestBody Tramite tramite) {
		return colegiadoService.saveTramite(tramite);
	}
	
	
	//Editar tramite
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/tramites/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Tramite updateTramite(@RequestBody Tramite tramite,@PathVariable Long id){
		Tramite tramiteActual = colegiadoService.findTramiteById(id);
		try {
			tramiteActual.setNombre(tramite.getNombre());
			tramiteActual.setDescripcion(tramite.getDescripcion());
			tramiteActual.setPrecio(tramite.getPrecio());
			tramiteActual.setCancelado(tramite.getCancelado());
			tramiteActual.setImagen(tramite.getImagen());
			tramiteActual.setPonente(tramite.getPonente());
			tramiteActual.setInicio(tramite.getInicio());
			tramiteActual.setFin(tramite.getFin());
			tramiteActual.setHorario(tramite.getHorario());
			tramiteActual.setDuracion(tramite.getDuracion());
			tramiteActual.setModalidad(tramite.getModalidad());
			
			 return colegiadoService.saveTramite(tramiteActual);
			
		} catch (DataAccessException e) {
			return null;
		}

	}
	/*  Mantenimiento multas   */
	
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/multas/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Tramite> traerMultas(@PathVariable String term) {
		return colegiadoService.findAllMultas(term);
	}

}
