package com.colegioabogados.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Direccion;

public interface IDireccionDao extends  CrudRepository<Direccion,Long>{

}
