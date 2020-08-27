package model;

import java.util.HashMap;
import java.util.Map;

public class Materia {
	//Materia a la que pertence una asignatura:
	//Ej: Estucturas de datos y algoritmos, pertence a la asignatura Programacion en la carrera
	//Tecnologo en Informatica
	
	private String nombre;
	private int cantCreditos;
	private Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
	private String nombreCarrera;
	
	public Materia(String nombre, String nombreCarrera, int cantCreditos, 
			Map<String, Asignatura> asignaturas) {
		
		this.nombre = nombre;
		this.nombreCarrera = nombreCarrera;
		this.cantCreditos = cantCreditos;
		this.asignaturas.putAll(asignaturas);
	}
	
	//GETTERS
	public String getNombre() {
		return nombre;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}
	
	public int getCantCreditos() {
		return cantCreditos;
	}

	public Map<String, Asignatura> getAsignaturas() {
		return asignaturas;
	}

	//SETTERS
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	
	public void setCantCreditos(int cantCreditos) {
		this.cantCreditos = cantCreditos;
	}

	public void setAsignaturas(Map<String, Asignatura> asignaturas) {
		this.asignaturas.putAll(asignaturas);
	}
}
