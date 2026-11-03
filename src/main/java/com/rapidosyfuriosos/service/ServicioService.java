package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.ServicioEntity;

public interface ServicioService {

	// Mostrar todos
	List<ServicioEntity> findAll();

	// Mostrar solo habilitados
	List<ServicioEntity> findAllCustom();

	// Buscar por codigo
	ServicioEntity findById(Long id);

	// Registrar
	ServicioEntity add(ServicioEntity obj);

	// Actualizar
	ServicioEntity update(ServicioEntity obj, Long id);

	// Eliminar (logico: estado = false)
	ServicioEntity delete(Long id);

	// Habilitar (estado = true)
	ServicioEntity enable(Long id);
}
