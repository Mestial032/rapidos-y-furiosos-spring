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
import com.rapidosyfuriosos.entity.ServicioEntity;
import com.rapidosyfuriosos.service.ServicioService;

import jakarta.validation.Valid;

@Controller
public class ServicioController {

	// inyeccion de dependencia
	@Autowired
	private ServicioService servicio;

	// rutas
	// listar servicio (solo habilitados)
	@GetMapping("/servicio/listar")
	public String MostrarListarServicio(Model modelo) {
		modelo.addAttribute("listaservicio", servicio.findAllCustom());
		return "servicio/listarservicio";
	}

	// registrar servicio
	@GetMapping("/servicio/registro")
	public String MostrarRegistrarServicio() {
		return "servicio/registrarservicio";
	}

	// actualizar servicio
	// {id} -> es una variable de ruta
	@GetMapping("/servicio/actualiza/{id}")
	public String MostrarActualizarServicio(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("servicio", servicio.findById(id));
		return "servicio/actualizarservicio";
	}

	// habilitar servicio (muestra todos: habilitados y deshabilitados)
	@GetMapping("/servicio/habilita")
	public String MostrarHabilitarServicio(Model modelo) {
		modelo.addAttribute("listaservicio", servicio.findAll());
		return "servicio/habilitarservicio";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/servicio/eliminar/{id}")
	public String EliminarServicio(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Servicio deshabilitado correctamente");
		return "redirect:/servicio/listar";
	}

	// habilitar
	@GetMapping("/servicio/habilitar/{id}")
	public String HabilitarServicio(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Servicio habilitado correctamente");
		return "redirect:/servicio/habilita";
	}

	// deshabilitar
	@GetMapping("/servicio/deshabilitar/{id}")
	public String DeshabilitarServicio(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Servicio deshabilitado correctamente");
		return "redirect:/servicio/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("servicio")
	public ServicioEntity ModeloServicio() {
		ServicioEntity obj = new ServicioEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/servicio/registrar")
	public String RegistrarServicio(@Valid @ModelAttribute("servicio") ServicioEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "servicio/registrarservicio";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "servicio/registrarservicio";
		}
		atributos.addFlashAttribute("mensaje", "Servicio registrado correctamente");
		return "redirect:/servicio/listar";
	}

	// actualizar
	@PostMapping("/servicio/actualizar/{id}")
	public String ActualizarServicio(@Valid @ModelAttribute("servicio") ServicioEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "servicio/actualizarservicio";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "servicio/actualizarservicio";
		}
		atributos.addFlashAttribute("mensaje", "Servicio actualizado correctamente");
		return "redirect:/servicio/listar";
	}
}
