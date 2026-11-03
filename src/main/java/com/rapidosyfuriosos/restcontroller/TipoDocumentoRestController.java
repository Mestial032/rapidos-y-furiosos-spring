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

import com.rapidosyfuriosos.entity.TipoDocumentoEntity;
import com.rapidosyfuriosos.service.TipoDocumentoService;

@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoRestController {

	// Inyeccion de dependencias hacia el servicio
	@Autowired
	private TipoDocumentoService servicio;

	// Mostrar todos
	@GetMapping
	public List<TipoDocumentoEntity> findAll() {
		return servicio.findAll();
	}

	// Mostrar solo habilitados
	@GetMapping("/custom")
	public List<TipoDocumentoEntity> findAllCustom() {
		return servicio.findAllCustom();
	}

	// Buscar por codigo
	@GetMapping("/{id}")
	public TipoDocumentoEntity findById(@PathVariable Long id) {
		return servicio.findById(id);
	}

	// Registrar
	@PostMapping
	public TipoDocumentoEntity add(@RequestBody TipoDocumentoEntity obj) {
		return servicio.add(obj);
	}

	// Actualizar
	@PutMapping("/{id}")
	public TipoDocumentoEntity update(@RequestBody TipoDocumentoEntity obj, @PathVariable Long id) {
		return servicio.update(obj, id);
	}

	// Eliminar (logico)
	@DeleteMapping("/{id}")
	public TipoDocumentoEntity delete(@PathVariable Long id) {
		return servicio.delete(id);
	}

	// Habilitar (actualizacion parcial)
	@PatchMapping("/{id}")
	public TipoDocumentoEntity enable(@PathVariable Long id) {
		return servicio.enable(id);
	}
}
