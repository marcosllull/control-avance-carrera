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
	
	public static void errorSizeNombreCarrera() {
		String mensaje = "El nombre de la carrera debe contener entre 1-80 caracteres";
		mostrarError(mensaje);
	}
	
	public static void errorSizeNombreMateria() {
		String mensaje = "El nombre de la materia debe contener entre 1-80 caracteres";
		mostrarError(mensaje);
	}
	
	public static void errorSizeNombreAsignatura() {
		String mensaje = "El nombre de la asignatura debe contener entre 1-80 caracteres";
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
	
	public static void errorFaltaEliminarAsignaturasAsociadas() {
		String mensaje = "Debe eliminar todas las asignaturas asociadas a la materia para poder eliminarla";
		mostrarError(mensaje);
	}
	
	public static void errorFaltaSeleccionarCarrera() {
		String mensaje = "Debe seleccionar una carrera";
		mostrarError(mensaje);
	}
	
	public static void errorEsPrevia() {
		String mensaje = "Esta asignatura es previa de una o mas asignaturas. Debe eliminarlas para poder eliminar esta";
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
	
	public static void materiaEliminada() {
		String mensaje = "Materia eliminada con exito";
		mostrarOk(mensaje);
	}
	
	public static void asignaturaEliminada() {
		String mensaje = "Asignatura eliminada con exito";
		mostrarOk(mensaje);
	}
	
	public static void carreraModificada() {
		String mensaje = "Carrera modificada con exito";
		mostrarOk(mensaje);
	}
	
	public static void materiaModificada() {
		String mensaje = "Materia modificada con exito";
		mostrarOk(mensaje);
	}
	
	public static void asignaturaModificada() {
		String mensaje = "Asignatura modificada con exito";
		mostrarOk(mensaje);
	}
}
