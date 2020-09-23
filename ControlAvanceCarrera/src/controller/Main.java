package controller;

import view.Inicio;

import model.Asignatura;
import model.Materia;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public void iniciarVentana() {
		Inicio.getInstancia();
	}
	
	public static void main(String[] args) {

		Main m = new Main();
		//****************************************************DATOS DE PRUEBA********************************************************************
		/*Controlador.agregarCarrera("Tecnologo en informatica", 150, 250, new HashMap<String, Materia>());
		Controlador.agregarMateria("Programacion", "Tecnologo en informatica", 60, new HashMap<String, Asignatura>());
		Controlador.agregarMateria("Matematicas", "Tecnologo en informatica", 30, new HashMap<String, Asignatura>());
		Controlador.agregarCarrera("Ingenieria de software", 210, 350, new HashMap<String, Materia>());
		Controlador.agregarMateria("Programacion", "Ingenieria en sistemas", 100, new HashMap<String, Asignatura>());
		Controlador.agregarMateria("Ciencias humanas", "Ingenieria en sistemas", 100, new HashMap<String, Asignatura>());
		Controlador.agregarCarrera("Tecnicatura en redes", 230, 360, new HashMap<String, Materia>());
		Controlador.agregarMateria("Bases de datos", "Ingenieria de software", 110, new HashMap<String, Asignatura>());
		Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
		Controlador.agregarAsignatura("Principios de programacion", "Tecnologo en informatica", "Programacion", 0, false, previas);
		Asignatura a = Controlador.getColeccionCarreras().get("Tecnologo en informatica").getMaterias().get("Programacion").getAsignaturas().get("Principios de programacion");
		previas.put("Principios de programacion", a);
		Controlador.agregarAsignatura("Estructuras de datos y algoritmos", "Tecnologo en informatica", "Programacion", 6, true, previas);
		Controlador.agregarAsignatura("Bases de datos I", "Tecnologo en informatica", "Programacion", 12, false, new HashMap<String, Asignatura>());
		Controlador.agregarAsignatura("Matematicas discretas y logica I", "Tecnologo en informatica", "Matematicas", 12, false, new HashMap<String, Asignatura>());*/
		//****************************************************DATOS DE PRUEBA********************************************************************
		
		Controlador.cargarCarrerasBD();
		Controlador.cargarMateriasBD();
		Controlador.cargarAsignaturasBD();
		m.iniciarVentana();
	}
}
