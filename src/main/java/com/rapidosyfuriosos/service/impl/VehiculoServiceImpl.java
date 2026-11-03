package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.VehiculoEntity;
import com.rapidosyfuriosos.repository.VehiculoRepository;
import com.rapidosyfuriosos.service.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private VehiculoRepository repositorio;

	@Override
	public List<VehiculoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<VehiculoEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public VehiculoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public VehiculoEntity add(VehiculoEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public VehiculoEntity update(VehiculoEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		VehiculoEntity objVehiculo = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objVehiculo, "codigo");
		return repositorio.save(objVehiculo);
	}

	@Override
	public VehiculoEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		VehiculoEntity objVehiculo = repositorio.findById(id).get();
		objVehiculo.setEstado(false);
		return repositorio.save(objVehiculo);
	}

	@Override
	public VehiculoEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		VehiculoEntity objVehiculo = repositorio.findById(id).get();
		objVehiculo.setEstado(true);
		return repositorio.save(objVehiculo);
	}
}
