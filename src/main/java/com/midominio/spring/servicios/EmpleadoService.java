package com.midominio.spring.servicios;

import java.util.List;

import com.midominio.spring.entidades.Empleado;

public interface EmpleadoService {

	//Metodos CRUD
	public Empleado add(Empleado e);
	public List<Empleado> findAll();
	public Empleado findById(long id);
	public Empleado edit (Empleado e);
}
