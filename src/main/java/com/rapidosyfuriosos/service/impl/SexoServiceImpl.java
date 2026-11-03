package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.SexoEntity;
import com.rapidosyfuriosos.repository.SexoRepository;
import com.rapidosyfuriosos.service.SexoService;

@Service
public class SexoServiceImpl implements SexoService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private SexoRepository repositorio;

	@Override
	public List<SexoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<SexoEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public SexoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public SexoEntity add(SexoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public SexoEntity update(SexoEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		SexoEntity objSexo = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objSexo, "codigo");
		return repositorio.save(objSexo);
	}

	@Override
	public SexoEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		SexoEntity objSexo = repositorio.findById(id).get();
		objSexo.setEstado(false);
		return repositorio.save(objSexo);
	}

	@Override
	public SexoEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		SexoEntity objSexo = repositorio.findById(id).get();
		objSexo.setEstado(true);
		return repositorio.save(objSexo);
	}
}
