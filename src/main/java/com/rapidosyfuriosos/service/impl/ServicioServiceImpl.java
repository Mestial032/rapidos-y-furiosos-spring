package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.ServicioEntity;
import com.rapidosyfuriosos.repository.ServicioRepository;
import com.rapidosyfuriosos.service.ServicioService;

@Service
public class ServicioServiceImpl implements ServicioService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private ServicioRepository repositorio;

	@Override
	public List<ServicioEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<ServicioEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public ServicioEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ServicioEntity add(ServicioEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public ServicioEntity update(ServicioEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		ServicioEntity objServicio = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objServicio, "codigo");
		return repositorio.save(objServicio);
	}

	@Override
	public ServicioEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		ServicioEntity objServicio = repositorio.findById(id).get();
		objServicio.setEstado(false);
		return repositorio.save(objServicio);
	}

	@Override
	public ServicioEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		ServicioEntity objServicio = repositorio.findById(id).get();
		objServicio.setEstado(true);
		return repositorio.save(objServicio);
	}
}
