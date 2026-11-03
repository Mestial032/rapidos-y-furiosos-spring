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

import com.rapidosyfuriosos.entity.VehiculoEntity;
import com.rapidosyfuriosos.service.VehiculoService;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoRestController {

	// Inyeccion de dependencias hacia el servicio
	@Autowired
	private VehiculoService servicio;

	// Mostrar todos
	@GetMapping
	public List<VehiculoEntity> findAll() {
		return servicio.findAll();
	}

	// Mostrar solo habilitados
	@GetMapping("/custom")
	public List<VehiculoEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	// Buscar por codigo
	@GetMapping("/{id}")
	public VehiculoEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	// Registrar
	@PostMapping
	public VehiculoEntity add(@RequestBody VehiculoEntity obj) {
		return servicio.add(obj);
	}

	// Actualizar
	@PutMapping("/{id}")
	public VehiculoEntity update(@RequestBody VehiculoEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	// Eliminar (logico)
	@DeleteMapping("/{id}")
	public VehiculoEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	// Habilitar (actualizacion parcial)
	@PatchMapping("/{id}")
	public VehiculoEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
