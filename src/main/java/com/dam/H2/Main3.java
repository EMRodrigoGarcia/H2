package com.dam.H2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// parametros para conectarse a la bbdd
		final String user = "sa";
		final String password = "";
		final String url = "jdbc:h2:~/test";
		// enganchar el driver de la base de datos H2
		Class.forName("org.h2.Driver");
		
		// crear la conexion con dichos parametros
		Connection conexion = DriverManager.getConnection(url, user, password);
		
		System.out.println("Conexion establecida");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("dept_no");
		String dept_no = sc.nextLine();
		sc.close();
		
		Depart depart = Depart.builder()
				.dept_no(dept_no)
				.dname("ingles")
				.dloc("Madrid")
				.build();
		
		String query1 = "SELECT * FROM depart WHERE dept_no = ?;";
		
		PreparedStatement statement = conexion.prepareStatement(query1);
		
		statement.setString(1, dept_no);
		
		ResultSet setSelect = statement.executeQuery();
		
		if (setSelect.next()) {
			// la fila ya existe. no se puede insertar
			System.out.println("El departamento " + dept_no + "ya existe.");
		}else {
			
			query1 = "INSERT INTO depart (dept_no, dname, dloc)"
					+ " VALUES (?, ?, ?)";
			PreparedStatement statementInsert = conexion.prepareStatement(query1);
			statementInsert.setString(1, dept_no);
			statementInsert.setString(2, depart.getDname());
			statementInsert.setString(3, depart.getDloc());
			int nCambios = statementInsert.executeUpdate();
			System.out.println("Departamento insertado correctamente.");
		}
		
		conexion.close();

	}

}
