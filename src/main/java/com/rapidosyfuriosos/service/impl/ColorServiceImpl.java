package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.ColorEntity;
import com.rapidosyfuriosos.repository.ColorRepository;
import com.rapidosyfuriosos.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private ColorRepository repositorio;

	@Override
	public List<ColorEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<ColorEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public ColorEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ColorEntity add(ColorEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public ColorEntity update(ColorEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		ColorEntity objColor = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objColor, "codigo");
		return repositorio.save(objColor);
	}

	@Override
	public ColorEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		ColorEntity objColor = repositorio.findById(id).get();
		objColor.setEstado(false);
		return repositorio.save(objColor);
	}

	@Override
	public ColorEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		ColorEntity objColor = repositorio.findById(id).get();
		objColor.setEstado(true);
		return repositorio.save(objColor);
	}
}
