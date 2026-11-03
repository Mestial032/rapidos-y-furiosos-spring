package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.SexoEntity;

public interface SexoService {

	// Mostrar todos
	List<SexoEntity> findAll();

	// Mostrar solo habilitados
	List<SexoEntity> findAllCustom();

	// Buscar por codigo
	SexoEntity findById(Long id);

	// Registrar
	SexoEntity add(SexoEntity obj);

	// Actualizar
	SexoEntity update(SexoEntity obj, Long id);

	// Eliminar (logico: estado = false)
	SexoEntity delete(Long id);

	// Habilitar (estado = true)
	SexoEntity enable(Long id);
}
