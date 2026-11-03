package com.rapidosyfuriosos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rapidosyfuriosos.config.MensajeError;
import com.rapidosyfuriosos.entity.RolEntity;
import com.rapidosyfuriosos.service.RolService;

import jakarta.validation.Valid;

@Controller
public class RolController {

	// inyeccion de dependencia
	@Autowired
	private RolService servicio;

	// rutas
	// listar rol (solo habilitados)
	@GetMapping("/rol/listar")
	public String MostrarListarRol(Model modelo) {
		modelo.addAttribute("listarol", servicio.findAllCustom());
		return "rol/listarrol";
	}

	// registrar rol
	@GetMapping("/rol/registro")
	public String MostrarRegistrarRol() {
		return "rol/registrarrol";
	}

	// actualizar rol
	// {id} -> es una variable de ruta
	@GetMapping("/rol/actualiza/{id}")
	public String MostrarActualizarRol(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("rol", servicio.findById(id));
		return "rol/actualizarrol";
	}

	// habilitar rol (muestra todos: habilitados y deshabilitados)
	@GetMapping("/rol/habilita")
	public String MostrarHabilitarRol(Model modelo) {
		modelo.addAttribute("listarol", servicio.findAll());
		return "rol/habilitarrol";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/rol/eliminar/{id}")
	public String EliminarRol(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Rol deshabilitado correctamente");
		return "redirect:/rol/listar";
	}

	// habilitar
	@GetMapping("/rol/habilitar/{id}")
	public String HabilitarRol(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Rol habilitado correctamente");
		return "redirect:/rol/habilita";
	}

	// deshabilitar
	@GetMapping("/rol/deshabilitar/{id}")
	public String DeshabilitarRol(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Rol deshabilitado correctamente");
		return "redirect:/rol/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("rol")
	public RolEntity ModeloRol() {
		RolEntity obj = new RolEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/rol/registrar")
	public String RegistrarRol(@Valid @ModelAttribute("rol") RolEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "rol/registrarrol";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "rol/registrarrol";
		}
		atributos.addFlashAttribute("mensaje", "Rol registrado correctamente");
		return "redirect:/rol/listar";
	}

	// actualizar
	@PostMapping("/rol/actualizar/{id}")
	public String ActualizarRol(@Valid @ModelAttribute("rol") RolEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "rol/actualizarrol";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "rol/actualizarrol";
		}
		atributos.addFlashAttribute("mensaje", "Rol actualizado correctamente");
		return "redirect:/rol/listar";
	}
}
