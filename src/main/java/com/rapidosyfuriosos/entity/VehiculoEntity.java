package com.rapidosyfuriosos.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "VehiculoEntity")
@Table(name = "vehiculo")
public class VehiculoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codveh")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "La placa es obligatoria")
	@Pattern(regexp = "^[A-Za-z0-9-]{6,20}$", message = "La placa debe tener entre 6 y 20 caracteres (letras, numeros o guion)")
	@Column(name = "plaveh", length = 20, nullable = false, unique = true)
	private String placa;

	@NotBlank(message = "El modelo es obligatorio")
	@Size(max = 50, message = "El modelo no debe superar los 50 caracteres")
	@Column(name = "modveh", length = 50, nullable = false)
	private String modelo;

	@NotNull(message = "El año es obligatorio")
	@Min(value = 1950, message = "El año debe ser mayor o igual a 1950")
	@Max(value = 2027, message = "El año no debe ser mayor a 2027")
	@Column(name = "anioveh", nullable = false)
	private Integer anio;

	@Column(name = "estveh", nullable = false)
	private Boolean estado;

	// Claves foraneas (relacion de muchos a uno)
	@NotNull(message = "Seleccione una marca")
	@ManyToOne
	@JoinColumn(name = "codmar", nullable = false)
	private MarcaEntity marca;

	@NotNull(message = "Seleccione un color")
	@ManyToOne
	@JoinColumn(name = "codcol", nullable = false)
	private ColorEntity color;

	@NotNull(message = "Seleccione un cliente")
	@ManyToOne
	@JoinColumn(name = "codcli", nullable = false)
	private ClienteEntity cliente;
}
