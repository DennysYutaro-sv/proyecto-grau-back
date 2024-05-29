package com.colegioabogados.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//Esta clase se encarga del proceso de autentificacion por el lado de ao2, crear el token validarlo todo lo relacionado
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	//Inyectamos el bean BCRy de la clase SpringSecurityConfig
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//inyetamos el autocation manager
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	//Aca intyectamos la informacion adicional del token
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	//implemntamos 3 metodos de confuguracion
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//add react vue
		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("12345"))
		//scopes le dara el permiso al usario de leer y escribirm un crud
		.scopes("read","write")
		//como obtendremos nuestro token si el usario exsite en nuestro backedn osea tiene que tener usario y contraseña
		.authorizedGrantTypes("password","refresh_token")
		//ahora tenemos que validar cuando tiempo servira el token
		.accessTokenValiditySeconds(28800)
		.refreshTokenValiditySeconds(28800);
		
	}
	
	//Este metodo se encarga del proceso de autotenticacion genera token, se entrega al usuario, etc
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//Aca registramso la informacion adicional a nuestro token
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		//convierte los datos del token para auentificar y datos del cliente
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		//modificamos la llave mac que es obligatorio para autentificacion con jwt
		//aCA FIRMAMOS CON NUESTRA LLAVE PRIVADA
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		//Y LA LLAVE PUBLICA VERIFICA
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}
}
