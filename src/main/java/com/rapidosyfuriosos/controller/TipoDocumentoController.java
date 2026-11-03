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
import com.rapidosyfuriosos.entity.TipoDocumentoEntity;
import com.rapidosyfuriosos.service.TipoDocumentoService;

import jakarta.validation.Valid;

@Controller
public class TipoDocumentoController {

	// inyeccion de dependencia
	@Autowired
	private TipoDocumentoService servicio;

	// rutas
	// listar tipodocumento (solo habilitados)
	@GetMapping("/tipodocumento/listar")
	public String MostrarListarTipoDocumento(Model modelo) {
		modelo.addAttribute("listatipodocumento", servicio.findAllCustom());
		return "tipodocumento/listartipodocumento";
	}

	// registrar tipodocumento
	@GetMapping("/tipodocumento/registro")
	public String MostrarRegistrarTipoDocumento() {
		return "tipodocumento/registrartipodocumento";
	}

	// actualizar tipodocumento
	// {id} -> es una variable de ruta
	@GetMapping("/tipodocumento/actualiza/{id}")
	public String MostrarActualizarTipoDocumento(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("tipodocumento", servicio.findById(id));
		return "tipodocumento/actualizartipodocumento";
	}

	// habilitar tipodocumento (muestra todos: habilitados y deshabilitados)
	@GetMapping("/tipodocumento/habilita")
	public String MostrarHabilitarTipoDocumento(Model modelo) {
		modelo.addAttribute("listatipodocumento", servicio.findAll());
		return "tipodocumento/habilitartipodocumento";
	}

	// acciones de rutas
	// eliminar (logico: cambia el estado a deshabilitado)
	@GetMapping("/tipodocumento/eliminar/{id}")
	public String EliminarTipoDocumento(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Tipo de Documento deshabilitado correctamente");
		return "redirect:/tipodocumento/listar";
	}

	// habilitar
	@GetMapping("/tipodocumento/habilitar/{id}")
	public String HabilitarTipoDocumento(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Tipo de Documento habilitado correctamente");
		return "redirect:/tipodocumento/habilita";
	}

	// deshabilitar
	@GetMapping("/tipodocumento/deshabilitar/{id}")
	public String DeshabilitarTipoDocumento(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Tipo de Documento deshabilitado correctamente");
		return "redirect:/tipodocumento/habilita";
	}

	// creamos un modelo para pasar datos entre el controlador y la vista
	// y tambien recibir datos enviados desde el formulario
	@ModelAttribute("tipodocumento")
	public TipoDocumentoEntity ModeloTipoDocumento() {
		TipoDocumentoEntity obj = new TipoDocumentoEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/tipodocumento/registrar")
	public String RegistrarTipoDocumento(@Valid @ModelAttribute("tipodocumento") TipoDocumentoEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// si hay errores de validacion regresamos al formulario
		if (resultado.hasErrors()) {
			return "tipodocumento/registrartipodocumento";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			// registro duplicado u otra restriccion de la base de datos
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "tipodocumento/registrartipodocumento";
		}
		atributos.addFlashAttribute("mensaje", "Tipo de Documento registrado correctamente");
		return "redirect:/tipodocumento/listar";
	}

	// actualizar
	@PostMapping("/tipodocumento/actualizar/{id}")
	public String ActualizarTipoDocumento(@Valid @ModelAttribute("tipodocumento") TipoDocumentoEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			return "tipodocumento/actualizartipodocumento";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			return "tipodocumento/actualizartipodocumento";
		}
		atributos.addFlashAttribute("mensaje", "Tipo de Documento actualizado correctamente");
		return "redirect:/tipodocumento/listar";
	}
}
