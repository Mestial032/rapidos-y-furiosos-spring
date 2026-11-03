package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.RolEntity;

public interface RolService {

	// Mostrar todos
	List<RolEntity> findAll();

	// Mostrar solo habilitados
	List<RolEntity> findAllCustom();

	// Buscar por codigo
	RolEntity findById(Long id);

	// Registrar
	RolEntity add(RolEntity obj);

	// Actualizar
	RolEntity update(RolEntity obj, Long id);

	// Eliminar (logico: estado = false)
	RolEntity delete(Long id);

	// Habilitar (estado = true)
	RolEntity enable(Long id);
}
