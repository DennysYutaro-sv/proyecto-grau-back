package com.colegioabogados.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colegioabogados.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.colegioabogados.springboot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService,UserDetailsService {

	//para tratamiento de errores si no esxite roles en el usuario
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
		
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
			//si el usario no existe 
			if (usuario ==null) {
				logger.error("Error en el login: no existe el usario '"+username+"' en el sistema!!");
				throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			}
			//Aca traemos la lista de roles del usario y tambien convertir los roles a tipo granteda
			List<GrantedAuthority> authorities = usuario.getRoles()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					//para mostrar el nombre dek rol
					.peek(authority -> logger.info("Role: "+authority.getAuthority()))
					.collect(Collectors.toList());
			//el metodo retorna el userdetails es un usario de srping security
			return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}
	//Listar usuarios
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	@Override
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}
	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}
}
