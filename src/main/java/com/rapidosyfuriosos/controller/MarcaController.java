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
import com.rapidosyfuriosos.entity.MarcaEntity;
import com.rapidosyfuriosos.service.MarcaService;

import jakarta.validation.Valid;

@Controller
public class MarcaController {

	// inyeccion de dependencia
	@Autowired
	private MarcaService servicio;

	// rutas
	// listar marca (solo habilitados)
	@GetMapping("/marca/listar")
	public String MostrarListarMarca(Model modelo) {
		modelo.addAttribute("listamarca", servicio.findAllCustom());
		return "marca/listarmarca";
	}

	// registrar marca
	@GetMapping("/marca/registro")
	public String MostrarRegistrarMarca() {
		return "marca/registrarmarca";
	}

	// actualizar marca
	// {id} -> es una variable de ruta
	@GetMapping("/marca/actualiza/{id}")
	public String MostrarActualizarMarca(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("marca", servicio.findById(id));
		return "marca/actualizarmarca";
	}

	// habilitar marca (muestra todos: habilitados y deshabilitados)
	@GetMapping("/marca/habilita")
	public String MostrarHabilitarMarca(Model modelo) {
		modelo.addAttribute("listamarca", servicio.findAll());
		return "marca/habilitarmarca";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/marca/eliminar/{id}")
	public String EliminarMarca(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Marca deshabilitada correctamente");
		return "redirect:/marca/listar";
	}

	// habilitar
	@GetMapping("/marca/habilitar/{id}")
	public String HabilitarMarca(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Marca habilitada correctamente");
		return "redirect:/marca/habilita";
	}

	// deshabilitar
	@GetMapping("/marca/deshabilitar/{id}")
	public String DeshabilitarMarca(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Marca deshabilitada correctamente");
		return "redirect:/marca/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("marca")
	public MarcaEntity ModeloMarca() {
		MarcaEntity obj = new MarcaEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/marca/registrar")
	public String RegistrarMarca(@Valid @ModelAttribute("marca") MarcaEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "marca/registrarmarca";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "marca/registrarmarca";
		}
		atributos.addFlashAttribute("mensaje", "Marca registrada correctamente");
		return "redirect:/marca/listar";
	}

	// actualizar
	@PostMapping("/marca/actualizar/{id}")
	public String ActualizarMarca(@Valid @ModelAttribute("marca") MarcaEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "marca/actualizarmarca";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "marca/actualizarmarca";
		}
		atributos.addFlashAttribute("mensaje", "Marca actualizada correctamente");
		return "redirect:/marca/listar";
	}
}
