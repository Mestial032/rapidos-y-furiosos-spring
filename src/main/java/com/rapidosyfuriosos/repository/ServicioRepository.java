package com.rapidosyfuriosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rapidosyfuriosos.entity.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long> {

	// JpaRepository ya trae: findAll, findById, save (registrar/actualizar) y delete

	// Consulta personalizada: solo los registros habilitados
	@Query("select s from ServicioEntity s where s.estado = true")
	List<ServicioEntity> findAllCustom();
}
