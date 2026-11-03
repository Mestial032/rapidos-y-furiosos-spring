package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.EstadoCivilEntity;
import com.rapidosyfuriosos.repository.EstadoCivilRepository;
import com.rapidosyfuriosos.service.EstadoCivilService;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private EstadoCivilRepository repositorio;

	@Override
	public List<EstadoCivilEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<EstadoCivilEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public EstadoCivilEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public EstadoCivilEntity add(EstadoCivilEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public EstadoCivilEntity update(EstadoCivilEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		EstadoCivilEntity objEstadoCivil = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objEstadoCivil, "codigo");
		return repositorio.save(objEstadoCivil);
	}

	@Override
	public EstadoCivilEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		EstadoCivilEntity objEstadoCivil = repositorio.findById(id).get();
		objEstadoCivil.setEstado(false);
		return repositorio.save(objEstadoCivil);
	}

	@Override
	public EstadoCivilEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		EstadoCivilEntity objEstadoCivil = repositorio.findById(id).get();
		objEstadoCivil.setEstado(true);
		return repositorio.save(objEstadoCivil);
	}
}
