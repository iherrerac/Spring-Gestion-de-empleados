package com.midominio.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.midominio.spring.entidades.Empleado;
import com.midominio.spring.servicios.EmpleadoService;
import com.midominio.spring.upload.storage.StorageService;


@Controller
public class MainController {

	@Autowired
	//private EmpleadoServiceMemory servicio;
	private EmpleadoService servicio; // Cambiamos el cableado del servicio
	
	@Autowired
	private StorageService storageService;
	
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
	public String nuevoEmpleadoSubmit (@Valid @ModelAttribute ("empleadoForm") Empleado nuevoEmpleado, BindingResult bindingResult,
			@RequestParam ("file") MultipartFile file) {
		
		if(bindingResult.hasErrors()) {
			return "form";
			
		} else {
			if (!file.isEmpty()) {
				//No vamos a obligar a que el avatar se suba, si no se sube el valor es nulo
				String avatar = storageService.store(file, nuevoEmpleado.getId());
				nuevoEmpleado.setImagen(MvcUriComponentsBuilder
						.fromMethodName(MainController.class, "serveFile", avatar).build().toUriString());
			}
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
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
		
	}

}
