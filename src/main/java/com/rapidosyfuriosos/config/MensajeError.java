package com.rapidosyfuriosos.config;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;

// Traduce los errores de MySQL (registros duplicados, claves foraneas)
// a mensajes entendibles para mostrarlos en los formularios
public final class MensajeError {

	// Ejemplo de MySQL: Duplicate entry 'Lima' for key 'distrito.nomdis'
	private static final Pattern DUPLICADO = Pattern.compile("Duplicate entry '(.*)' for key '(?:[\\w]+\\.)?([\\w]+)'");

	// Columnas UNIQUE de la base de datos y su nombre descriptivo
	private static final Map<String, String> CAMPOS = Map.ofEntries(
			Map.entry("nomdis", "el nombre"), Map.entry("nomsex", "el nombre"), Map.entry("nomtipd", "el nombre"),
			Map.entry("nomestc", "el nombre"), Map.entry("nommar", "el nombre"), Map.entry("nomcol", "el nombre"),
			Map.entry("nomrol", "el nombre"), Map.entry("nomser", "el nombre"),
			Map.entry("doccli", "el número de documento"), Map.entry("celcli", "el celular"),
			Map.entry("corcli", "el correo"), Map.entry("docemp", "el número de documento"),
			Map.entry("celemp", "el celular"), Map.entry("coremp", "el correo"), Map.entry("usuemp", "el usuario"),
			Map.entry("plaveh", "la placa"));

	private MensajeError() {
	}

	public static String obtener(DataIntegrityViolationException e) {
		String detalle = String.valueOf(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		Matcher m = DUPLICADO.matcher(detalle);
		if (m.find()) {
			String campo = CAMPOS.getOrDefault(m.group(2), "el valor");
			return "Ya existe un registro con " + campo + " '" + m.group(1) + "'. Ingrese un valor diferente.";
		}
		if (detalle.contains("foreign key constraint")) {
			return "No se puede completar la operación: el registro seleccionado no existe o está relacionado con otros datos.";
		}
		if (detalle.contains("Data too long")) {
			return "Uno de los datos ingresados supera la longitud permitida.";
		}
		return "No se pudo guardar el registro por una restricción de la base de datos.";
	}
}
