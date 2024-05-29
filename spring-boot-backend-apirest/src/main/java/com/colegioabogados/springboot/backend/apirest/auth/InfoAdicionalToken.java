package com.colegioabogados.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.colegioabogados.springboot.backend.apirest.models.entity.Usuario;
import com.colegioabogados.springboot.backend.apirest.models.services.IUsuarioService;

//Este para que funcione debe ser un componente de spring
//Y este componente hay que registrarlo en nuestro servidor de autorizacion
@Component
public class InfoAdicionalToken implements TokenEnhancer{
	//Aca inyectamos el servico de usarioservice para encontrar un usuario ´po su username
	@Autowired
	private IUsuarioService usuarioService;
	//Esta clase nos permitira agregar informacion adicional a nuestro token
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		//Aca utilizamos la inyeccion del servicio
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		//Aca agregamos
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal!".concat(authentication.getName()));
		
		//Agregamos la informacion del usario que traemos desde el servicio 
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getEmail());
		info.put("filial", usuario.getFilial());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	
}
