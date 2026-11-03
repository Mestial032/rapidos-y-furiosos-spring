package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.TipoDocumentoEntity;
import com.rapidosyfuriosos.repository.TipoDocumentoRepository;
import com.rapidosyfuriosos.service.TipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private TipoDocumentoRepository repositorio;

	@Override
	public List<TipoDocumentoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<TipoDocumentoEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public TipoDocumentoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public TipoDocumentoEntity add(TipoDocumentoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public TipoDocumentoEntity update(TipoDocumentoEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		TipoDocumentoEntity objTipoDocumento = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objTipoDocumento, "codigo");
		return repositorio.save(objTipoDocumento);
	}

	@Override
	public TipoDocumentoEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		TipoDocumentoEntity objTipoDocumento = repositorio.findById(id).get();
		objTipoDocumento.setEstado(false);
		return repositorio.save(objTipoDocumento);
	}

	@Override
	public TipoDocumentoEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		TipoDocumentoEntity objTipoDocumento = repositorio.findById(id).get();
		objTipoDocumento.setEstado(true);
		return repositorio.save(objTipoDocumento);
	}
}
