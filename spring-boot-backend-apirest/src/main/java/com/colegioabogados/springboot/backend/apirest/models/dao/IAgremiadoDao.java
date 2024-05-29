package com.colegioabogados.springboot.backend.apirest.models.dao;
import com.colegioabogados.springboot.backend.apirest.models.entity.Agremiado;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IAgremiadoDao extends CrudRepository<Agremiado, Long>{
	//Reportes habilidades
	@Query(value="SELECT c.colegiatura,c.nombre,c.apellido,c.correo,a.usurio,a.password FROM \r\n"
			+ "	agremiados a INNER JOIN colegiados c ON a.acolegiatura = c.colegiatura\r\n"
			+ "	WHERE a.acolegiatura = ?1", nativeQuery = true)
	public List<Object[]> ObtenerDatosAgremiado(String term);
}
