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
import com.rapidosyfuriosos.entity.DistritoEntity;
import com.rapidosyfuriosos.service.DistritoService;

import jakarta.validation.Valid;

@Controller
public class DistritoController {

	// inyeccion de dependencia
	@Autowired
	private DistritoService servicio;

	// rutas
	// listar distrito (solo habilitados)
	@GetMapping("/distrito/listar")
	public String MostrarListarDistrito(Model modelo) {
		modelo.addAttribute("listadistrito", servicio.findAllCustom());
		return "distrito/listardistrito";
	}

	// registrar distrito
	@GetMapping("/distrito/registro")
	public String MostrarRegistrarDistrito() {
		return "distrito/registrardistrito";
	}

	// actualizar distrito
	// {id} -> es una variable de ruta
	@GetMapping("/distrito/actualiza/{id}")
	public String MostrarActualizarDistrito(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("distrito", servicio.findById(id));
		return "distrito/actualizardistrito";
	}

	// habilitar distrito (muestra todos: habilitados y deshabilitados)
	@GetMapping("/distrito/habilita")
	public String MostrarHabilitarDistrito(Model modelo) {
		modelo.addAttribute("listadistrito", servicio.findAll());
		return "distrito/habilitardistrito";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/distrito/eliminar/{id}")
	public String EliminarDistrito(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Distrito deshabilitado correctamente");
		return "redirect:/distrito/listar";
	}

	// habilitar
	@GetMapping("/distrito/habilitar/{id}")
	public String HabilitarDistrito(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Distrito habilitado correctamente");
		return "redirect:/distrito/habilita";
	}

	// deshabilitar
	@GetMapping("/distrito/deshabilitar/{id}")
	public String DeshabilitarDistrito(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Distrito deshabilitado correctamente");
		return "redirect:/distrito/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("distrito")
	public DistritoEntity ModeloDistrito() {
		DistritoEntity obj = new DistritoEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/distrito/registrar")
	public String RegistrarDistrito(@Valid @ModelAttribute("distrito") DistritoEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "distrito/registrardistrito";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "distrito/registrardistrito";
		}
		atributos.addFlashAttribute("mensaje", "Distrito registrado correctamente");
		return "redirect:/distrito/listar";
	}

	// actualizar
	@PostMapping("/distrito/actualizar/{id}")
	public String ActualizarDistrito(@Valid @ModelAttribute("distrito") DistritoEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "distrito/actualizardistrito";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "distrito/actualizardistrito";
		}
		atributos.addFlashAttribute("mensaje", "Distrito actualizado correctamente");
		return "redirect:/distrito/listar";
	}
}
