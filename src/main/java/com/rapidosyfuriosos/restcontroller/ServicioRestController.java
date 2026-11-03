package com.rapidosyfuriosos.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidosyfuriosos.entity.ServicioEntity;
import com.rapidosyfuriosos.service.ServicioService;

@RestController
@RequestMapping("/api/servicio")
public class ServicioRestController {

	// Inyeccion de dependencias hacia el servicio
	@Autowired
	private ServicioService servicio;

	// Mostrar todos
	@GetMapping
	public List<ServicioEntity> findAll() {
		return servicio.findAll();
	}

	// Mostrar solo habilitados
	@GetMapping("/custom")
	public List<ServicioEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	// Buscar por codigo
	@GetMapping("/{id}")
	public ServicioEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	// Registrar
	@PostMapping
	public ServicioEntity add(@RequestBody ServicioEntity obj) {
		return servicio.add(obj);
	}

	// Actualizar
	@PutMapping("/{id}")
	public ServicioEntity update(@RequestBody ServicioEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	// Eliminar (logico)
	@DeleteMapping("/{id}")
	public ServicioEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	// Habilitar (actualizacion parcial)
	@PatchMapping("/{id}")
	public ServicioEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
