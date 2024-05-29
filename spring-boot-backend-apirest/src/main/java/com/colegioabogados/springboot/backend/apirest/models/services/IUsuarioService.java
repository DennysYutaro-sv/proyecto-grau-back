package com.colegioabogados.springboot.backend.apirest.models.services;

import java.util.List;

import com.colegioabogados.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	public List<Usuario> findAll();
	//Servicio para buscar un usuario por id
	public Usuario findById(Long id);
	//Servicio para guardar usaurio
	public Usuario save(Usuario usuario);
}
