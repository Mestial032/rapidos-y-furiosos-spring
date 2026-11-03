package com.rapidosyfuriosos.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "EstadoCivilEntity")
@Table(name = "estadocivil")
public class EstadoCivilEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codestc")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	// Validaciones del formulario: obligatorio y longitud maxima (UNIQUE en la BD)
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 30, message = "El nombre no debe superar los 30 caracteres")
	@Column(name = "nomestc", length = 30, nullable = false, unique = true)
	private String nombre;

	@Column(name = "estestc", nullable = false)
	private Boolean estado;
}
