package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {
	
	private static Connection conexion;
	private static Statement sentencia;
	
	public static void iniciar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver encontrado");
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	
	public static Connection getConexion() {
		
		if (conexion == null) {
			try {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/control_avance_carrera","root" ,"27088678");
				System.out.println("Conexion exitosa");
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}
	
	public static Statement getStatement(Connection conexion) {
		if (sentencia == null) {
			try {
				sentencia = conexion.createStatement();
				System.out.println("Statement creado");
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sentencia;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void insertarCarreraBD(String nombre, int creditosMin, int creditosMax) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "INSERT INTO carrera ("
					+ "nombre, "
					+ "cantCreditosMin, "
					+ "cantCreditosMax) VALUES ("
					+ "?, ?, ?)";
			PreparedStatement insertarCarrera = conexion.prepareStatement(query);

			insertarCarrera.setString(1, nombre);
			insertarCarrera.setInt(2, creditosMin);
			insertarCarrera.setInt(3, creditosMax);
			
			insertarCarrera.executeUpdate();
			insertarCarrera.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Carrera NO insertada");
		}
	}
	
	public static void insertarMateriaBD(String nombre, String nombreCarrera, int cantCreditos) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "INSERT INTO materia ("
					+ "nombre, "
					+ "nombreCarrera, "
					+ "cantCreditos) VALUES ("
					+ "?, ?, ?)";
			PreparedStatement insertarMateria = conexion.prepareStatement(query);

			insertarMateria.setString(1, nombre);
			insertarMateria.setString(2, nombreCarrera);
			insertarMateria.setInt(3, cantCreditos);
			
			insertarMateria.executeUpdate();
			insertarMateria.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Materia NO insertada");
		}
	}
	
	public static void insertarAsignaturaBD(String nombre, String nombreCarrera, String nombreMateria, int cantCreditos, boolean tienePrevias) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "INSERT INTO asignatura ("
					+ "nombre, "
					+ "nombreCarrera, "
					+ "nombreMateria, "
					+ "cantCreditos, "
					+ "tienePrevias) VALUES ("
					+ "?, ?, ?, ?, ?)";
			PreparedStatement insertarAsignatura = conexion.prepareStatement(query);
			
			insertarAsignatura.setString(1, nombre);
			insertarAsignatura.setString(2, nombreCarrera);
			insertarAsignatura.setString(3, nombreMateria);
			insertarAsignatura.setInt(4, cantCreditos);
			insertarAsignatura.setBoolean(5, tienePrevias);
			
			insertarAsignatura.executeUpdate();
			insertarAsignatura.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Asignatura NO insertada");
		}
	}
	
	public static void eliminarCarreraBD(String nombre) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "DELETE FROM carrera"
					+ "WHERE nombre=?";
			PreparedStatement eliminarCarrera = conexion.prepareStatement(query);

			eliminarCarrera.setString(1, nombre);
			
			eliminarCarrera.executeUpdate();
			eliminarCarrera.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Carrera NO eliminada");
		}
	}
	
	public static void eliminarMateriaBD(String nombre) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "DELETE FROM materia"
					+ "WHERE nombre=?";
			PreparedStatement eliminarMateria = conexion.prepareStatement(query);

			eliminarMateria.setString(1, nombre);
			
			eliminarMateria.executeUpdate();
			eliminarMateria.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Materia NO eliminada");
		}
	}
	
	public static void eliminarAsignaturaBD(String nombre) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "DELETE FROM asignatura"
					+ "WHERE nombre=?";
			PreparedStatement eliminarAsignatura = conexion.prepareStatement(query);

			eliminarAsignatura.setString(1, nombre);
			
			eliminarAsignatura.executeUpdate();
			eliminarAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Asignatura NO eliminada");
		}
	}
	
	public static void eliminarPreviaAsignaturaBD(String nombreCarrera, String nombreAsignatura) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "DELETE FROM asignatura_previa"
					+ "WHERE nombreCarrera=? AND"
					+ "nombreAsignatura=?";
			PreparedStatement eliminarPreviaAsignatura = conexion.prepareStatement(query);

			eliminarPreviaAsignatura.setString(1, nombreCarrera);
			eliminarPreviaAsignatura.setString(2, nombreAsignatura);
			
			eliminarPreviaAsignatura.executeUpdate();
			eliminarPreviaAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Previas NO eliminadas");
		}
	}

	public static void insertarPreviaAsignaturaBD(String nombreCarrera, String nombreAsignatura, String nombrePrevia) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "INSERT INTO asignatura_previa ("
					+ "nombreCarrera, "
					+ "nombreAsignatura, "
					+ "nombrePrevia VALUES ("
					+ "?, ?, ?)";
			PreparedStatement insertarPreviaAsignatura = conexion.prepareStatement(query);

			insertarPreviaAsignatura.setString(1, nombreCarrera);
			insertarPreviaAsignatura.setString(2, nombreAsignatura);
			insertarPreviaAsignatura.setString(3, nombrePrevia);
			
			insertarPreviaAsignatura.executeUpdate();
			insertarPreviaAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Previa NO insertada");
		}
	}
}
