package com.rapidosyfuriosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rapidosyfuriosos.entity.MarcaEntity;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {

	// JpaRepository ya trae: findAll, findById, save (registrar/actualizar) y delete

	// Consulta personalizada: solo los registros habilitados
	@Query("select m from MarcaEntity m where m.estado = true")
	List<MarcaEntity> findAllCustom();
}
