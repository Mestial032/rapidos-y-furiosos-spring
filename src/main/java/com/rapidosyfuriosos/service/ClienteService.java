package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.ClienteEntity;

public interface ClienteService {

	// Mostrar todos
	List<ClienteEntity> findAll();

	// Mostrar solo habilitados
	List<ClienteEntity> findAllCustom();

	// Buscar por codigo
	ClienteEntity findById(Long id);

	// Registrar
	ClienteEntity add(ClienteEntity obj);

	// Actualizar
	ClienteEntity update(ClienteEntity obj, Long id);

	// Eliminar (logico: estado = false)
	ClienteEntity delete(Long id);

	// Habilitar (estado = true)
	ClienteEntity enable(Long id);
}
