package com.colegioabogados.springboot.backend.apirest.models.services;

import java.util.List;

import com.colegioabogados.springboot.backend.apirest.models.entity.Agremiado;
import com.colegioabogados.springboot.backend.apirest.models.entity.Colegiado;
import com.colegioabogados.springboot.backend.apirest.models.entity.Factura;
import com.colegioabogados.springboot.backend.apirest.models.entity.Habilidad;
import com.colegioabogados.springboot.backend.apirest.models.entity.Imagen;
import com.colegioabogados.springboot.backend.apirest.models.entity.Multa;
import com.colegioabogados.springboot.backend.apirest.models.entity.Sistema;
import com.colegioabogados.springboot.backend.apirest.models.entity.Tramite;
import com.colegioabogados.springboot.backend.apirest.models.entity.Universidad;

public interface IColegiadoService {
	
	//Servicio para listar colegiados
	public List<Colegiado> findAll();
	
	//Servicio para buscar un colegiado por id
	public Colegiado findById(Long id);
	
	//Servicio para guardar colegiado
	public Colegiado save(Colegiado colegiado);
	
	// ----   UNIVERSIDADES ----
	//Servicio para listar universidades
	public List<Universidad> findAllUniversidades();
	
	public Universidad findUniversidadById(Long id);
	
	public Universidad saveUniversidad(Universidad universidad);
	
	//Servicio para listar habilidades
	public List<Habilidad> findAllHabilidades();
	
	//Servicio para buscar por dni
	public List<Colegiado> findColegiadoByDni(String term);
	
	//Servicio para buscar por apellido
	public List<Colegiado> findColegiadoByApellido(String term);
		
	//Servicio para buscar por colegiatura
	public List<Colegiado> findColegiadoByColegiatura(String term);
	
	//Servicio para traer colegiados del ultimo año
	public List<Colegiado> findColegiadosUltimos();
	
	//Traer un colegiado por colegiatura
	public Colegiado findColegiadoByCole(String term);
	
	//Servicio para buscar por nombre
	public List<Colegiado> findColegiadoByNombre(String term);
	
	//Generar codigo de colegiatura
	public Colegiado findTopByOrderByColegiaturaDesc(); 
	
	//Factura
	//Servicio para buscar factura por id
	public Factura findFacturaById(Long id);
	
	//Servicio para crear factura
	public Factura saveFactura(Factura factura);
	
	//Servicio para eliminar factura
	public void deleteFacturaById(Long id);
	
	//Servicio para listar facturas del día
	public List<Factura> listarFacturasPorDia(String term1,String term2);
	
	//Serivicio para validar el nro de boleta sea unica 0 nro de boleta valida
	public Long verficarNumeroBoleta(String term);
	
	//SVDY 08012023 Validar que factuta tipo cuota sea la ultima sin extornar
	public Long verficarBoletaExtorno(String term);
	
	//SVDY 25022023 Obtener ultimo id de la factura
	public Long obtenerUltimaIdFactura();
	
	//SVDY 25022023 Obtener ultimo id de la factura
	public Factura obtenerFacturaPorNroBoleta(String term);
	
	//Tramite
	//Servicio para buscar tramite por id
	public Tramite findTramiteById(Long id);
	
	//Servicio para buscar un tramite
	public List<Tramite> findTramiteByNombre(String term);
	
	//SVDY 11012023 Servicio para buscar un curso
		public List<Tramite> findTramiteByCurso(String term);
	
	//Servicio para crear tramite
	public Tramite saveTramite(Tramite tramite);

	//Servicio para listar tramites
	public List<Tramite> findAllTramites();
	
	//MULTAS
	//Servicio para buscar una multa por id
	public Multa findMultaById(Long id);
	//Servicio para crear multa
	public Multa saveMulta(Multa multa);
	//Servicio para traer tramites tipo multa
	public List<Tramite> findAllMultas(String term);
	//Servicio para traer una multa asignada a varios colegiados
	public List<Multa> findAllMultasAsig(String term);
	//Servicio para traer todas las multas de un colegiado
	public List<Multa> findAllMultasDeColegiado(String term1,boolean term2);
	
	//Notificaciones de cumpleaños
	public List<Colegiado> findColegiadoCumple();
	
	//SVDY 16012023 Almacenar url de imagenes subidas
	public Imagen findImagenById(Long id);
	
	public Imagen findImagenByColegiatura(String term);
	
	public Imagen saveImagen(Imagen imagen);
	
	public void deleteImagen(Long id);
	
	public boolean existImagen(Long id);
	
	//SVDY 02022023 Servicio para listar habilidad colegiado
	public List<Object[]> findColegiadoHabilidad(Long term);
	
	//SVDY 24022023 Servicio para listar  colegiado habilitado-activo
	public List<Object[]> ColegiadoHabilitadoActivo();
	//SVDY 24022023 Servicio para listar  colegiado habilitado-activo
	public List<Object[]> ColegiadoCumpleFecha(String term);
	
	//SVDY 16042023 Servicio para traer datos del agremiado
	public List<Object[]> DatosAgremiado(String term);
	
	//Servicio para crear multa
	public Agremiado saveAgremiado(Agremiado agremiado);
	
	/*-------------- SERVICIOS EXTERNOS -----------------*/
	//Serivicio para la licencia de sistema
	public List<Sistema> obtenerMensajeSistema();
	
	//Servicio para obtener colegiado por colegiatura
	public Colegiado findColegiadoByColegiaturaExterno(String term);
	
	//Servicio para obtener colegiado por nombre y apellido
	public Colegiado findColegiadoByNombreApellidoExterno(String term1,String term2);
	
	//Servicio para obtener multas por colegiatura
	public List<Multa> listarMultasColegiado(String term);
	
	//Servicio para obtener nombres de multas
	public List<Tramite> listarNombresMultas();
	
	//Servicio para obtener datos del colegiado para el portal por colegiatura
	public List<Object[]> obtenerDatoscolegiado(String term);
	
	//Servicio para obtener cursos de colegiados para el portal por colegiatura
	public List<Object[]> obtenerCursosColegiado(String term);
	
	//Servicio para obtener cursos de colegiados para el portal por colegiatura
	public List<Object[]> obtenerHabilidadColegiado(String term);
}










