package com.dam.cuentacliente;

import java.sql.SQLException;
import java.util.Optional;

public interface DAOInterface<V, T> {
	public Optional<V> select(T pk);
	public Iterable<V> selectAll();
	public int insert(V pk);
	public int delete(V pk);
	public int update(V pk);

}
