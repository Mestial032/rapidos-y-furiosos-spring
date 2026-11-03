package com.rapidosyfuriosos.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
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
@Entity(name = "EmpleadoEntity")
@Table(name = "empleado")
public class EmpleadoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codemp")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	@Column(name = "nomemp", length = 40, nullable = false)
	private String nombre;

	@NotBlank(message = "El apellido paterno es obligatorio")
	@Size(max = 40, message = "El apellido paterno no debe superar los 40 caracteres")
	@Column(name = "apepemp", length = 40, nullable = false)
	private String apellidoPaterno;

	@NotBlank(message = "El apellido materno es obligatorio")
	@Size(max = 40, message = "El apellido materno no debe superar los 40 caracteres")
	@Column(name = "apememp", length = 40, nullable = false)
	private String apellidoMaterno;

	@NotBlank(message = "El numero de documento es obligatorio")
	@Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "El documento debe tener entre 8 y 20 caracteres alfanumericos")
	@Column(name = "docemp", length = 20, nullable = false, unique = true)
	private String numeroDocumento;

	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@Past(message = "La fecha de nacimiento debe ser una fecha pasada")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecemp", nullable = false)
	private LocalDate fechaNacimiento;

	@NotBlank(message = "La nacionalidad es obligatoria")
	@Size(max = 50, message = "La nacionalidad no debe superar los 50 caracteres")
	@Column(name = "nacemp", length = 50, nullable = false)
	private String nacionalidad;

	@NotBlank(message = "La direccion es obligatoria")
	@Size(max = 300, message = "La direccion no debe superar los 300 caracteres")
	@Column(name = "diremp", length = 300, nullable = false)
	private String direccion;

	@NotBlank(message = "El telefono es obligatorio")
	@Pattern(regexp = "^[0-9]{6,15}$", message = "El telefono debe tener entre 6 y 15 digitos")
	@Column(name = "telemp", length = 15, nullable = false)
	private String telefono;

	@NotBlank(message = "El celular es obligatorio")
	@Pattern(regexp = "^9[0-9]{8}$", message = "El celular debe tener 9 digitos y empezar con 9")
	@Column(name = "celemp", length = 15, nullable = false, unique = true)
	private String celular;

	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "Ingrese un correo valido")
	@Size(max = 100, message = "El correo no debe superar los 100 caracteres")
	@Column(name = "coremp", length = 100, nullable = false, unique = true)
	private String correo;

	@NotBlank(message = "El usuario es obligatorio")
	@Size(min = 4, max = 40, message = "El usuario debe tener entre 4 y 40 caracteres")
	@Column(name = "usuemp", length = 40, nullable = false, unique = true)
	private String usuario;

	// La clave es obligatoria al registrar; al actualizar se puede dejar en blanco
	// para conservar la actual (se valida en el controlador y en el servicio).
	// Se guarda cifrada con BCrypt y la API REST la recibe pero nunca la devuelve
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 4, max = 255, message = "La clave debe tener entre 4 y 255 caracteres")
	@Column(name = "claemp", length = 255, nullable = false)
	private String clave;

	@NotNull(message = "El sueldo es obligatorio")
	@DecimalMin(value = "0.01", message = "El sueldo debe ser mayor a 0")
	@Digits(integer = 8, fraction = 2, message = "El sueldo admite hasta 8 enteros y 2 decimales")
	@Column(name = "sueemp", nullable = false)
	private Double sueldo;

	@Column(name = "estemp", nullable = false)
	private Boolean estado;

	// Claves foraneas (relacion de muchos a uno)
	@NotNull(message = "Seleccione un distrito")
	@ManyToOne
	@JoinColumn(name = "coddis", nullable = false)
	private DistritoEntity distrito;

	@NotNull(message = "Seleccione un rol")
	@ManyToOne
	@JoinColumn(name = "codrol", nullable = false)
	private RolEntity rol;

	@NotNull(message = "Seleccione un sexo")
	@ManyToOne
	@JoinColumn(name = "codsex", nullable = false)
	private SexoEntity sexo;

	@NotNull(message = "Seleccione un tipo de documento")
	@ManyToOne
	@JoinColumn(name = "codtipd", nullable = false)
	private TipoDocumentoEntity tipoDocumento;

	@NotNull(message = "Seleccione un estado civil")
	@ManyToOne
	@JoinColumn(name = "codestc", nullable = false)
	private EstadoCivilEntity estadoCivil;
}
