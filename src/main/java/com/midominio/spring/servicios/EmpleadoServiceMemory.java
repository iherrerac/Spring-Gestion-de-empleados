package com.midominio.spring.servicios;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.midominio.spring.entidades.Empleado;

@Service("EmpleadoServiceMemory")
public class EmpleadoServiceMemory {
	
	private List<Empleado> repositorio = new ArrayList<>();
	
	
	public Empleado add(Empleado e) {
		repositorio.add(e);
		return e;
	}
	
	public List<Empleado> findAll() {
		return repositorio;
	}
	
	public Empleado findById(long id) {
		Empleado empleado = null;
		for (Empleado empleado2 : repositorio) {
			if (empleado2.getId()== id) empleado = empleado2;
		}
		return empleado;
	}
	
	public Empleado edit(Empleado e) {
		boolean encontrado= false;
		for (int i = 0; i < repositorio.size(); i++) {
			if(repositorio.get(i).getId() == e.getId()) {
				encontrado = true;
				repositorio.remove(i);
				repositorio.add(i, e);
			}
		}
		// Falta codigo de si no hay ninguna coincidencia
		if (encontrado == false) repositorio.add(e);
		return e;
	}
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Empleado(1,"Antonio García", "antonio.garcia@openwebinars.net", "954000000", true),
						new Empleado(2,"María López", "maria.lopez@openwebinars.net", "954000000", false),
						new Empleado(3,"Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000", true)						
						)
				);
	}

}
