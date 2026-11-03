package com.rapidosyfuriosos.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
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
@Entity(name = "ServicioEntity")
@Table(name = "servicio")
public class ServicioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codser")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	// Validaciones del formulario: obligatorio y longitud maxima (UNIQUE en la BD)
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
	@Column(name = "nomser", length = 50, nullable = false, unique = true)
	private String nombre;

	@NotBlank(message = "La descripcion es obligatoria")
	@Size(max = 300, message = "La descripcion no debe superar los 300 caracteres")
	@Column(name = "desser", length = 300, nullable = false)
	private String descripcion;

	@NotNull(message = "El precio es obligatorio")
	@DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
	@Digits(integer = 8, fraction = 2, message = "El precio admite hasta 8 enteros y 2 decimales")
	@Column(name = "preser", nullable = false)
	private Double precio;

	@Column(name = "estser", nullable = false)
	private Boolean estado;
}
