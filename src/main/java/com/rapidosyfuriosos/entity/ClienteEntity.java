package com.rapidosyfuriosos.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
@Entity(name = "ClienteEntity")
@Table(name = "cliente")
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codcli")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	@Column(name = "nomcli", length = 40, nullable = false)
	private String nombre;

	@NotBlank(message = "El apellido paterno es obligatorio")
	@Size(max = 40, message = "El apellido paterno no debe superar los 40 caracteres")
	@Column(name = "apepcli", length = 40, nullable = false)
	private String apellidoPaterno;

	@NotBlank(message = "El apellido materno es obligatorio")
	@Size(max = 40, message = "El apellido materno no debe superar los 40 caracteres")
	@Column(name = "apemcli", length = 40, nullable = false)
	private String apellidoMaterno;

	@NotBlank(message = "El numero de documento es obligatorio")
	@Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "El documento debe tener entre 8 y 20 caracteres alfanumericos")
	@Column(name = "doccli", length = 20, nullable = false, unique = true)
	private String numeroDocumento;

	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@Past(message = "La fecha de nacimiento debe ser una fecha pasada")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "feccli", nullable = false)
	private LocalDate fechaNacimiento;

	@NotBlank(message = "La nacionalidad es obligatoria")
	@Size(max = 50, message = "La nacionalidad no debe superar los 50 caracteres")
	@Column(name = "naccli", length = 50, nullable = false)
	private String nacionalidad;

	@NotBlank(message = "La direccion es obligatoria")
	@Size(max = 300, message = "La direccion no debe superar los 300 caracteres")
	@Column(name = "dircli", length = 300, nullable = false)
	private String direccion;

	// Opcional (NULL en la BD)
	@Pattern(regexp = "^[0-9]{6,15}$", message = "El telefono debe tener entre 6 y 15 digitos")
	@Column(name = "telcli", length = 15, nullable = true)
	private String telefono;

	@NotBlank(message = "El celular es obligatorio")
	@Pattern(regexp = "^9[0-9]{8}$", message = "El celular debe tener 9 digitos y empezar con 9")
	@Column(name = "celcli", length = 15, nullable = false, unique = true)
	private String celular;

	// Opcional (NULL en la BD) pero unico
	@Email(message = "Ingrese un correo valido")
	@Size(max = 100, message = "El correo no debe superar los 100 caracteres")
	@Column(name = "corcli", length = 100, nullable = true, unique = true)
	private String correo;

	@Column(name = "estcli", nullable = false)
	private Boolean estado;

	// Claves foraneas (relacion de muchos a uno)
	@NotNull(message = "Seleccione un distrito")
	@ManyToOne
	@JoinColumn(name = "coddis", nullable = false)
	private DistritoEntity distrito;

	@NotNull(message = "Seleccione un sexo")
	@ManyToOne
	@JoinColumn(name = "codsex", nullable = false)
	private SexoEntity sexo;

	@NotNull(message = "Seleccione un tipo de documento")
	@ManyToOne
	@JoinColumn(name = "codtipd", nullable = false)
	private TipoDocumentoEntity tipoDocumento;
}
