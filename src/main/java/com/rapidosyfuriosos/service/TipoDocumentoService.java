package com.rapidosyfuriosos.service;

import java.util.List;

import com.rapidosyfuriosos.entity.TipoDocumentoEntity;

public interface TipoDocumentoService {

	// Mostrar todos
	List<TipoDocumentoEntity> findAll();

	// Mostrar solo habilitados
	List<TipoDocumentoEntity> findAllCustom();

	// Buscar por codigo
	TipoDocumentoEntity findById(Long id);

	// Registrar
	TipoDocumentoEntity add(TipoDocumentoEntity obj);

	// Actualizar
	TipoDocumentoEntity update(TipoDocumentoEntity obj, Long id);

	// Eliminar (logico: estado = false)
	TipoDocumentoEntity delete(Long id);

	// Habilitar (estado = true)
	TipoDocumentoEntity enable(Long id);
}
