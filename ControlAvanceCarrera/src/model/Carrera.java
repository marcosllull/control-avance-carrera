package model;

import java.util.HashMap;
import java.util.Map;

public class Carrera {
	
	private String nombre;
	private int cantCreditosMin;
	private int cantCreditosMax;
	private Map<String, Materia> materias = new HashMap<String, Materia>();
	
	public Carrera(String nombre, int cantCreditosMin, int cantCreditosMax, Map<String, Materia> materias) {
		
		this.nombre = nombre;
		this.cantCreditosMin = cantCreditosMin;
		this.cantCreditosMax = cantCreditosMax;
		this.materias.putAll(materias);
	}

	//GETTERS
	public String getNombre() {
		return nombre;
	}

	public int getCantCreditosMin() {
		return cantCreditosMin;
	}

	public int getCantCreditosMax() {
		return cantCreditosMax;
	}

	public Map<String, Materia> getMaterias() {
		return materias;
	}

	//SETTERS
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCantCreditosMin(int cantCreditosMin) {
		this.cantCreditosMin = cantCreditosMin;
	}

	public void setCantCreditosMax(int cantCreditosMax) {
		this.cantCreditosMax = cantCreditosMax;
	}

	public void setMaterias(Map<String, Materia> materias) {
		this.materias.putAll(materias);
	}
}
