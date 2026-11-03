package com.rapidosyfuriosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rapidosyfuriosos.entity.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {

	// JpaRepository ya trae: findAll, findById, save (registrar/actualizar) y delete

	// Consulta personalizada: solo los registros habilitados
	@Query("select e from EmpleadoEntity e where e.estado = true")
	List<EmpleadoEntity> findAllCustom();
}
