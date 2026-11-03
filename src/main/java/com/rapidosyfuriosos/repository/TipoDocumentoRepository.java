package com.rapidosyfuriosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rapidosyfuriosos.entity.TipoDocumentoEntity;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Long> {

	// JpaRepository ya trae: findAll, findById, save (registrar/actualizar) y delete

	// Consulta personalizada: solo los registros habilitados
	@Query("select t from TipoDocumentoEntity t where t.estado = true")
	List<TipoDocumentoEntity> findAllCustom();
}
