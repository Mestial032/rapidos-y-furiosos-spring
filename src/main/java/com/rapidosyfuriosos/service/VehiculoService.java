package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.VehiculoEntity;

public interface VehiculoService {

	// Mostrar todos
	List<VehiculoEntity> findAll();

	// Mostrar solo habilitados
	List<VehiculoEntity> findAllCustom();

	// Buscar por codigo
	VehiculoEntity findById(Long id);

	// Registrar
	VehiculoEntity add(VehiculoEntity obj);

	// Actualizar
	VehiculoEntity update(VehiculoEntity obj, Long id);

	// Eliminar (logico: estado = false)
	VehiculoEntity delete(Long id);

	// Habilitar (estado = true)
	VehiculoEntity enable(Long id);
}
