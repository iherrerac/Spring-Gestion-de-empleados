package com.midominio.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.midominio.spring.entidades.Empleado;
import com.midominio.spring.servicios.EmpleadoService;


@Controller
public class MainController {

	@Autowired
	private EmpleadoService servicio;
	
	//Listar usuarios
	@GetMapping({"/","empleado/list"})
	public String ListadoEmpleados(Model model) {
		model.addAttribute("ListaEmp", servicio.findAll());
		return "list";
	}

	//Nuevo usuario
	@GetMapping("/empleado/new")
	public String nuevoEmpleadoForm (Model model) {
		model.addAttribute("empleadoForm", new Empleado()); //Creamos Command Object
		return "form";
	}
	@PostMapping("/empleado/new/submit")
	public String nuevoEmpleadoSubmit (@Valid @ModelAttribute ("empleadoForm") Empleado nuevoEmpleado, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "form";
		} else {
			servicio.add(nuevoEmpleado);
			return "redirect:/empleado/list";
		}
	}
	
	//Editar usuario
	@GetMapping("/empleado/edit/{id}")
	public String editarEmpleadoForm (@PathVariable long id, Model model) {
		Empleado empleado = servicio.findById(id);
		if(empleado != null) {
			model.addAttribute("empleadoForm", empleado); //Creamos Command Object
			return "form";
		} else {
			return "redirect:/empleado/new"; //si empleado es null redirigimos a nuevo usuario para creacion
		}
	}

	@PostMapping("/empleado/edit/submit")
	public String editarEmpleadoSubmit (@Valid @ModelAttribute ("empleadoForm") Empleado empleado, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		} else {
			servicio.edit(empleado);
			return "redirect:/empleado/list";
		}
	}

}
