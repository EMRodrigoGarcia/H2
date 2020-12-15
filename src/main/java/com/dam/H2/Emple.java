package com.dam.H2;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Emple {
	@NonNull
	@EqualsAndHashCode.Include
	private String dni;
	private String nombre;
	private float salario;
	private LocalDate fecha;
	
}
