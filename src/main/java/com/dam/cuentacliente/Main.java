package com.dam.cuentacliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import daw.com.Teclado;

public class Main {

	public static void main(String[] args) {
		//crear cuenta, add cliente a cuenta, delete cliente de cuenta y delete cuenta
		ClienteDAO daoCli = new ClienteDAO();
		CuentaDAO daoCue = new CuentaDAO();
		Cliente cli;
		Cuenta cue;
		
		cue = Cuenta.builder().numCuenta(Teclado.leerString("Numero de cuenta")).saldo(Teclado.leerInt("Saldo")).build();
		if (daoCue.select(cue.getNumCuenta()).isEmpty()) {
			daoCue.insert(cue);
			System.out.println("Cuenta insertada correctamente");
		}
		
		cli = Cliente.builder()
				.dni(Teclado.leerString("DNI"))
				.nombre(Teclado.leerString("Nombre"))
				.fechaNacimiento(LocalDate.parse(Teclado.leerString("FechaNac (YYYY-MM-DD")))
				.aval(Teclado.leerInt("Aval"))
				.numCuenta(cue.getNumCuenta()).build();
		
		if (daoCli.select(cli.getDni()).isEmpty()) {
			daoCli.insert(cli);
			
			System.out.println("Cliente de cuenta " + cli.getNumCuenta() + " insertado correctamente");
		}
		
		if (daoCli.delete(cli) > 0) {
			System.out.println("Cliente borrado correctamente");
			daoCue.delete(cue);
			System.out.println("Cuenta borrada correctamente");
		}
		
		Cuenta cuentaOrigen = leerCuenta();
		Cuenta cuentaDestino = leerCuenta();
		
		boolean exito = transferencia(cuentaOrigen, cuentaDestino);
		
		
		
	}

	private static boolean transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		int cantidadATransferir = Teclado.leerInt("Cantidad a transferir");
		boolean exito = false;
		if (cantidadATransferir > cuentaOrigen.getSaldo()) {
			int avalesClientes;
			
			avalesClientes = cuentaOrigen.getClientes().stream().mapToInt(Cliente::getAval).sum();
			
			if (cantidadATransferir - cuentaOrigen.getSaldo() <= (avalesClientes / 2)) {
				// se permite transferencia pero te quedas en numeros rojos
				insertCuenta(cuentaOrigen, cuentaDestino, cantidadATransferir);
				exito = true;
			}else {
				System.out.println("Cantidad superior a la maxima permitida");
			}
		}else {
			insertCuenta(cuentaOrigen, cuentaDestino, cantidadATransferir);
			exito = true;
		}
		
		return exito;
	}
	
	private static void insertCuenta(Cuenta cuentaOrigen, Cuenta cuentaDestino, int cantidadATransferir) {
		CuentaDAO daoCue = new CuentaDAO();
		
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidadATransferir);
		cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidadATransferir);
		if (daoCue.select(cuentaOrigen.getNumCuenta()).isPresent()) {
			daoCue.update(cuentaOrigen);
		}else {
			daoCue.insert(cuentaOrigen);
		}
	
		if (daoCue.select(cuentaDestino.getNumCuenta()).isPresent()) {
			daoCue.update(cuentaDestino);
		} else {
			daoCue.insert(cuentaDestino);
		}

	}

	private static Cuenta leerCuenta() {
		Cuenta c;
		String numCuenta = Teclado.leerString("Numero de cuenta");
		c = Cuenta.builder()
				.numCuenta(numCuenta)
				.saldo(Teclado.leerInt("Saldo"))
				.clientes(leerClientes(numCuenta))
				.build();
		
		return c;
	}
	
	private static List<Cliente> leerClientes(String numCuenta) {
		List<Cliente> clientes = new ArrayList<>();
		do {
			 Cliente c = Cliente.builder()
						.dni(Teclado.leerString("DNI"))
						.nombre(Teclado.leerString("Nombre"))
						.fechaNacimiento(LocalDate.parse(Teclado.leerString("FechaNac (YYYY-MM-DD)")))
						.aval(Teclado.leerInt("Aval"))
						.numCuenta(numCuenta)
						.build();
			 clientes.add(c);
		}while(Teclado.leerString("Mas clientes? (S/N)").equalsIgnoreCase("S"));
		return clientes;
	}

}
