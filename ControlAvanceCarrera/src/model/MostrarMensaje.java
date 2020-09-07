package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controlador;

public class MostrarMensaje {
	
	public static void mostrarOk(String mensaje) {
		JOptionPane.showMessageDialog(null,
				mensaje,
			    "OK",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mostrarError(String mensaje) {
		JFrame defaultFrame = new JFrame();
		JOptionPane.showMessageDialog(defaultFrame,
				mensaje,
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public static void errorNombre() {
		String mensaje = "El nombre solo puede contener letras"; 
		mostrarError(mensaje);
	}
	
	public static void errorFormatoCreditosMin() {
		String mensaje = "La cantidad de creditos minimos debe ser un numero entero";
		mostrarError(mensaje);
	}
	
	public static void errorFormatoCreditosMax() {
		String mensaje = "La cantidad de creditos maximos debe ser un numero entero";
		mostrarError(mensaje);
	}
	
	public static void errorFormatoCreditos() {
		String mensaje = "La cantidad de creditos debe ser un numero entero";
		mostrarError(mensaje);
	}
	
	public static void errorCreditosMax() {
		String mensaje = "La cantidad de creditos maximos no puede ser menor que los minimos";
		mostrarError(mensaje);
	}
	
	public static void errorCreditosMinCarrera() {
		String mensaje = "La cantidad de creditos minimos de la carrera no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_CARRERA);
		mostrarError(mensaje);
	}
	
	public static void errorCreditosMinMateria() {
		String mensaje = "La cantidad de creditos de una materia no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_MATERIA);
		mostrarError(mensaje);
	}
	
	public static void errorExisteCarrera() {
		String mensaje = "La carrera ya existe";
		mostrarError(mensaje);
	}
	
	public static void errorExisteMateria() {
		String mensaje = "La materia ya existe";
		mostrarError(mensaje);
	}
	
	public static void errorNoExistenCarreras() {
		String mensaje = "No existen carreras en el sistema";
		mostrarError(mensaje);
	}
	
	public static void errorAsignaturaAgregada() {
		String mensaje = "La asignatura ya se encuentra agregada";
		mostrarError(mensaje);
	}
	
	public static void errorPreviasSinAgregar() {
		String mensaje = "Debe agregar al menos una previa";
		mostrarError(mensaje);
	}
	
	public static void errorNoExistenCarrerasConMaterias() {
		String mensaje = "Debe existir al menos una carrera que tenga una materia";
		mostrarError(mensaje);
	}
	
	public static void errorImposible() {
		String mensaje = "ERROR inesperado. Comuniquese con desarrollo";
		mostrarError(mensaje);
	}
	
	public static void debug(String mensaje) {
		mostrarError(mensaje);
	}
	
	//*******************************************************MENSAJES OK***********************************************************
	
	public static void carreraAgregada() {
		String mensaje = "Carrera creada con exito";
		mostrarOk(mensaje);
	}
	
	public static void materiaAgregada() {
		String mensaje = "Materia creada con exito";
		mostrarOk(mensaje);
	}
	
	public static void asignaturaAgregada() {
		String mensaje = "Asignatura creada con exito";
		mostrarOk(mensaje);
	}

	public static void carreraEliminada() {
		String mensaje = "Carrera eliminada con exito";
		mostrarOk(mensaje);
	}
}
