package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.RolEntity;
import com.rapidosyfuriosos.repository.RolRepository;
import com.rapidosyfuriosos.service.RolService;

@Service
public class RolServiceImpl implements RolService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private RolRepository repositorio;

	@Override
	public List<RolEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<RolEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public RolEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public RolEntity add(RolEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public RolEntity update(RolEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		RolEntity objRol = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objRol, "codigo");
		return repositorio.save(objRol);
	}

	@Override
	public RolEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		RolEntity objRol = repositorio.findById(id).get();
		objRol.setEstado(false);
		return repositorio.save(objRol);
	}

	@Override
	public RolEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		RolEntity objRol = repositorio.findById(id).get();
		objRol.setEstado(true);
		return repositorio.save(objRol);
	}
}
