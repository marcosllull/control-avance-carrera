package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDeDatos {
	
	private static Connection conexion;
	private static Statement sentencia;
	
	public static void iniciar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}
	
	public static Connection getConexion() {
		
		try {
			if (conexion == null || conexion.isClosed()) {
				try {
					conexion = DriverManager.getConnection("jdbc:mysql://localhost/control_avance_carrera?useSSL=false","root" ,"27088678");
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}
	
	public static Statement getStatement(Connection conexion) {
		if (sentencia == null) {
			try {
				sentencia = conexion.createStatement();
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
			String query = "DELETE FROM carrera "
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
	
	public static void eliminarMateriaBD(String nombre, String nombreCarrera) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "DELETE FROM materia "
					+ "WHERE nombre=? "
					+ "AND nombreCarrera=?";
			PreparedStatement eliminarMateria = conexion.prepareStatement(query);

			eliminarMateria.setString(1, nombre);
			eliminarMateria.setString(2, nombreCarrera);
			
			eliminarMateria.executeUpdate();
			eliminarMateria.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Materia NO eliminada");
		}
	}
	
	public static void eliminarAsignaturaBD(String nombre, String nombreCarrera, boolean eliminarCarrera) {
		try {
			/*Eliminar todas las previas de esta asignatura de la tabla asignatura_previa*/
			BaseDeDatos.eliminarPreviasAsignatura(nombreCarrera, nombre);
			
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			/*Eliminar todas las filas en que esta asignatura es previa de otra de la tabla asignatura_previa*/
			/*SOLO SE HACE SI SE ELIMINA LA CARRERA ASOCIADA, YA QUE UNA ASIGNATURA NO PUEDE ELIMINARSE SI ES PREVIA DE OTRA*/
			String query1 = "DELETE FROM asignatura_previa "
					+ "WHERE nombreCarrera=? "
					+ "AND nombrePrevia=?";
			/*Eliminar la asignatura*/
			String query2 = "DELETE FROM asignatura "
					+ "WHERE nombreCarrera=? "
					+ "AND nombre=?";
			
			PreparedStatement eliminarAsignatura;
			
			if (eliminarCarrera) {
				eliminarAsignatura = conexion.prepareStatement(query1);
				eliminarAsignatura.setString(1, nombreCarrera);
				eliminarAsignatura.setString(2, nombre);
				eliminarAsignatura.executeUpdate();
			}
			
			eliminarAsignatura = conexion.prepareStatement(query2);
			eliminarAsignatura.setString(1, nombreCarrera);
			eliminarAsignatura.setString(2, nombre);
			eliminarAsignatura.executeUpdate();
			
			eliminarAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Asignatura NO eliminada");
		}
	}

	public static void insertarPreviaAsignaturaBD(String nombreCarrera, String nombreAsignatura, String nombrePrevia, TipoPrevia tipoPrevia) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			
			String query = "INSERT INTO asignatura_previa ("
					+ "nombreCarrera, "
					+ "nombreAsignatura, "
					+ "nombrePrevia, "
					+ "tipoPrevia) VALUES ("
					+ "?, ?, ?, ?)";
			PreparedStatement insertarPreviaAsignatura = conexion.prepareStatement(query);
			
			insertarPreviaAsignatura.setString(1, nombreCarrera);
			insertarPreviaAsignatura.setString(2, nombreAsignatura);
			insertarPreviaAsignatura.setString(3, nombrePrevia);
			insertarPreviaAsignatura.setString(4, tipoPrevia.name());
			
			insertarPreviaAsignatura.executeUpdate();
			insertarPreviaAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Previa NO insertada");
		}
	}
	
	public static ArrayList<String[]> getCarreras() {
		
		ArrayList<String[]> carreras = new ArrayList<String[]>();
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			Statement sentencia = conexion.createStatement();
			String query = "SELECT * "
					+ "FROM carrera";
			ResultSet resultado = sentencia.executeQuery(query);
			while (resultado.next()) {
				String[] carrera = {resultado.getString(1), Integer.toString(resultado.getInt(2)), Integer.toString(resultado.getInt(3))};
				carreras.add(carrera);
			}
			sentencia.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Error en la consulta getCarreras()");
		}
		
		return carreras;
	}
	
	public static ArrayList<String[]> getMaterias() {
		
		ArrayList<String[]> materias = new ArrayList<String[]>();
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			Statement sentencia = conexion.createStatement();
			
			String query = "SELECT * "
					+ "FROM materia";

			ResultSet resultado = sentencia.executeQuery(query);
			while (resultado.next()) {
				String[] materia = {resultado.getString(1), resultado.getString(2), Integer.toString(resultado.getInt(3))};
				materias.add(materia);
			}
			sentencia.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Error en la consulta getMaterias()");
		}
		
		return materias;
	}
	
	public static ArrayList<String[]> getAsignaturas() {
		
		ArrayList<String[]> asignaturas = new ArrayList<String[]>();
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			Statement sentencia = conexion.createStatement();
			
			String query = "SELECT * "
					+ "FROM asignatura";

			ResultSet resultado = sentencia.executeQuery(query);
			
			while (resultado.next()) {
				String[] asignatura = {resultado.getString(1), resultado.getString(2), resultado.getString(3), 
						Integer.toString(resultado.getInt(4)), Boolean.toString(resultado.getBoolean(5))};
				asignaturas.add(asignatura);
			}
			sentencia.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Error en la consulta getMaterias()");
		}
		
		return asignaturas;
	}
	
	public static ArrayList<String[]> getAsignaturasConPrevias() {
		
		ArrayList<String[]> asignaturasConPrevias = new ArrayList<String[]>();
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			Statement sentencia = conexion.createStatement();
			
			String query = "SELECT * "
					+ "FROM asignatura_previa";

			ResultSet resultado = sentencia.executeQuery(query);
			
			while (resultado.next()) {
				String[] asignaturaConPrevia = {resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4)};
				asignaturasConPrevias.add(asignaturaConPrevia);
			}
			sentencia.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Error en la consulta getAsignaturasConPrevias()");
		}
		
		return asignaturasConPrevias;
	}
	
	public static void modificarCarreraBD(String nombreAntes, String nombreDespues, int creditosMin, int creditosMax) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			String query = "UPDATE carrera SET nombre=?, cantCreditosMin=?, cantCreditosMax=? WHERE nombre=?";
			
			PreparedStatement modificarCarrera = conexion.prepareStatement(query);
			modificarCarrera.setString(1, nombreDespues);
			modificarCarrera.setInt(2, creditosMin);
			modificarCarrera.setInt(3, creditosMax);
			modificarCarrera.setString(4, nombreAntes);
			modificarCarrera.executeUpdate();

			modificarCarrera.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Carrera NO actualizada");
		}
	}
	
	public static void modificarMateriaBD(String nombreAntes, String nombreDespues, String nombreCarrera, int cantCreditos) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			String query = "UPDATE materia SET nombre=?, nombreCarrera=?, cantCreditos=? WHERE nombre=? && nombreCarrera=?";
			
			PreparedStatement modificarCarrera = conexion.prepareStatement(query);
			modificarCarrera.setString(1, nombreDespues);
			modificarCarrera.setString(2, nombreCarrera);
			modificarCarrera.setInt(3, cantCreditos);
			modificarCarrera.setString(4, nombreAntes);
			modificarCarrera.setString(5, nombreCarrera);
			modificarCarrera.executeUpdate();

			modificarCarrera.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Materia NO actualizada");
		}
	}
	
	public static void modificarAsignaturaBD(String nombreAntes, String nombreDespues, String nombreCarrera, int cantCreditos, boolean tienePrevias) {
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			String query = "UPDATE asignatura SET nombre=?, cantCreditos=?, tienePrevias=? "
					+ "WHERE nombre=? "
					+ "AND nombreCarrera=?";
			
			PreparedStatement modificarAsignatura = conexion.prepareStatement(query);
			modificarAsignatura.setString(1, nombreDespues);
			modificarAsignatura.setInt(2, cantCreditos);
			modificarAsignatura.setBoolean(3, tienePrevias);
			modificarAsignatura.setString(4, nombreAntes);
			modificarAsignatura.setString(5, nombreCarrera);
			
			modificarAsignatura.executeUpdate();

			modificarAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Asignatura NO actualizada");
		}
	}
	
	public static void eliminarPreviasAsignatura(String nombreCarrera, String nombreAsignatura) {
		
		try {
			BaseDeDatos.iniciar();
			Connection conexion = BaseDeDatos.getConexion();
			/*Eliminar todas las previas de esta asignatura de la tabla asignatura_previa*/
			String query1 ="DELETE FROM asignatura_previa "
					+ "WHERE nombreCarrera=? "
					+ "AND nombreAsignatura=?";
			
			
			PreparedStatement eliminarAsignatura;
			
			eliminarAsignatura = conexion.prepareStatement(query1);
			eliminarAsignatura.setString(1, nombreCarrera);
			eliminarAsignatura.setString(2, nombreAsignatura);
			eliminarAsignatura.executeUpdate();
			eliminarAsignatura.close();
			conexion.close();
		} 
		catch (SQLException e) {
			System.out.println("Asignatura NO eliminada");
		}
	}
}
