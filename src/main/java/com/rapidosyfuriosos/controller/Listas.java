package com.rapidosyfuriosos.controller;

import java.util.ArrayList;
import java.util.List;

// Utilidad para las listas desplegables de los mantenimientos cruzados
final class Listas {

	private Listas() {
	}

	// Devuelve la lista de habilitados y, si el valor actual del registro
	// esta deshabilitado, lo agrega para que no se pierda al actualizar
	static <T> List<T> conActual(List<T> habilitados, T actual) {
		if (actual == null || habilitados.contains(actual)) {
			return habilitados;
		}
		List<T> lista = new ArrayList<>(habilitados);
		lista.add(actual);
		return lista;
	}
}
