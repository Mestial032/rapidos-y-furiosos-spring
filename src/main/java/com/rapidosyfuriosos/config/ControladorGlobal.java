package com.rapidosyfuriosos.config;

import java.util.NoSuchElementException;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

// Configuracion comun para todos los controladores web (no afecta a los REST)
@ControllerAdvice(basePackages = "com.rapidosyfuriosos.controller")
public class ControladorGlobal {

	// Quita espacios al inicio y al final de los textos del formulario
	// y convierte los textos vacios en null (para campos opcionales como telefono o correo)
	@InitBinder
	public void limpiarTextos(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	// Cuando se busca un codigo que no existe en la base de datos
	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView registroNoEncontrado() {
		ModelAndView vista = new ModelAndView("errorregistro");
		vista.addObject("error", "El registro solicitado no existe en la base de datos.");
		return vista;
	}
}
