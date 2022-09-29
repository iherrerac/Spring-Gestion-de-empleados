package com.midominio.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.midominio.spring.repositorios.EmpleadoRepository;
import com.midominio.spring.entidades.Empleado;
import com.midominio.spring.upload.storage.StorageService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


/**
 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el
 * almacenamiento secundario del proyecto
 * 
 * @param storageService Almacenamiento secundario del proyecto
 * @return
 */
@Bean
CommandLineRunner init(StorageService storageService) {
	return (args) -> {
		storageService.deleteAll();
		storageService.init();
	};
}


@Bean
CommandLineRunner initData(EmpleadoRepository repositorio) {
	return (args) -> {

		Empleado empleado = new Empleado("Luis Miguel López", "luismi.lopez@openwebinars.net", "954000000");
		Empleado empleado2 = new Empleado("José García", "jose.garcia@openwebinars.net", "954000000");
		
		repositorio.save(empleado);
		repositorio.save(empleado2);
		
		repositorio.findAll().forEach(System.out::println);
	};
  }	
}
