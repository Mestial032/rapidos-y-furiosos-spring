package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.DistritoEntity;
import com.rapidosyfuriosos.repository.DistritoRepository;
import com.rapidosyfuriosos.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private DistritoRepository repositorio;

	@Override
	public List<DistritoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<DistritoEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public DistritoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public DistritoEntity add(DistritoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public DistritoEntity update(DistritoEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		DistritoEntity objDistrito = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objDistrito, "codigo");
		return repositorio.save(objDistrito);
	}

	@Override
	public DistritoEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		DistritoEntity objDistrito = repositorio.findById(id).get();
		objDistrito.setEstado(false);
		return repositorio.save(objDistrito);
	}

	@Override
	public DistritoEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		DistritoEntity objDistrito = repositorio.findById(id).get();
		objDistrito.setEstado(true);
		return repositorio.save(objDistrito);
	}
}
