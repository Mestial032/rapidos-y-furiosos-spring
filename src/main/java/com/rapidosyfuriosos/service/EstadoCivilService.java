package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.EstadoCivilEntity;

public interface EstadoCivilService {

	// Mostrar todos
	List<EstadoCivilEntity> findAll();

	// Mostrar solo habilitados
	List<EstadoCivilEntity> findAllCustom();

	// Buscar por codigo
	EstadoCivilEntity findById(Long id);

	// Registrar
	EstadoCivilEntity add(EstadoCivilEntity obj);

	// Actualizar
	EstadoCivilEntity update(EstadoCivilEntity obj, Long id);

	// Eliminar (logico: estado = false)
	EstadoCivilEntity delete(Long id);

	// Habilitar (estado = true)
	EstadoCivilEntity enable(Long id);
}
