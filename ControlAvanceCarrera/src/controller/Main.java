package controller;

import view.Inicio;

public class Main {
	
	public void iniciarVentana() {
		Inicio.getInstancia();
	}
	
	public static void main(String[] args) {

		Main m = new Main();
		
		Controlador.cargarCarrerasBD();
		Controlador.cargarMateriasBD();
		Controlador.cargarAsignaturasBD();
		m.iniciarVentana();
	}
}
