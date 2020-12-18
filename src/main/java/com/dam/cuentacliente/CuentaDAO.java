package com.dam.cuentacliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaDAO implements DAOInterface<Cuenta, String> {

	@Override
	public Optional<Cuenta> select(String pk) {
		Optional <Cuenta> OpCuenta = Optional.empty();
		String query = "SELECT * FROM cuenta WHERE NUM_CUENTA = ?";
		
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			pstmnt.setString(1, pk);
			ResultSet set = pstmnt.executeQuery();
			
			while(set.next()) {
				String num_cuenta = set.getString("num_cuenta");
				int saldo = set.getInt("saldo");
				
				OpCuenta = Optional.ofNullable(Cuenta.builder().numCuenta(num_cuenta).saldo(saldo).build());
				ConexionBBDD.desconectar();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return OpCuenta;

	}

	@Override
	public Iterable<Cuenta> selectAll() {
		List<Cuenta> cuentas = new ArrayList<>();
		String query = "SELECT * FROM cuenta";
		
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			ResultSet set = pstmnt.executeQuery();
			
			while(set.next()) {
				cuentas.add(Cuenta.builder().numCuenta(set.getString("num_cuenta")).saldo(set.getInt("saldo")).build());
			}
			ConexionBBDD.desconectar();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cuentas;
	}

	@Override
	public int insert(Cuenta pk) {
		int cuantos = 0;
		String query = "INSERT INTO cuenta (num_cuenta, saldo) VALUES (?, ?)";
		
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			
			pstmnt.setString(1, pk.getNumCuenta());
			pstmnt.setInt(2, pk.getSaldo());
			
			cuantos = pstmnt.executeUpdate();
			ConexionBBDD.desconectar();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cuantos;
	}

	@Override
	public int delete(Cuenta pk) {
		int cuantos = 0;
		String query = "DELETE FROM cuenta WHERE num_cuenta = ?";
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			pstmnt.setString(1, pk.getNumCuenta());
			cuantos = pstmnt.executeUpdate();
			ConexionBBDD.desconectar();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cuantos;
	}

	@Override
	public int update(Cuenta pk) {
		int cuantos = 0;
		String query = "UPDATE cuenta SET saldo = ? WHERE num_cuenta = ?";
		
		try {
			PreparedStatement pstmnt = ConexionBBDD.conectar().prepareStatement(query);
			pstmnt.setInt(1, pk.getSaldo());
			pstmnt.setString(2, pk.getNumCuenta());
			cuantos = pstmnt.executeUpdate();
			ConexionBBDD.desconectar();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cuantos;
	}

}
