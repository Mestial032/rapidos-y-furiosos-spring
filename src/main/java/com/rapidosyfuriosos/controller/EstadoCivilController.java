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
import com.rapidosyfuriosos.entity.EstadoCivilEntity;
import com.rapidosyfuriosos.service.EstadoCivilService;

import jakarta.validation.Valid;

@Controller
public class EstadoCivilController {

	// inyeccion de dependencia
	@Autowired
	private EstadoCivilService servicio;

	// rutas
	// listar estadocivil (solo habilitados)
	@GetMapping("/estadocivil/listar")
	public String MostrarListarEstadoCivil(Model modelo) {
		modelo.addAttribute("listaestadocivil", servicio.findAllCustom());
		return "estadocivil/listarestadocivil";
	}

	// registrar estadocivil
	@GetMapping("/estadocivil/registro")
	public String MostrarRegistrarEstadoCivil() {
		return "estadocivil/registrarestadocivil";
	}

	// actualizar estadocivil
	// {id} -> es una variable de ruta
	@GetMapping("/estadocivil/actualiza/{id}")
	public String MostrarActualizarEstadoCivil(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("estadocivil", servicio.findById(id));
		return "estadocivil/actualizarestadocivil";
	}

	// habilitar estadocivil (muestra todos: habilitados y deshabilitados)
	@GetMapping("/estadocivil/habilita")
	public String MostrarHabilitarEstadoCivil(Model modelo) {
		modelo.addAttribute("listaestadocivil", servicio.findAll());
		return "estadocivil/habilitarestadocivil";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/estadocivil/eliminar/{id}")
	public String EliminarEstadoCivil(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Estado Civil deshabilitado correctamente");
		return "redirect:/estadocivil/listar";
	}

	// habilitar
	@GetMapping("/estadocivil/habilitar/{id}")
	public String HabilitarEstadoCivil(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Estado Civil habilitado correctamente");
		return "redirect:/estadocivil/habilita";
	}

	// deshabilitar
	@GetMapping("/estadocivil/deshabilitar/{id}")
	public String DeshabilitarEstadoCivil(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Estado Civil deshabilitado correctamente");
		return "redirect:/estadocivil/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("estadocivil")
	public EstadoCivilEntity ModeloEstadoCivil() {
		EstadoCivilEntity obj = new EstadoCivilEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/estadocivil/registrar")
	public String RegistrarEstadoCivil(@Valid @ModelAttribute("estadocivil") EstadoCivilEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "estadocivil/registrarestadocivil";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "estadocivil/registrarestadocivil";
		}
		atributos.addFlashAttribute("mensaje", "Estado Civil registrado correctamente");
		return "redirect:/estadocivil/listar";
	}

	// actualizar
	@PostMapping("/estadocivil/actualizar/{id}")
	public String ActualizarEstadoCivil(@Valid @ModelAttribute("estadocivil") EstadoCivilEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "estadocivil/actualizarestadocivil";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "estadocivil/actualizarestadocivil";
		}
		atributos.addFlashAttribute("mensaje", "Estado Civil actualizado correctamente");
		return "redirect:/estadocivil/listar";
	}
}
