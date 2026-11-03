package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.MarcaEntity;

public interface MarcaService {

	// Mostrar todos
	List<MarcaEntity> findAll();

	// Mostrar solo habilitados
	List<MarcaEntity> findAllCustom();

	// Buscar por codigo
	MarcaEntity findById(Long id);

	// Registrar
	MarcaEntity add(MarcaEntity obj);

	// Actualizar
	MarcaEntity update(MarcaEntity obj, Long id);

	// Eliminar (logico: estado = false)
	MarcaEntity delete(Long id);

	// Habilitar (estado = true)
	MarcaEntity enable(Long id);
}
