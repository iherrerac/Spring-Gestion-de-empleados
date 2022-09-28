package com.midominio.spring.seguridad;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/*
 * Autenticacion: Con este condigo yatenemos Requisitos de autenticacion en todas las URL, 
 * nos ha generado un Formulario de Login,Acceso a un usuario admin/admin,  permite hacer logout, prevencion de algunos ataques, 
 * integracion con la api Servlet (HttpServletRequest):@getRemoteUser,@getUserPrincipal,@isUserInRol,@login,@logout, etc 
 * ha registrado un filtro de servlet especialspringSecurityFilterChain (cadena de filtros responsable de la seguridad)
 */
/*
 * Autorizacion:
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//Autenticacion
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication() //autenticacion en memoria
			.passwordEncoder(NoOpPasswordEncoder.getInstance()) //Contraseña no cifradas
			.withUser("admin") //un solo usuario admin
			.password("admin")
			.roles("ADMIN"); //Rol Administrador
	}

	//Autorizacion
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http
			.authorizeRequests() //Para autorizar las peticiones
				.antMatchers("/webjars/**","/css/**").permitAll() //Expresiones helpup ( cadenas con comodin ) vamos a permitirlas siempre
				.anyRequest().authenticated() //Cualquier otra peticion tendra que venir autenticada
				.and()
		.formLogin()
			.loginPage("/login")
			.permitAll();
	}
	
	

}
