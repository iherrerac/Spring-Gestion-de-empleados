package com.midominio.spring.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * En vez de crear un controlador que nos lleve a nuestra plantilla de login customizada (algo al parecer simple)
 * vamos a crear una configuracion que nos enrute directamente a esa plantilla
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/login");
	}

	
}
