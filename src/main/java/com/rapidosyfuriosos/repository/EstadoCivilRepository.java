package com.rapidosyfuriosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rapidosyfuriosos.entity.EstadoCivilEntity;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivilEntity, Long> {

	// JpaRepository ya trae: findAll, findById, save (registrar/actualizar) y delete

	// Consulta personalizada: solo los registros habilitados
	@Query("select e from EstadoCivilEntity e where e.estado = true")
	List<EstadoCivilEntity> findAllCustom();
}
