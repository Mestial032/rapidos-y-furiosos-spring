package com.rapidosyfuriosos.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rapidosyfuriosos.entity.EmpleadoEntity;
import com.rapidosyfuriosos.repository.EmpleadoRepository;
import com.rapidosyfuriosos.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	// Inyeccion de dependencias hacia el repositorio
	@Autowired
	private EmpleadoRepository repositorio;

	// Cifrado de claves: nunca se guardan en texto plano
	private final PasswordEncoder cifrador = new BCryptPasswordEncoder();

	@Override
	public List<EmpleadoEntity> findAll() {
		return repositorio.findAll();
	}

	@Override
	public List<EmpleadoEntity> findAllCustom() {
		return repositorio.findAllCustom();
	}

	@Override
	public EmpleadoEntity findById(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public EmpleadoEntity add(EmpleadoEntity obj) {
		obj.setClave(cifrador.encode(obj.getClave()));
		return repositorio.save(obj);
	}

	@Override
	public EmpleadoEntity update(EmpleadoEntity obj, Long id) {
		// Buscamos el valor que se va a actualizar
		EmpleadoEntity objEmpleado = repositorio.findById(id).get();
		// Copiamos los nuevos valores (el codigo no se toca)
		// Si no se envia una nueva clave se conserva la clave actual
		if (obj.getClave() == null || obj.getClave().isBlank()) {
			BeanUtils.copyProperties(obj, objEmpleado, "codigo", "clave");
		} else {
			BeanUtils.copyProperties(obj, objEmpleado, "codigo");
			objEmpleado.setClave(cifrador.encode(obj.getClave()));
		}
		return repositorio.save(objEmpleado);
	}

	@Override
	public EmpleadoEntity delete(Long id) {
		// Buscamos el valor que se va a eliminar y cambiamos el estado
		EmpleadoEntity objEmpleado = repositorio.findById(id).get();
		objEmpleado.setEstado(false);
		return repositorio.save(objEmpleado);
	}

	@Override
	public EmpleadoEntity enable(Long id) {
		// Buscamos el valor que se va a habilitar y cambiamos el estado
		EmpleadoEntity objEmpleado = repositorio.findById(id).get();
		objEmpleado.setEstado(true);
		return repositorio.save(objEmpleado);
	}
}
