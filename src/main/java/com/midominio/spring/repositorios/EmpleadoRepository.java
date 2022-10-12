package com.midominio.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.midominio.spring.entidades.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	// Consulta Bd mediante nombre de metodo
	//List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String telefono);
	
	// Misma consulta BD mediante lenguaje @Query de JPA
	//@Query("select e from Empleado e where lower(e.nombre) like lower(concat('%', ?1,'%')) or lower(e.email) like lower(concat('%', ?1,'%')) or lower(telefono) like lower(concat('%', ?1,'%'))")
	
	// Misma consulta mediante query NATIVA ( El SQL del gestor de la BD)
	@Query(value = "SELECT * FROM empleado WHERE lower(nombre) LIKE lower(concat('%', ?1,'%')) OR LOWER(email) LIKE lower(concat('%', ?1,'%')) OR lower(telefono) LIKE lower(concat('%', ?1,'%'))", nativeQuery= true)
	
	List<Empleado> buscaPorNombreEmailTelefono (String e);
}
