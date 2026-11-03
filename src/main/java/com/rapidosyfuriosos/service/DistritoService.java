package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.DistritoEntity;

public interface DistritoService {

	// Mostrar todos
	List<DistritoEntity> findAll();

	// Mostrar solo habilitados
	List<DistritoEntity> findAllCustom();

	// Buscar por codigo
	DistritoEntity findById(Long id);

	// Registrar
	DistritoEntity add(DistritoEntity obj);

	// Actualizar
	DistritoEntity update(DistritoEntity obj, Long id);

	// Eliminar (logico: estado = false)
	DistritoEntity delete(Long id);

	// Habilitar (estado = true)
	DistritoEntity enable(Long id);
}
