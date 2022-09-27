package com.midominio.spring.entidades;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Empleado {
	@Min(value=0,message="{empleado.id.mayorquecero}")
	private long id;
	@NotNull (message="aaaaaaaa")
	private String nombre;
	@Email (message="{empleado.email.email}")
	private String email;
	@Size
	private String telefono;
	private boolean directivo;

	//constructores, getters y setters
	public Empleado() {
		
	}
	
	public Empleado(long id, String nombre, String email, String telefono, boolean directivo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.directivo = directivo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
    public boolean isDirectivo() {
		return directivo;
	}

	public void setDirectivo(boolean directivo) {
		this.directivo = directivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(directivo, email, id, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return directivo == other.directivo && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
				+ ", directivo=" + directivo + "]";
	}

	
}