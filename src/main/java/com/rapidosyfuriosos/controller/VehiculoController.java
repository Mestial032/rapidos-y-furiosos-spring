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
import com.rapidosyfuriosos.entity.VehiculoEntity;
import com.rapidosyfuriosos.service.ClienteService;
import com.rapidosyfuriosos.service.ColorService;
import com.rapidosyfuriosos.service.MarcaService;
import com.rapidosyfuriosos.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
public class VehiculoController {

	// inyeccion de dependencia
	@Autowired
	private VehiculoService servicio;

	// servicios de las tablas relacionadas (para las listas desplegables)
	@Autowired
	private MarcaService servicioMarca;

	@Autowired
	private ColorService servicioColor;

	@Autowired
	private ClienteService servicioCliente;

	// carga las listas desplegables con los registros habilitados de MySQL
	private void CargarListas(Model modelo, VehiculoEntity obj) {
		modelo.addAttribute("listamarca", Listas.conActual(servicioMarca.findAllCustom(), obj.getMarca()));
		modelo.addAttribute("listacolor", Listas.conActual(servicioColor.findAllCustom(), obj.getColor()));
		modelo.addAttribute("listacliente", Listas.conActual(servicioCliente.findAllCustom(), obj.getCliente()));
	}

	// rutas
	// listar vehiculo (solo habilitados)
	@GetMapping("/vehiculo/listar")
	public String MostrarListarVehiculo(Model modelo) {
		modelo.addAttribute("listavehiculo", servicio.findAllCustom());
		return "vehiculo/listarvehiculo";
	}

	// registrar vehiculo
	@GetMapping("/vehiculo/registro")
	public String MostrarRegistrarVehiculo(Model modelo, @ModelAttribute("vehiculo") VehiculoEntity obj) {
		CargarListas(modelo, obj);
		return "vehiculo/registrarvehiculo";
	}

	// actualizar vehiculo
	@GetMapping("/vehiculo/actualiza/{id}")
	public String MostrarActualizarVehiculo(Model modelo, @PathVariable Long id) {
		VehiculoEntity obj = servicio.findById(id);
		modelo.addAttribute("vehiculo", obj);
		CargarListas(modelo, obj);
		return "vehiculo/actualizarvehiculo";
	}

	// habilitar vehiculo (muestra todos)
	@GetMapping("/vehiculo/habilita")
	public String MostrarHabilitarVehiculo(Model modelo) {
		modelo.addAttribute("listavehiculo", servicio.findAll());
		return "vehiculo/habilitarvehiculo";
	}

	// acciones de rutas
	// eliminar (logico)
	@GetMapping("/vehiculo/eliminar/{id}")
	public String EliminarVehiculo(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Vehiculo deshabilitado correctamente");
		return "redirect:/vehiculo/listar";
	}

	// habilitar
	@GetMapping("/vehiculo/habilitar/{id}")
	public String HabilitarVehiculo(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Vehiculo habilitado correctamente");
		return "redirect:/vehiculo/habilita";
	}

	// deshabilitar
	@GetMapping("/vehiculo/deshabilitar/{id}")
	public String DeshabilitarVehiculo(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Vehiculo deshabilitado correctamente");
		return "redirect:/vehiculo/habilita";
	}

	// modelo para pasar datos entre el controlador y la vista
	@ModelAttribute("vehiculo")
	public VehiculoEntity ModeloVehiculo() {
		VehiculoEntity obj = new VehiculoEntity();
		obj.setEstado(true);
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/vehiculo/registrar")
	public String RegistrarVehiculo(@Valid @ModelAttribute("vehiculo") VehiculoEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		if (resultado.hasErrors()) {
			CargarListas(modelo, obj);
			return "vehiculo/registrarvehiculo";
		}
		try {
			obj.setCodigo(null);
			obj.setPlaca(obj.getPlaca().toUpperCase());
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			CargarListas(modelo, obj);
			return "vehiculo/registrarvehiculo";
		}
		atributos.addFlashAttribute("mensaje", "Vehiculo registrado correctamente");
		return "redirect:/vehiculo/listar";
	}

	// actualizar
	@PostMapping("/vehiculo/actualizar/{id}")
	public String ActualizarVehiculo(@Valid @ModelAttribute("vehiculo") VehiculoEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			CargarListas(modelo, obj);
			return "vehiculo/actualizarvehiculo";
		}
		try {
			obj.setPlaca(obj.getPlaca().toUpperCase());
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			CargarListas(modelo, obj);
			return "vehiculo/actualizarvehiculo";
		}
		atributos.addFlashAttribute("mensaje", "Vehiculo actualizado correctamente");
		return "redirect:/vehiculo/listar";
	}
}
