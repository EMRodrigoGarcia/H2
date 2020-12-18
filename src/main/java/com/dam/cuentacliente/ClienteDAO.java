package com.dam.cuentacliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements DAOInterface<Cliente, String> {



	@Override
	public int insert(Cliente pk) {
		int cuantos = 0;
		String query = "INSERT INTO cliente (dni, nombre, fecha_nac, aval, num_cuenta) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			pstmnt.setString(1, pk.getDni());
			pstmnt.setString(2, pk.getNombre());
			pstmnt.setDate(3, Date.valueOf(pk.getFechaNacimiento()));
			pstmnt.setInt(4, pk.getAval());
			pstmnt.setString(5, pk.getNumCuenta());
			cuantos = pstmnt.executeUpdate();
			ConexionBBDD.desconectar();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cuantos;
	}

	@Override
	public int delete(Cliente pk) {
		int cuantos = 0;
		String query = "DELETE FROM cliente WHERE dni = ?";
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			pstmnt.setString(1, pk.getDni());
			cuantos = pstmnt.executeUpdate();
			ConexionBBDD.desconectar();

		} catch (SQLException e) {
			// : handle exception
			e.printStackTrace();
		}
		return cuantos;
	}

	@Override
	public Optional<Cliente> select(String pk){
		Connection conexion = ConexionBBDD.conectar();
		Optional<Cliente> cliente = Optional.empty();
		String query = "SELECT * FROM cliente WHERE dni = ?";
		try {
			
			PreparedStatement statement = conexion.prepareStatement(query);
			
			statement.setString(1, pk);
			
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				String dni = set.getString("dni");
				String nombre = set.getString("nombre");
				LocalDate fecha = set.getDate("fecha_nac").toLocalDate();
				int aval = set.getInt("aval");
				String numCuenta = set.getString("num_cuenta");
				
				cliente = Optional.ofNullable(Cliente.builder()
						.dni(dni)
						.nombre(nombre)
						.fechaNacimiento(fecha)
						.aval(aval)
						.numCuenta(numCuenta)
						.build());
			}

			ConexionBBDD.desconectar();

			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return cliente;

		
	}

	@Override
	public Iterable<Cliente> selectAll() {
		
		List<Cliente> clientes = new ArrayList<>();
		String query = "SELECT * FROM cliente";
		
		try {
			PreparedStatement statement = ConexionBBDD.conectar().prepareStatement(query);
			
			ResultSet set = statement.executeQuery();
			while(set.next()) {
				String dni = set.getString("dni");
				String nombre = set.getString("nombre");
				LocalDate fecha_nac = set.getDate("fecha_nac").toLocalDate();
				int aval = set.getInt("aval");
				String num_cuenta = set.getString("num_cuenta");
				
				Cliente c = Cliente.builder().dni(dni).nombre(nombre).fechaNacimiento(fecha_nac).aval(aval).numCuenta(num_cuenta).build();
				clientes.add(c);
				ConexionBBDD.desconectar();

			}
		} catch (SQLException  e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return clientes;
	}

	@Override
	public int update(Cliente pk) {
		int cuantos = 0;
		String query = "UPDATE cliente SET nombre = ?, fecha_nac = ?, aval = ?, num_cuenta = ? WHERE dni = ?";
		
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			pstmnt.setString(1, pk.getNombre());
			pstmnt.setDate(2, Date.valueOf(pk.getFechaNacimiento()));
			pstmnt.setInt(3, pk.getAval());
			pstmnt.setString(4, pk.getNumCuenta());
			pstmnt.setString(5, pk.getDni());
			cuantos = pstmnt.executeUpdate();
			
			ConexionBBDD.desconectar();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cuantos;
	}

}
