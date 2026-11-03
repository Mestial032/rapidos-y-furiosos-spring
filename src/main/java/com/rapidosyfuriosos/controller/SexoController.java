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
import com.rapidosyfuriosos.entity.SexoEntity;
import com.rapidosyfuriosos.service.SexoService;

import jakarta.validation.Valid;

@Controller
public class SexoController {

	// inyeccion de dependencia
	@Autowired
	private SexoService servicio;

	// rutas
	// listar sexo (solo habilitados)
	@GetMapping("/sexo/listar")
	public String MostrarListarSexo(Model modelo) {
		modelo.addAttribute("listasexo", servicio.findAllCustom());
		return "sexo/listarsexo";
	}

	// registrar sexo
	@GetMapping("/sexo/registro")
	public String MostrarRegistrarSexo() {
		return "sexo/registrarsexo";
	}

	// actualizar sexo
	// {id} -> es una variable de ruta
	@GetMapping("/sexo/actualiza/{id}")
	public String MostrarActualizarSexo(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("sexo", servicio.findById(id));
		return "sexo/actualizarsexo";
	}

	// habilitar sexo (muestra todos: habilitados y deshabilitados)
	@GetMapping("/sexo/habilita")
	public String MostrarHabilitarSexo(Model modelo) {
		modelo.addAttribute("listasexo", servicio.findAll());
		return "sexo/habilitarsexo";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/sexo/eliminar/{id}")
	public String EliminarSexo(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Sexo deshabilitado correctamente");
		return "redirect:/sexo/listar";
	}

	// habilitar
	@GetMapping("/sexo/habilitar/{id}")
	public String HabilitarSexo(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Sexo habilitado correctamente");
		return "redirect:/sexo/habilita";
	}

	// deshabilitar
	@GetMapping("/sexo/deshabilitar/{id}")
	public String DeshabilitarSexo(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Sexo deshabilitado correctamente");
		return "redirect:/sexo/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("sexo")
	public SexoEntity ModeloSexo() {
		SexoEntity obj = new SexoEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/sexo/registrar")
	public String RegistrarSexo(@Valid @ModelAttribute("sexo") SexoEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "sexo/registrarsexo";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "sexo/registrarsexo";
		}
		atributos.addFlashAttribute("mensaje", "Sexo registrado correctamente");
		return "redirect:/sexo/listar";
	}

	// actualizar
	@PostMapping("/sexo/actualizar/{id}")
	public String ActualizarSexo(@Valid @ModelAttribute("sexo") SexoEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "sexo/actualizarsexo";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "sexo/actualizarsexo";
		}
		atributos.addFlashAttribute("mensaje", "Sexo actualizado correctamente");
		return "redirect:/sexo/listar";
	}
}
