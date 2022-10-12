package com.midominio.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midominio.spring.entidades.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String telefono);
}
