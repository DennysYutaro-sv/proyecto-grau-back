package com.colegioabogados.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.colegioabogados.springboot.backend.apirest.models.entity.Factura;

public interface IFacturaDao  extends CrudRepository<Factura, Long>{
	
	@Query("SELECT COUNT(f) FROM Factura f WHERE f.numeroBoleta=?1")
	public Long verficarNumeroBoleta(String term);
	@Query(value="SELECT * FROM facturas WHERE DATE(fecha_pago) = ?1 and cancelado = ?2",nativeQuery = true)
	public List<Factura> listarFacturasDia(String term1,String term2);
	//SVDY 08012023 Validar que factuta tipo cuota sea la ultima sin extornar
	@Query(value="SELECT * FROM FACTURAS WHERE colegiado_id = ?1 AND tipo = 'cuota' AND cancelado = 0 ORDER BY id DESC LIMIT 1",nativeQuery = true)
	public Long verficarBoletaExtorno(String term);
	@Query(value="SELECT MAX( id ) FROM facturas",nativeQuery = true)
	public Long obtenerUltimaIdFactura();
	//SVDY 25022023 Obtener boleta por nro de boleta
	@Query(value="SELECT * FROM facturas WHERE numero_boleta = ?1",nativeQuery = true)
	public Factura obtenerFacturaPorNroBoleta(String term);
}
