package com.colegioabogados.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Multa;
import com.colegioabogados.springboot.backend.apirest.models.entity.Tramite;

//extends CrudRepository<Tramite, Long>
public interface IMultaDao extends  CrudRepository<Multa,Long>{
	@Query("select t from Tramite t where t.tipo = ?1")
	public List<Tramite> findAllMultas(String term);
	
	@Query("select m from Multa m where m.tramiteid = ?1")
	public List<Multa> findAllMultasAsig(String term);
	
	@Query("select m from Multa m where m.colegiatura = ?1 and m.pagado = ?2")
	public List<Multa> findAllMultasDeColegiado(String term1,boolean term2);
	
	@Query(value="SELECT * FROM multas where colegiatura = ?1 AND pagado = false",nativeQuery = true)
	public List<Multa> listarMultasColegiado(String term);
	
	@Query(value="select t from Tramite t where t.tipo = 'multa'")
	public List<Tramite> listarMultasExternas();
}
