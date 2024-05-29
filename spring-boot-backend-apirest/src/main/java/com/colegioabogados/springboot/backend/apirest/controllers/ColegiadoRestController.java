package com.colegioabogados.springboot.backend.apirest.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.colegioabogados.springboot.backend.apirest.dto.Mensaje;
import com.colegioabogados.springboot.backend.apirest.models.entity.Agremiado;
import com.colegioabogados.springboot.backend.apirest.models.entity.Colegiado;
import com.colegioabogados.springboot.backend.apirest.models.entity.Habilidad;
import com.colegioabogados.springboot.backend.apirest.models.entity.Imagen;
import com.colegioabogados.springboot.backend.apirest.models.entity.Multa;
import com.colegioabogados.springboot.backend.apirest.models.entity.Role;
import com.colegioabogados.springboot.backend.apirest.models.entity.Sistema;
import com.colegioabogados.springboot.backend.apirest.models.entity.Tramite;
import com.colegioabogados.springboot.backend.apirest.models.entity.Universidad;
import com.colegioabogados.springboot.backend.apirest.models.entity.Usuario;
import com.colegioabogados.springboot.backend.apirest.models.services.CloudinaryService;
import com.colegioabogados.springboot.backend.apirest.models.services.IColegiadoService;
import com.colegioabogados.springboot.backend.apirest.models.services.IUsuarioService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ColegiadoRestController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IColegiadoService colegiadoService;
	@Autowired
	private IUsuarioService usuarioService;
	//SVDY 15012023 Insertamos servicio para subir imagenes en cloudinary
	@Autowired
	CloudinaryService cloudinaryService;
	
	//Listar Usuarios
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/usuarios")
	public List<Usuario> usuarios(){
		return usuarioService.findAll();
	}
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario traerUsuario(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	//Crear usuario
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/usuario/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode((usuario.getPassword())));
		return usuarioService.save(usuario);
	}
	//Editar usuario
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario updateUsuario(@RequestBody Usuario usuario,@PathVariable Long id){
		Usuario usuarioActual = usuarioService.findById(id);
		try {
			
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setEnabled(usuario.getEnabled());
			usuarioActual.setFilial(usuario.getFilial());
			usuarioActual.setDni(usuario.getDni());
			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setRoles(usuario.getRoles());
			
			return usuarioService.save(usuarioActual);
			
		} catch (DataAccessException e) {
			return null;
		}

	}
	
	//listar colegiados
	//@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/colegiados")
	public List<Colegiado> index(){
		return colegiadoService.findAll();
	}
	
	//--------------------- UNIVERSIDADES - -- - - - - - - - - -  -
	//listar universidades
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/universidades")
	public List<Universidad> listarUniversidades(){
		return colegiadoService.findAllUniversidades();
	}
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/universidades/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Universidad traerUniversidad(@PathVariable Long id) {
		return colegiadoService.findUniversidadById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/colegiados/universidades")
	@ResponseStatus(HttpStatus.CREATED)
	public Universidad crearUniversidad(@RequestBody Universidad universidad) {
		return colegiadoService.saveUniversidad(universidad);
	}
	
	//Editar universidad
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/colegiados/universidades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Universidad updateUniversidad(@RequestBody Universidad universidad,@PathVariable Long id){
		Universidad universidadActual = colegiadoService.findUniversidadById(id);
		try {
			universidadActual.setNombre(universidad.getNombre());
			universidadActual.setDescripcion(universidad.getDescripcion());
			
			return colegiadoService.saveUniversidad(universidadActual);
			
		} catch (DataAccessException e) {
			return null;
		}

	}
	
	//listar habilidades
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/habilidades")
	public List<Habilidad> listarHabilidades(){
		return colegiadoService.findAllHabilidades();
	}
	
	//buscar por dni
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/filtrar-colegiados-dni/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Colegiado> filtrarColegiadosByDni(@PathVariable String term){
		return colegiadoService.findColegiadoByDni(term);
	}
	
	//buscar por apellido
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/filtrar-colegiados-apellido/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Colegiado> filtrarColegiadosByApellido(@PathVariable String term){
		return colegiadoService.findColegiadoByApellido(term);
	}
	
	//buscar por colegiatura colegiados
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/filtrar-colegiados-colegiatura/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Colegiado> filtrarColegiadosByColegiatura(@PathVariable String term){
		return colegiadoService.findColegiadoByColegiatura(term);
	}
	
	//buscar por nombre
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/filtrar-colegiados-nombre/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Colegiado> filtrarColegiadosByNombre(@PathVariable String term){
		return colegiadoService.findColegiadoByNombre(term);
	}
	
	//Buscar un colegiado por colegiatura
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/reporte/{term}")
	@ResponseStatus(HttpStatus.OK)
	public Colegiado findColegiadoByColegiatura(@PathVariable String term){
		return colegiadoService.findColegiadoByCole(term);
	}
	
	//Ultimo colegiado
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/ultimo")
	@ResponseStatus(HttpStatus.OK)
	public Colegiado findTopByOrderByColegiaturaDesc(){
		return colegiadoService.findTopByOrderByColegiaturaDesc();
	}
	
	//Obtener un colegiado
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Colegiado colegiado =null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			colegiado = colegiadoService.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la consulta en la BD");
			response.put("Error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(colegiado == null) {
			response.put("Mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos, verifique!!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Colegiado>(colegiado, HttpStatus.OK);
	}
	
	//guardar colegiado
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("/colegiados")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Colegiado colegiado,BindingResult result){
		Colegiado colegiadoNew= null;
		Agremiado agremiadoNew= new Agremiado();
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {
						return "El campo " + err.getField()+" : "+err.getDefaultMessage();
					})
					.collect(Collectors.toList());
			response.put("Errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		try {
			colegiadoNew = colegiadoService.save(colegiado);
			agremiadoNew.setAcolegiatura(colegiado.getColegiatura());
			agremiadoNew.setUsurio("ICAC-"+colegiado.getColegiatura());
			agremiadoNew.setPassword(colegiado.getColegiatura()+"-"+colegiado.getDni());
			agremiadoNew = colegiadoService.saveAgremiado(agremiadoNew);
		} catch (DataAccessException e) {
			response.put("Mensaje","Error al realizar la inserción en la BD");
			response.put("Error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//si no hay errores
		response.put("Mensaje", "El nuevo colegiado ha sido creado con éxito!!");
		response.put("Colegiado", colegiadoNew);
		return new ResponseEntity<Map>(response,HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	//Editar colegiado
	@PutMapping("/colegiados/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Colegiado colegiado, BindingResult result, @PathVariable Long id){
		Colegiado colegiadoActual = colegiadoService.findById(id);
		Colegiado colegiadoActualizado = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> {
						return "El campo " + err.getField() + " : " + err.getDefaultMessage();
					})
					.collect(Collectors.toList());
			response.put("Errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		if(colegiadoActual == null) {
			response.put("Mensaje", "Error : No se puedo editar, el colegiado ID: ".concat(id.toString().concat(" no existe en nuestra base de datos!!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			colegiadoActual.setColegiatura(colegiado.getColegiatura());
			colegiadoActual.setApellido(colegiado.getApellido());
			colegiadoActual.setNombre(colegiado.getNombre());
			colegiadoActual.setDni(colegiado.getDni());
			colegiadoActual.setNacimiento(colegiado.getNacimiento());
			colegiadoActual.setDepartamento(colegiado.getDepartamento());
			colegiadoActual.setProvincia(colegiado.getProvincia());
			colegiadoActual.setDistrito(colegiado.getDistrito());
			colegiadoActual.setDomicilio(colegiado.getDomicilio());
			colegiadoActual.setTrabajo(colegiado.getTrabajo());
			colegiadoActual.setUniversidad(colegiado.getUniversidad());
			colegiadoActual.setTelefono(colegiado.getTelefono());
			colegiadoActual.setCorreo(colegiado.getCorreo());
			colegiadoActual.setHabilidad(colegiado.getHabilidad());
			colegiadoActual.setFechaFallecimiento(colegiado.getFechaFallecimiento());
			colegiadoActual.setFechaColegiatura(colegiado.getFechaColegiatura());
			colegiadoActual.setLm(colegiado.getLm());
			colegiadoActual.setSexo(colegiado.getSexo());
			colegiadoActual.setTelefono2(colegiado.getTelefono2());
			colegiadoActual.setImagenId(colegiado.getImagenId());
			colegiadoActual.setEspecialidad(colegiado.getEspecialidad());
			colegiadoActual.setOtros(colegiado.getOtros());
			colegiadoActual.setActualizador(colegiado.getActualizador());
			
			colegiadoActualizado = colegiadoService.save(colegiadoActual);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar la actualización en la BD");
			response.put("Error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "El colegiado ha sido actualizado con éxito!!");
		response.put("Colegiado", colegiadoActualizado);
		
		return new ResponseEntity<Map>(response,HttpStatus.CREATED);
	}
	
	/*Obtener cumpleañeros*/
	//@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping("/colegiados/cumple")
	public List<Colegiado> listarCumples(){
		return colegiadoService.findColegiadoCumple();
	}
	
	/*Obtener cumpleañeros*/
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/colegiados/recientes")
	public List<Colegiado> listarColegiadosRecientes(){
		return colegiadoService.findColegiadosUltimos();
	}
	/*SVDY 22012023 Obtener imagen*/
	@GetMapping("/upload/{term}")
	@ResponseStatus(HttpStatus.OK)
	public Imagen obtenerImagen(@PathVariable String term){
		return colegiadoService.findImagenByColegiatura(term);
	}
	/*Subir imagenes en cloudinary*/
	@PostMapping("/upload/{term}")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile, @PathVariable String term) throws IOException{
		//Validamos que el archivo a subir sea imagen
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if(bi==null) {
			return new  ResponseEntity(new Mensaje("YAWAR TECH: Imagen no válida."),HttpStatus.OK);
		}
		Map result = cloudinaryService.upload(multipartFile);
		Imagen imagen = 
				new Imagen((String)result.get("original_filename"),
						(String)result.get("url"),
						(String)result.get("public_id"));
		imagen.setColegiadoId(term);
		colegiadoService.saveImagen(imagen);
		//return new  ResponseEntity(new Mensaje("Imagen subida correctamente,"),HttpStatus.OK);
		return new  ResponseEntity(new Mensaje("Imagen subida correctamente,"),HttpStatus.OK);
	}
	/*Eliminar imagenes en cloudinary*/
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException{
		if(!colegiadoService.existImagen(id))
			return new ResponseEntity(new Mensaje("Imagen no existe"),HttpStatus.NOT_FOUND);
		Imagen imagen = colegiadoService.findImagenById(id);
		
		Map result = cloudinaryService.delete(imagen.getCloudinaryId());
		colegiadoService.deleteImagen(id);
		//colegiadoService.de
		return new  ResponseEntity(new Mensaje("Imagen eliminada."),HttpStatus.OK);
	}
	/*SVDY 02022023 Liste de colegiados por habilidad*/
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/reportes/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> listarColegiadosHabilidad(@PathVariable Long term){
		return colegiadoService.findColegiadoHabilidad(term);
	}
	/*SVDY 24022023 Liste de colegiados habilitados-activos*/
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/reportes-activos")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> listarColegiadosActivos(){
		return colegiadoService.ColegiadoHabilitadoActivo();
	}
	/*SVDY 24022023 Liste de cumpleñaos por fecha*/
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/reportes-cumpleanios/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> listarCumpleaniosFecha(@PathVariable String term){
		return colegiadoService.ColegiadoCumpleFecha(term);
	}
	/*SVDY 16042023 Obtiene credenciales del agremiado*/
	@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_CAJA","ROLE_PARTES","ROLE_DECANO"})
	@GetMapping("/agremiado/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> obtenerCredencialesAgremiado(@PathVariable String term){
		return colegiadoService.DatosAgremiado(term);
	}
	/* --------------------------- APIS EXTERNAS -------------------------*/
	//Obtener licencia del software
	@GetMapping("/sistema")
	@ResponseStatus(HttpStatus.OK)
	public List<Sistema> obtenerLicencia(){
		return colegiadoService.obtenerMensajeSistema();
	}
	
	//Buscar colegiado por colegiatura
	@GetMapping("/dxy1spv0/{term}")
	@ResponseStatus(HttpStatus.OK)
	public Colegiado BuscarColegiadoByColegiatura(@PathVariable String term){
		return colegiadoService.findColegiadoByColegiaturaExterno(term);
	}
	
	//Buscar colegiado por nombre y apellido
	@GetMapping("/d2y3sñvx/{term1}/{term2}")
	@ResponseStatus(HttpStatus.OK)
	public Colegiado BuscarColegiadoByNombreApellido(@PathVariable String term1,@PathVariable String term2 ){
		return colegiadoService.findColegiadoByNombreApellidoExterno(term1, term2);
	}
	//Buscar multas por colegiatura
	@GetMapping("/m7rf56dfgfv86yigk/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Multa> ListarMultasColegiado(@PathVariable String term){
		return colegiadoService.listarMultasColegiado(term);
	}
	//Listar de multas
	@GetMapping("/nd56y67igh7drstrt")
	@ResponseStatus(HttpStatus.OK)
	public List<Tramite> ListarNombresMultass(){
		return colegiadoService.listarNombresMultas();
	}
	//Obtener datos de agremido por colegiatura
	@GetMapping("/obtener-colegiado-y4w4r/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> obtenerDatosAgremiado(@PathVariable String term){
		return colegiadoService.obtenerDatoscolegiado(term);
	}
	//Obtener cursos de agremido por colegiatura
	@GetMapping("/obtener-cursos-y4w4r/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> obtenerCursosAgremiado(@PathVariable String term){
		return colegiadoService.obtenerCursosColegiado(term);
	}
	//Editar colegiado Externo
	@PutMapping("/actualizar-colegiado-y4w4r/{term}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateColegiadoExt(@RequestBody Colegiado colegiado,@PathVariable String term){
		Colegiado colegiadoActual = colegiadoService.findColegiadoByColegiaturaExterno(term);
		try {
			colegiadoActual.setCorreo(colegiado.getCorreo());
			colegiadoActual.setDomicilio(colegiado.getDomicilio());
			colegiadoActual.setTrabajo(colegiado.getTrabajo());
			colegiadoActual.setTelefono2(colegiado.getTelefono2());
			colegiadoService.save(colegiadoActual);
			return new  ResponseEntity(new Mensaje("Sus datos se actualizarón correctamente."),HttpStatus.OK);
			
		} catch (DataAccessException e) {
			return null;
		}
	}
	//Obtener cursos de agremido por colegiatura
	@GetMapping("/obtener-habilidad-y4w4r/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Object[]> obtenerHabilidadAgremiado(@PathVariable String term){
		return colegiadoService.obtenerHabilidadColegiado(term);
	}
}












