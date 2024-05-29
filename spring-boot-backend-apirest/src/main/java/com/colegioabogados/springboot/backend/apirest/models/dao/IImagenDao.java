package com.colegioabogados.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Imagen;

public interface IImagenDao extends  CrudRepository<Imagen,Long>{
	@Query("select c from Imagen c where c.colegiadoId = ?1")
	public Imagen findImagenByCole(String term);
}
