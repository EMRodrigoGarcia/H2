package com.dam.H2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import daw.com.Teclado;
import lombok.Data;
@Data
public class MenuSQL {
	// parametros para conectarse a la bbdd
	private static final String user = "sa";
	private static final String password = "";
	private static final String url = "jdbc:h2:~/test";
	private Connection conexion;
	
	private String[] opcs = {"1. Insertar departamento", "2. Borrar departamento", "3. Seleccionar Departamentos" , "4. Salir"};
	
	public MenuSQL() throws ClassNotFoundException, SQLException {
		// enganchar el driver de la base de datos H2
		Class.forName("org.h2.Driver");
		
		// crear la conexion con dichos parametros
		conexion = DriverManager.getConnection(url, user, password);
		System.out.println("Conexion establecida");
	}
	
	public static void main(String[] args) {
		try {
			MenuSQL menu = new MenuSQL();
			menu.seleccionarAccion();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void seleccionarAccion() throws SQLException {
		//mostrar opciones
		Arrays.stream(opcs).forEach(System.out::println);
		int opc = Teclado.leerInt();
		switch (opc) {
		case 1:
			//insertar departamento
			insertDepart();
			break;
		case 2:
			//borrar departamento
			deleteDepart();
			break;
		case 3:
			//seleccionar departamento
			selectDepart();
			break;
		default:
			this.conexion.close();
			break;
		}
	}

	private void deleteDepart() {
		//TODO: Hacer esto entero (borrar departamento y todos los empleados en cascada)
	}

	private void selectDepart() throws SQLException {
		//pedir dept_no
		String dept_no = Teclado.leerString("dept_no");
		//preparo y ejecuto la query
		String query = "SELECT * FROM depart WHERE dept_no = ?";
		PreparedStatement statementSelect = this.conexion.prepareStatement(query);
		statementSelect.setString(1, dept_no);
		// leo los resultados obtenidos
		ResultSet set = statementSelect.executeQuery();
		
		while (set.next()) {
			String dname = set.getString("dname");
			String dloc = set.getString("dloc");
			System.out.println(Depart.builder().dept_no(dept_no).dname(dname).dloc(dloc).build());
		}
	}

	private void insertDepart() throws SQLException {
		String dept_no = Teclado.leerString("dept_no");
		String query = "SELECT * FROM depart WHERE dept_no = ?";
		
		PreparedStatement statementPruebaInsert = this.conexion.prepareStatement(query);
		statementPruebaInsert.setString(1, dept_no);
		ResultSet set = statementPruebaInsert.executeQuery();
		if (set.next()) {
			System.out.println("El departamento " + dept_no + " ya existe");
		}else {
			
			query = "INSERT INTO depart (dept_no, dname, dloc) VALUES (?, ?, ?)";
			String queryInsertEmple = "INSERT INTO emple (dni, nombre, salario, fecha) VALUES (?, ?, ?, ?)";
			//TODO: Ejecutar querys
		}
		
	}
	
	
}
