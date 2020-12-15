package com.dam.H2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main2 {

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
		System.out.println("Num de departamento");
		String numDepartamento = sc.nextLine();
		sc.close();
		
		
		// crear la query
		String query = "select depart.dname, depart.dloc, "
				+ " emple.dni, emple.nombre, emple.sueldo "
				+ "from depart "
				+ "inner join emple on depart.dept_no = emple.dept_no " 
				+ "where depart.dept_no = ?";
		
		// preparar la query para que no nos hackeen
		PreparedStatement statement = conexion.prepareStatement(query);
		
		statement.setString(1, numDepartamento);
		// ejecutar query
		ResultSet set = statement.executeQuery();
		while (set.next()) {
			String dname = set.getString("dname");
			String dloc = set.getString("dloc");

			String dni = set.getString("dni");
			String nombre = set.getString("nombre");
			float sueldo = set.getFloat("sueldo");
			
			Depart d = Depart.builder().dname(dname).dept_no(numDepartamento).dloc(dloc).empleado(Emple.builder().dni(dni).nombre(nombre).salario(sueldo).build()).build();
			System.out.println(d);
		}
	
		
		conexion.close();

	}

}
