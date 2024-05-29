package com.colegioabogados.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//Estas cofiguraciones son por el lado de oauth  
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/colegiados").permitAll()
		.antMatchers(HttpMethod.GET,"/api/sistema").permitAll()
		.antMatchers(HttpMethod.POST,"/api/upload/{term}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/upload/{term}").permitAll()
		.antMatchers(HttpMethod.DELETE,"/api/delete/{id}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/dxy1spv0/{term}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/d2y3sñvx/{term1}/{term2}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/m7rf56dfgfv86yigk/{term}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/nd56y67igh7drstrt").permitAll()
		.antMatchers(HttpMethod.GET,"/api/obtener-colegiado-y4w4r/{term}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/obtener-cursos-y4w4r/{term}").permitAll()
		.antMatchers(HttpMethod.PUT,"/api/actualizar-colegiado-y4w4r/{term}").permitAll()
		.antMatchers(HttpMethod.GET,"/api/obtener-habilidad-y4w4r/{term}").permitAll()
		.anyRequest().authenticated()
		//Aca invocamos el metodo para configurar el cors
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	//ESte metodo es el cors de spirng security tenemos que configurar el cors es un mecanismo que utliza las cabeceras http los headers 
	//para permitir que un clinete que esta en otro dominio distinto de backend pueda utlizar estos recuros spirng security y -oauth2
	
	//Cors muyimportante para agregar que dominios consumiran nuestras apis
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		//Es estas lineas configuramos los dominias que podran consdumir nuestros servicos si colocamos * permitimos a todos condumir
		config.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200","*"));
		//En esta lina que tipo de peticiones podran hacer tamien puedes colocar * para todo
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowCredentials(true);
		//Aca permitimos las cabeceras o headers las cabeceras a enviar las cuales son Content-Type y Authorization
		config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		//Ahora tenemos que registrar el cors para todas las rutas de nuestro backend
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	//Este metodo filtrara los filtros para que se aplique en el -oauth y para validadr nuestros tokes este registra las configfuracioines del cors que hicimos
	//y lo coloca en el cors de springsecurity y -oauth
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
	