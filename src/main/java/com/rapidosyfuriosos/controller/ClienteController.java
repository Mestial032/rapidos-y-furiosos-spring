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
import com.rapidosyfuriosos.entity.ClienteEntity;
import com.rapidosyfuriosos.service.ClienteService;
import com.rapidosyfuriosos.service.DistritoService;
import com.rapidosyfuriosos.service.SexoService;
import com.rapidosyfuriosos.service.TipoDocumentoService;

import jakarta.validation.Valid;

@Controller
public class ClienteController {

	// inyeccion de dependencia
	@Autowired
	private ClienteService servicio;

	// servicios de las tablas relacionadas (para las listas desplegables)
	@Autowired
	private DistritoService servicioDistrito;

	@Autowired
	private SexoService servicioSexo;

	@Autowired
	private TipoDocumentoService servicioTipoDocumento;

	// carga las listas desplegables con los registros habilitados de MySQL
	// (si el cliente tiene asignado un valor deshabilitado, igual se muestra)
	private void CargarListas(Model modelo, ClienteEntity obj) {
		modelo.addAttribute("listadistrito", Listas.conActual(servicioDistrito.findAllCustom(), obj.getDistrito()));
		modelo.addAttribute("listasexo", Listas.conActual(servicioSexo.findAllCustom(), obj.getSexo()));
		modelo.addAttribute("listatipodocumento",
				Listas.conActual(servicioTipoDocumento.findAllCustom(), obj.getTipoDocumento()));
	}

	// rutas
	// listar cliente (solo habilitados)
	@GetMapping("/cliente/listar")
	public String MostrarListarCliente(Model modelo) {
		modelo.addAttribute("listacliente", servicio.findAllCustom());
		return "cliente/listarcliente";
	}

	// registrar cliente
	@GetMapping("/cliente/registro")
	public String MostrarRegistrarCliente(Model modelo, @ModelAttribute("cliente") ClienteEntity obj) {
		CargarListas(modelo, obj);
		return "cliente/registrarcliente";
	}

	// actualizar cliente
	@GetMapping("/cliente/actualiza/{id}")
	public String MostrarActualizarCliente(Model modelo, @PathVariable Long id) {
		ClienteEntity obj = servicio.findById(id);
		modelo.addAttribute("cliente", obj);
		CargarListas(modelo, obj);
		return "cliente/actualizarcliente";
	}

	// habilitar cliente (muestra todos)
	@GetMapping("/cliente/habilita")
	public String MostrarHabilitarCliente(Model modelo) {
		modelo.addAttribute("listacliente", servicio.findAll());
		return "cliente/habilitarcliente";
	}

	// acciones de rutas
	// eliminar (logico)
	@GetMapping("/cliente/eliminar/{id}")
	public String EliminarCliente(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Cliente deshabilitado correctamente");
		return "redirect:/cliente/listar";
	}

	// habilitar
	@GetMapping("/cliente/habilitar/{id}")
	public String HabilitarCliente(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Cliente habilitado correctamente");
		return "redirect:/cliente/habilita";
	}

	// deshabilitar
	@GetMapping("/cliente/deshabilitar/{id}")
	public String DeshabilitarCliente(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Cliente deshabilitado correctamente");
		return "redirect:/cliente/habilita";
	}

	// modelo para pasar datos entre el controlador y la vista
	@ModelAttribute("cliente")
	public ClienteEntity ModeloCliente() {
		ClienteEntity obj = new ClienteEntity();
		obj.setEstado(true);
		obj.setNacionalidad("Peruana");
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/cliente/registrar")
	public String RegistrarCliente(@Valid @ModelAttribute("cliente") ClienteEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		if (resultado.hasErrors()) {
			CargarListas(modelo, obj);
			return "cliente/registrarcliente";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			CargarListas(modelo, obj);
			return "cliente/registrarcliente";
		}
		atributos.addFlashAttribute("mensaje", "Cliente registrado correctamente");
		return "redirect:/cliente/listar";
	}

	// actualizar
	@PostMapping("/cliente/actualizar/{id}")
	public String ActualizarCliente(@Valid @ModelAttribute("cliente") ClienteEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			CargarListas(modelo, obj);
			return "cliente/actualizarcliente";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			CargarListas(modelo, obj);
			return "cliente/actualizarcliente";
		}
		atributos.addFlashAttribute("mensaje", "Cliente actualizado correctamente");
		return "redirect:/cliente/listar";
	}
}
