package com.colegioabogados.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Tramite;

public interface ITramiteDao extends CrudRepository<Tramite, Long>{
	
	@Query("select t from Tramite t where t.nombre like ?1% and t.tipo = 'tramite'")
	public List<Tramite> findByNombre(String term);
	
	@Query("select t from Tramite t where t.nombre like ?1% and t.tipo = 'curso' and t.cancelado = 1")
	public List<Tramite> findByCurso(String term);
	
}
