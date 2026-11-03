package com.rapidosyfuriosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rapidosyfuriosos.entity.DistritoEntity;

public interface DistritoRepository extends JpaRepository<DistritoEntity, Long> {

	// JpaRepository ya trae: findAll, findById, save (registrar/actualizar) y delete

	// Consulta personalizada: solo los registros habilitados
	@Query("select d from DistritoEntity d where d.estado = true")
	List<DistritoEntity> findAllCustom();
}
