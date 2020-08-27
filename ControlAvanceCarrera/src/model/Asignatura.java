package model;

import java.util.HashMap;
import java.util.Map;

public class Asignatura {
	
	private String nombre;
	private String nombreMateria;
	private String nombreCarrera;
	private int cantCreditos;
	private boolean tienePrevias;
	private Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
	
	public Asignatura(String nombre, String nombreMateria, String nombreCarrera, int cantCreditos,
			boolean tienePrevias, Map<String, Asignatura> previas) {
		
		this.nombre = nombre;
		this.nombreMateria = nombreMateria;
		this.nombreCarrera = nombreCarrera;
		this.cantCreditos = cantCreditos;
		this.tienePrevias = tienePrevias;
		if (previas != null)
			this.previas.putAll(previas);
	}
	
	//GETTERS
	public String getNombre() {
		return nombre;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public int getCantCreditos() {
		return cantCreditos;
	}

	public boolean getTienePrevias() {
		return tienePrevias;
	}

	public Map<String, Asignatura> getPrevias() {
		return previas;
	}

	//SETTERS
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public void setCantCreditos(int cantCreditos) {
		this.cantCreditos = cantCreditos;
	}

	public void setTienePrevias(boolean tienePrevias) {
		this.tienePrevias = tienePrevias;
	}

	public void setPrevias(Map<String, Asignatura> previas) {
		this.previas.putAll(previas); 
	}
}
