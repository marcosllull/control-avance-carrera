package model;

import java.awt.Font;

public class Fuente {
	public static Font titulo() {
		Font fuente = new Font("Arial", Font.BOLD, 24);
		return fuente;
	}
	
	public static Font subTitulo() {
		Font fuente = new Font("Arial", Font.PLAIN, 18);
		return fuente;
	}
	
	public static Font total() {
		Font fuente = new Font("Arial", Font.PLAIN, 20);
		return fuente;
	}
	
	public static Font mensajes() {
		Font fuente = new Font("Arial", Font.PLAIN, 20);
		return fuente;
	}
	public static Font salir() {
		Font fuente = new Font("Arial", Font.BOLD, 20);
		return fuente;
	}
	
	public static Font tabla() {
		Font fuente = new Font("Arial", Font.PLAIN, 16);
		return fuente;
	}
	
	public static Font tablaHeader() {
		Font fuente = new Font("Arial", Font.PLAIN, 20);
		return fuente;
	}
}
