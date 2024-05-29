package com.colegioabogados.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Universidad;

public interface IUniversidadDao extends  CrudRepository<Universidad,Long> {

}
