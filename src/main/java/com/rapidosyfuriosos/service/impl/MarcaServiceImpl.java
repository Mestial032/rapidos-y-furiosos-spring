package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.MarcaEntity;
import com.rapidosyfuriosos.repository.MarcaRepository;
import com.rapidosyfuriosos.service.MarcaService;

@Service
public class MarcaServiceImpl implements MarcaService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private MarcaRepository repositorio;

	@Override
	public List<MarcaEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<MarcaEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public MarcaEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public MarcaEntity add(MarcaEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public MarcaEntity update(MarcaEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		MarcaEntity objMarca = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objMarca, "codigo");
		return repositorio.save(objMarca);
	}

	@Override
	public MarcaEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		MarcaEntity objMarca = repositorio.findById(id).get();
		objMarca.setEstado(false);
		return repositorio.save(objMarca);
	}

	@Override
	public MarcaEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		MarcaEntity objMarca = repositorio.findById(id).get();
		objMarca.setEstado(true);
		return repositorio.save(objMarca);
	}
}
