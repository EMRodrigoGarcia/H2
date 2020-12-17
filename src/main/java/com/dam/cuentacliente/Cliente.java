package com.dam.cuentacliente;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
	@EqualsAndHashCode.Include
	@NonNull
	private String dni;
	private String nombre;
	private LocalDate fechaNacimiento;
	private int aval;
	private String numCuenta;
}
