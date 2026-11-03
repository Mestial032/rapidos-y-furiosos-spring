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

import com.rapidosyfuriosos.entity.SexoEntity;
import com.rapidosyfuriosos.service.SexoService;

@RestController
@RequestMapping("/api/sexo")
public class SexoRestController {

	// Inyeccion de dependencias hacia el servicio
	@Autowired
	private SexoService servicio;

	// Mostrar todos
	@GetMapping
	public List<SexoEntity> findAll() {
		return servicio.findAll();
	}

	// Mostrar solo habilitados
	@GetMapping("/custom")
	public List<SexoEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	// Buscar por codigo
	@GetMapping("/{id}")
	public SexoEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	// Registrar
	@PostMapping
	public SexoEntity add(@RequestBody SexoEntity obj) {
		return servicio.add(obj);
	}

	// Actualizar
	@PutMapping("/{id}")
	public SexoEntity update(@RequestBody SexoEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	// Eliminar (logico)
	@DeleteMapping("/{id}")
	public SexoEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	// Habilitar (actualizacion parcial)
	@PatchMapping("/{id}")
	public SexoEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
