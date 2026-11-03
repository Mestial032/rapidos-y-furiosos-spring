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

import com.rapidosyfuriosos.entity.DistritoEntity;
import com.rapidosyfuriosos.service.DistritoService;

@RestController
@RequestMapping("/api/distrito")
public class DistritoRestController {

	// Inyeccion de dependencias hacia el servicio
	@Autowired
	private DistritoService servicio;

	// Mostrar todos
	@GetMapping
	public List<DistritoEntity> findAll() {
		return servicio.findAll();
	}

	// Mostrar solo habilitados
	@GetMapping("/custom")
	public List<DistritoEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	// Buscar por codigo
	@GetMapping("/{id}")
	public DistritoEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	// Registrar
	@PostMapping
	public DistritoEntity add(@RequestBody DistritoEntity obj) {
		return servicio.add(obj);
	}

	// Actualizar
	@PutMapping("/{id}")
	public DistritoEntity update(@RequestBody DistritoEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	// Eliminar (logico)
	@DeleteMapping("/{id}")
	public DistritoEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	// Habilitar (actualizacion parcial)
	@PatchMapping("/{id}")
	public DistritoEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
