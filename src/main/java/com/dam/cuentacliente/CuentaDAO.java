package com.dam.cuentacliente;

import java.sql.SQLException;
import java.util.Optional;

public class CuentaDAO implements DAOInterface<Cuenta, String> {

	@Override
	public Optional<Cuenta> select(String pk) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Cuenta> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Cuenta pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Cuenta pk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
