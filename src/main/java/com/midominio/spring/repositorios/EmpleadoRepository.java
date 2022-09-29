package com.midominio.spring.repositorios;


import org.springframework.data.repository.CrudRepository;
import com.midominio.spring.entidades.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{

}
