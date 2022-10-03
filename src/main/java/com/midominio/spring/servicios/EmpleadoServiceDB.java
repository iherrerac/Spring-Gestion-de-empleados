package com.midominio.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.midominio.spring.entidades.Empleado;
import com.midominio.spring.repositorios.EmpleadoRepository;

@Primary
@Service("empleadoServiceDB")
public class EmpleadoServiceDB implements EmpleadoService {
	
	@Autowired
	private EmpleadoRepository repositorio;

	@Override
	public Empleado add(Empleado e) {
		return repositorio.save(e); // Devuelve el empleado con el Id actualizado
	}

	@Override
	public List<Empleado> findAll() {
		return repositorio.findAll(); //Metodo del repositorio
	}

	@Override
	public Empleado findById(long id) {
		// La clase Optional es un envoltorio conveniente para objetos para evitar errores del tipo NullException,
		//Vemos que findById() Devuelve optional, para no hacer mas cambios, 
		// con orElse devuelve la instancia del empleado o devuelve nulo
		return repositorio.findById(id).orElse(null); 
	}

	@Override
	public Empleado edit(Empleado e) {
		//Si la entidad que vamos a salvar ya tiene un Id pues edita el que tengamos
		return repositorio.save(e);
	}

}
