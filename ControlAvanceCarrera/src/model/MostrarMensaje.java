package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controlador;
import view.CrearCarrera;

public class MostrarMensaje {
	
	public static void mostrarOk(JFrame frame, String mensaje) {
		JOptionPane.showMessageDialog(frame,
				mensaje,
			    "OK",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mostrarError(JFrame frame, String mensaje) {
		JOptionPane.showMessageDialog(frame,
				mensaje,
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public static void errorNombre(JFrame frame) {
		String mensaje = "El nombre solo puede contener letras"; 
		mostrarError(frame, mensaje);
	}
	
	public static void errorFormatoCreditosMin(JFrame frame) {
		String mensaje = "La cantidad de creditos minimos debe ser un numero entero";
		mostrarError(frame, mensaje);
	}
	
	public static void errorFormatoCreditosMax(JFrame frame) {
		String mensaje = "La cantidad de creditos maximos debe ser un numero entero";
		mostrarError(frame, mensaje);
	}
	
	public static void errorCreditosMax(JFrame frame) {
		String mensaje = "La cantidad de creditos maximos no puede ser menor que los minimos";
		mostrarError(frame, mensaje);
	}
	
	public static void errorCreditosMin(JFrame frame) {
		String mensaje = "La cantidad de creditos minimos de la carrera no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_CARRERA);
		mostrarError(frame, mensaje);
	}
	
	//*******************************************************MENSAJES OK***********************************************************
	
	public static void carreraAgregada(JFrame frame) {
		String mensaje = "Carrera creada con exito";
		mostrarOk(frame, mensaje);
	}
}
