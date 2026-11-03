package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.EmpleadoEntity;

public interface EmpleadoService {

	// Mostrar todos
	List<EmpleadoEntity> findAll();

	// Mostrar solo habilitados
	List<EmpleadoEntity> findAllCustom();

	// Buscar por codigo
	EmpleadoEntity findById(Long id);

	// Registrar
	EmpleadoEntity add(EmpleadoEntity obj);

	// Actualizar
	EmpleadoEntity update(EmpleadoEntity obj, Long id);

	// Eliminar (logico: estado = false)
	EmpleadoEntity delete(Long id);

	// Habilitar (estado = true)
	EmpleadoEntity enable(Long id);
}
