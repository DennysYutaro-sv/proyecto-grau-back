package com.colegioabogados.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Colegiado;
import com.colegioabogados.springboot.backend.apirest.models.entity.Habilidad;
import com.colegioabogados.springboot.backend.apirest.models.entity.Sistema;
import com.colegioabogados.springboot.backend.apirest.models.entity.Universidad;

public interface IColegiadoDao extends CrudRepository<Colegiado, Long>{

	@Query("select c from Colegiado c where c.dni = ?1")
	public List<Colegiado> findByDni(String term);
	@Query(value="SELECT * FROM colegiados WHERE apellido LIKE ?1% LIMIT 15",nativeQuery = true)
	public List<Colegiado> findByApellido(String term);
	@Query(value="SELECT  * FROM colegiados WHERE colegiatura LIKE ?1% LIMIT 15",nativeQuery = true)
	public List<Colegiado> findByColegiatura(String term);
	@Query(value="SELECT * FROM colegiados WHERE nombre LIKE ?1% LIMIT 15",nativeQuery = true)
	public List<Colegiado> findByNombre(String term);
	
	//buscar un colegiado por colegiaruta
	@Query("select c from Colegiado c where c.colegiatura = ?1")
	public Colegiado findColegiadoByCole(String term);
	
	//Listar colegiados colegiados el ultimo año
	@Query(value="SELECT * FROM colegiados WHERE EXTRACT(YEAR FROM fecha_colegiatura) = EXTRACT(YEAR FROM CURDATE())",nativeQuery = true)
	public List<Colegiado> findColegiadosUltimos();
	
	@Query("from Universidad")
	public List<Universidad> findAllUniversidades();
	
	@Query("from Habilidad")
	public List<Habilidad> findAllHabilidades();
	
	@Query(value = "SELECT * FROM colegiados WHERE id = (SELECT MAX(id) FROM colegiados)", nativeQuery = true)
	public Colegiado findLastColegiado();
	
	//Notificacion de cumpleañose
	@Query(value="SELECT * FROM colegiados WHERE DAY(nacimiento)=DAY(NOW()) AND MONTH(nacimiento)=MONTH(NOW())", nativeQuery = true)
	public List<Colegiado> findColegiadoCumple();
	
	//Listar cumpleaños por fecha
	@Query(value="SELECT colegiatura,nombre,apellido,habilidad_id,nacimiento  FROM colegiados WHERE DAY(nacimiento)=DAY(?1) AND MONTH(nacimiento)=MONTH(?1);", nativeQuery = true)
	public List<Object[]> findCumpleFecha(String term);
	
	/*-----------------RUTAS LIBRES PARA CONSUMIR DESDE EL FRONT LIBRE--------------------*/
	//Deshabilitar Sistema
	@Query("from Sistema")
	public List<Sistema> traerSistema();
	
	//Buscar Colegiado por colegiatura
	@Query(value="SELECT * FROM colegiados WHERE colegiatura=?1", nativeQuery = true)
	public Colegiado findColegiadoByColegiaturaExterno(String term);
	
	//Buscar Colegiado por nombre y apellido
	@Query(value="SELECT * FROM colegiados WHERE nombre=?1 and apellido=?2", nativeQuery = true)
	public Colegiado findColegiadoByNombreApellidoExterno(String term1,String term2);
	
	/*
	@Query("select u from User u where u.lastName=?1 and u.email=?2")
	User testQueryAnnotationParams1(String lastName,String email);
	 */
	//Reportes habilidades
	@Query(value="SELECT  colegiatura,nombre,apellido,habilidad_id FROM colegiados WHERE habilidad_id =?1", nativeQuery = true)
	public List<Object[]> ColegiadoHabilidad(Long term);
	
	//Reporte de colegiados HABILITADOS-ACTIVOS
	@Query(value="SELECT DISTINCT c.nombre,c.apellido,c.colegiatura,'HABILITADO-ACTIVO' AS habilidad_id\r\n"
			+ "FROM facturas f INNER JOIN colegiados c ON (f.colegiado_id = c.id) \r\n"
			+ "	WHERE (c.habilidad_id=1 and f.tipo = 'cuota' AND f.fecha_hasta > LAST_DAY(DATE_SUB(NOW(),INTERVAL '3' MONTH)))\r\n"
			+ "UNION\r\n"
			+ "SELECT DISTINCT nombre, apellido,colegiatura,'HABILITADO-ACTIVO'  AS habilidad_id from colegiados\r\n"
			+ "	WHERE fecha_colegiatura > LAST_DAY(DATE_SUB(NOW(),INTERVAL '3' MONTH)) AND habilidad_id=1", nativeQuery = true)
	public List<Object[]> ColegiadoHabilitadoActivo();
	//Obtener datos de colegiados en el portal de agremiados
	@Query(value="SELECT colegiatura,c.nombre,apellido,correo,domicilio,trabajo,telefono2,c.habilidad_id,I.url FROM colegiados c\r\n"
			+ "	LEFT JOIN imagenes i ON (c.colegiatura = i.colegiado_id)\r\n"
			+ "	WHERE c.colegiatura = ?1", nativeQuery = true)
	public List<Object[]> AgremiadoDatos(String term);
	//Obtener datos de cursos por colegiado en el portalde colegiado
	@Query(value="SELECT c.colegiatura,t.nombre,t.descripcion,t.imagen,t.horario,t.duracion,t.inicio,t.fin,t.ponente,t.modalidad \r\n"
			+ "	FROM colegiados c\r\n"
			+ "	inner JOIN FACTURAS F ON (c.id = F.colegiado_id)\r\n"
			+ "	inner JOIN facturas_items I ON (I.factura_id = F.id)\r\n"
			+ "	INNER JOIN tramites t ON(t.id = i.tramite_id)\r\n"
			+ "	WHERE F.tipo = 'curso' AND c.colegiatura = ?1", nativeQuery = true)
	public List<Object[]> AgremiadoCursos(String term);
	//Obtener estado de Habilidad en el portal de colegiado
	@Query(value="SELECT fecha_colegiatura AS fecha_hasta,habilidad_id\r\n"
			+ "FROM colegiados WHERE colegiatura=?1\r\n"
			+ "UNION\r\n"
			+ "SELECT fecha_hasta,habilidad_id\r\n"
			+ "FROM facturas F INNER JOIN colegiados C ON (C.ID=F.COLEGIADO_ID) \r\n"
			+ "WHERE c.colegiatura=?1 AND tipo = 'cuota' ORDER BY fecha_hasta DESC LIMIT 1", nativeQuery = true)
	public List<Object[]> AgremiadoHabilidad(String term);
}