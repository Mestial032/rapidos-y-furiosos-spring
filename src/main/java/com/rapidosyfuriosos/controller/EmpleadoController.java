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
import com.rapidosyfuriosos.entity.EmpleadoEntity;
import com.rapidosyfuriosos.service.DistritoService;
import com.rapidosyfuriosos.service.EmpleadoService;
import com.rapidosyfuriosos.service.EstadoCivilService;
import com.rapidosyfuriosos.service.RolService;
import com.rapidosyfuriosos.service.SexoService;
import com.rapidosyfuriosos.service.TipoDocumentoService;

import jakarta.validation.Valid;

@Controller
public class EmpleadoController {

	// inyeccion de dependencia
	@Autowired
	private EmpleadoService servicio;

	// servicios de las tablas relacionadas (para las listas desplegables)
	@Autowired
	private DistritoService servicioDistrito;

	@Autowired
	private RolService servicioRol;

	@Autowired
	private SexoService servicioSexo;

	@Autowired
	private TipoDocumentoService servicioTipoDocumento;

	@Autowired
	private EstadoCivilService servicioEstadoCivil;

	// carga las listas desplegables con los registros habilitados de MySQL
	private void CargarListas(Model modelo, EmpleadoEntity obj) {
		modelo.addAttribute("listadistrito", Listas.conActual(servicioDistrito.findAllCustom(), obj.getDistrito()));
		modelo.addAttribute("listarol", Listas.conActual(servicioRol.findAllCustom(), obj.getRol()));
		modelo.addAttribute("listasexo", Listas.conActual(servicioSexo.findAllCustom(), obj.getSexo()));
		modelo.addAttribute("listatipodocumento",
				Listas.conActual(servicioTipoDocumento.findAllCustom(), obj.getTipoDocumento()));
		modelo.addAttribute("listaestadocivil",
				Listas.conActual(servicioEstadoCivil.findAllCustom(), obj.getEstadoCivil()));
	}

	// rutas
	// listar empleado (solo habilitados)
	@GetMapping("/empleado/listar")
	public String MostrarListarEmpleado(Model modelo) {
		modelo.addAttribute("listaempleado", servicio.findAllCustom());
		return "empleado/listarempleado";
	}

	// registrar empleado
	@GetMapping("/empleado/registro")
	public String MostrarRegistrarEmpleado(Model modelo, @ModelAttribute("empleado") EmpleadoEntity obj) {
		CargarListas(modelo, obj);
		return "empleado/registrarempleado";
	}

	// actualizar empleado
	@GetMapping("/empleado/actualiza/{id}")
	public String MostrarActualizarEmpleado(Model modelo, @PathVariable Long id) {
		EmpleadoEntity obj = servicio.findById(id);
		modelo.addAttribute("empleado", obj);
		CargarListas(modelo, obj);
		return "empleado/actualizarempleado";
	}

	// habilitar empleado (muestra todos)
	@GetMapping("/empleado/habilita")
	public String MostrarHabilitarEmpleado(Model modelo) {
		modelo.addAttribute("listaempleado", servicio.findAll());
		return "empleado/habilitarempleado";
	}

	// acciones de rutas
	// eliminar (logico)
	@GetMapping("/empleado/eliminar/{id}")
	public String EliminarEmpleado(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Empleado deshabilitado correctamente");
		return "redirect:/empleado/listar";
	}

	// habilitar
	@GetMapping("/empleado/habilitar/{id}")
	public String HabilitarEmpleado(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.enable(id);
		atributos.addFlashAttribute("mensaje", "Empleado habilitado correctamente");
		return "redirect:/empleado/habilita";
	}

	// deshabilitar
	@GetMapping("/empleado/deshabilitar/{id}")
	public String DeshabilitarEmpleado(@PathVariable Long id, RedirectAttributes atributos) {
		servicio.delete(id);
		atributos.addFlashAttribute("mensaje", "Empleado deshabilitado correctamente");
		return "redirect:/empleado/habilita";
	}

	// modelo para pasar datos entre el controlador y la vista
	@ModelAttribute("empleado")
	public EmpleadoEntity ModeloEmpleado() {
		EmpleadoEntity obj = new EmpleadoEntity();
		obj.setEstado(true);
		obj.setNacionalidad("Peruana");
		return obj;
	}

	// acciones
	// registrar
	@PostMapping("/empleado/registrar")
	public String RegistrarEmpleado(@Valid @ModelAttribute("empleado") EmpleadoEntity obj, BindingResult resultado,
			Model modelo, RedirectAttributes atributos) {
		// al registrar la clave es obligatoria
		if (obj.getClave() == null) {
			resultado.rejectValue("clave", "NotBlank", "La clave es obligatoria");
		}
		if (resultado.hasErrors()) {
			obj.setClave(null);
			CargarListas(modelo, obj);
			return "empleado/registrarempleado";
		}
		try {
			obj.setCodigo(null);
			servicio.add(obj);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			obj.setClave(null);
			CargarListas(modelo, obj);
			return "empleado/registrarempleado";
		}
		atributos.addFlashAttribute("mensaje", "Empleado registrado correctamente");
		return "redirect:/empleado/listar";
	}

	// actualizar (si la clave se deja en blanco se conserva la actual)
	@PostMapping("/empleado/actualizar/{id}")
	public String ActualizarEmpleado(@Valid @ModelAttribute("empleado") EmpleadoEntity obj, BindingResult resultado,
			@PathVariable Long id, Model modelo, RedirectAttributes atributos) {
		obj.setCodigo(id);
		if (resultado.hasErrors()) {
			CargarListas(modelo, obj);
			return "empleado/actualizarempleado";
		}
		try {
			servicio.update(obj, id);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("error", MensajeError.obtener(e));
			CargarListas(modelo, obj);
			return "empleado/actualizarempleado";
		}
		atributos.addFlashAttribute("mensaje", "Empleado actualizado correctamente");
		return "redirect:/empleado/listar";
	}
}
