package com.dam.cuentacliente;

import java.sql.SQLException;
import java.util.Optional;

public interface DAOInterface<V, T> {
	public Optional<V> select(T pk) throws SQLException;
	public Iterable<V> selectAll(T pk);
	public int insert(T pk);
	public int delete(T pk);

}
