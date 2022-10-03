package com.midominio.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midominio.spring.entidades.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

}
