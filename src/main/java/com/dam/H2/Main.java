package com.dam.H2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	
		// parametros para conectarse a la bbdd
		final String user = "sa";
		final String password = "";
		final String url = "jdbc:h2:~/test";
		// enganchar el driver de la base de datos H2
		Class.forName("org.h2.Driver");
		
		// crear la conexion con dichos parametros
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		System.out.println("Conexion establecida");
		
		// leer la localizacion 
		String loc = leerLoc();
		
		// crear la query
		String query = "SELECT * FROM DEPART WHERE dloc = ?;";
		
		// preparar la query para que no nos hackeen
		PreparedStatement statement = conexion.prepareStatement(query);
		
		// ponerle el valor al comodin "?"
		statement.setString(1, loc);
		
		// ejecutar query
		ResultSet set = statement.executeQuery();
		
		
		// leer resultados
		while (set.next()) {
			// leer resultados por cada fila
			String dept_no = set.getString("dept_no");
			String dname = set.getString("dname");
			String dloc = set.getString("dloc");
			// construir pojo con dichos datos
			Depart d = Depart.builder()
					.dept_no(dept_no)
					.dname(dname)
					.dloc(dloc)
					.build();
			
			
			System.out.println(d);
		}
		// cerrar conexion
		conexion.close();
		
	}
	public static String leerLoc() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Localizacion");
		String loc = sc.nextLine();
		
		sc.close();
		return loc;
	}

}
