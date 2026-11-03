package com.rapidosyfuriosos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller -> controlador de la aplicacion web
@Controller
public class InicioController {

	// @GetMapping -> sirve para trabajar rutas y algunas acciones
	// @PostMapping -> sirve para trabajar las acciones de un boton

	// creamos una ruta para el index
	@GetMapping
	public String MostrarInicio() {
		return "index";
	}

	// creamos una ruta para el menu
	@GetMapping("/menuprincipal")
	public String MostrarMenuPrincipal() {
		return "menuprincipal";
	}
}
