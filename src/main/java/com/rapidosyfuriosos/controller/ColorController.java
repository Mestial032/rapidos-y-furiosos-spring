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
import com.rapidosyfuriosos.entity.ColorEntity;
import com.rapidosyfuriosos.service.ColorService;

import jakarta.validation.Valid;

@Controller
public class ColorController {

	// inyeccion de dependencia
	@Autowired
	private ColorService servicio;

	// rutas
	// listar color (solo habilitados)
	@GetMapping("/color/listar")
	public String MostrarListarColor(Model modelo) {
		modelo.addAttribute("listacolor", servicio.findAllCustom());
		return "color/listarcolor";
	}

	// registrar color
	@GetMapping("/color/registro")
	public String MostrarRegistrarColor() {
		return "color/registrarcolor";
	}

	// actualizar color
	// {id} -> es una variable de ruta
	@GetMapping("/color/actualiza/{id}")
	public String MostrarActualizarColor(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("color", servicio.findById(id));
		return "color/actualizarcolor";
	}

	// habilitar color (muestra todos: habilitados y deshabilitados)
	@GetMapping("/color/habilita")
	public String MostrarHabilitarColor(Model modelo) {
		modelo.addAttribute("listacolor", servicio.findAll());
		return "color/habilitarcolor";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/color/eliminar/{id}")
	public String EliminarColor(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Color deshabilitado correctamente");
		return "redirect:/color/listar";
	}

	// habilitar
	@GetMapping("/color/habilitar/{id}")
	public String HabilitarColor(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Color habilitado correctamente");
		return "redirect:/color/habilita";
	}

	// deshabilitar
	@GetMapping("/color/deshabilitar/{id}")
	public String DeshabilitarColor(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Color deshabilitado correctamente");
		return "redirect:/color/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("color")
	public ColorEntity ModeloColor() {
		ColorEntity obj = new ColorEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/color/registrar")
	public String RegistrarColor(@Valid @ModelAttribute("color") ColorEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "color/registrarcolor";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "color/registrarcolor";
		}
		atributos.addFlashAttribute("mensaje", "Color registrado correctamente");
		return "redirect:/color/listar";
	}

	// actualizar
	@PostMapping("/color/actualizar/{id}")
	public String ActualizarColor(@Valid @ModelAttribute("color") ColorEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "color/actualizarcolor";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "color/actualizarcolor";
		}
		atributos.addFlashAttribute("mensaje", "Color actualizado correctamente");
		return "redirect:/color/listar";
	}
}
