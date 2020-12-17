package com.dam.cuentacliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class ClienteDAO implements DAOInterface<Cliente, String> {



	@Override
	public int insert(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Cliente> select(String pk) throws SQLException {
		Connection conexion = ConexionBBDD.conectar();
		Optional<Cliente> cliente = Optional.ofNullable(Cliente.builder().build());
		String query = "SELECT * FROM emple WHERE dni = ?";
		PreparedStatement statement = conexion.prepareStatement(query);
		
		statement.setString(1, pk);
		
		ResultSet set = statement.executeQuery();
		
		if (set.next()) {
			String dni = set.getString("dni");
			String nombre = set.getString("nombre");
			LocalDate fecha = set.getDate("fecha_nacimiento").toLocalDate();
			int aval = set.getInt("aval");
			String numCuenta = set.getString("numero_cuenta");
			
			cliente = Optional.ofNullable(Cliente.builder()
					.dni(dni)
					.nombre(nombre)
					.fechaNacimiento(fecha)
					.aval(aval)
					.numCuenta(numCuenta)
					.build());
		}
		
		return cliente;
	}

	@Override
	public Iterable<Cliente> selectAll(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
