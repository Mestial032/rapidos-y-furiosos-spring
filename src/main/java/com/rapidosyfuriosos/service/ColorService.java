package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.ColorEntity;

public interface ColorService {

	// Mostrar todos
	List<ColorEntity> findAll();

	// Mostrar solo habilitados
	List<ColorEntity> findAllCustom();

	// Buscar por codigo
	ColorEntity findById(Long id);

	// Registrar
	ColorEntity add(ColorEntity obj);

	// Actualizar
	ColorEntity update(ColorEntity obj, Long id);

	// Eliminar (logico: estado = false)
	ColorEntity delete(Long id);

	// Habilitar (estado = true)
	ColorEntity enable(Long id);
}
