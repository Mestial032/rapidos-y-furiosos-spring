package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.ClienteEntity;
import com.rapidosyfuriosos.repository.ClienteRepository;
import com.rapidosyfuriosos.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private ClienteRepository repositorio;

	@Override
	public List<ClienteEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<ClienteEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public ClienteEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ClienteEntity add(ClienteEntity obj) {
		return repositorio.save(obj);
	}

	@Override
	public ClienteEntity update(ClienteEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		ClienteEntity objCliente = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		BeanUtils.copyProperties(obj, objCliente, "codigo");
		return repositorio.save(objCliente);
	}

	@Override
	public ClienteEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		ClienteEntity objCliente = repositorio.findById(id).get();
		objCliente.setEstado(false);
		return repositorio.save(objCliente);
	}

	@Override
	public ClienteEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		ClienteEntity objCliente = repositorio.findById(id).get();
		objCliente.setEstado(true);
		return repositorio.save(objCliente);
	}
}
