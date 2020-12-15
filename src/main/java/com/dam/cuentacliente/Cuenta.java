package com.dam.cuentacliente;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta {
	@EqualsAndHashCode.Include
	@NonNull
	private String numCuenta;
	private int saldo;
	@Singular
	private List<Cliente> clientes;
}
