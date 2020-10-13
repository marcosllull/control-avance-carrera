package model;

import java.util.HashMap;
import java.util.Map;

public class Asignatura {
	
	private String nombre;
	private String nombreMateria;
	private String nombreCarrera;
	private int cantCreditos;
	private boolean tienePrevias;
	private Map<String, Asignatura> previasCurso = new HashMap<String, Asignatura>();
	private Map<String, Asignatura> previasTodo = new HashMap<String, Asignatura>();
	
	public Asignatura(String nombre, String nombreMateria, String nombreCarrera, int cantCreditos,
			boolean tienePrevias, Map<String, Asignatura> previasCurso, Map<String, Asignatura> previasTodo) {
		
		this.nombre = nombre;
		this.nombreMateria = nombreMateria;
		this.nombreCarrera = nombreCarrera;
		this.cantCreditos = cantCreditos;
		this.tienePrevias = tienePrevias;
		if (previasCurso != null)
			this.previasCurso.putAll(previasCurso);
		if (previasTodo != null)
			this.previasTodo.putAll(previasTodo);
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

	public Map<String, Asignatura> getPreviasCurso() {
		return previasCurso;
	}
	
	public Map<String, Asignatura> getPreviasTodo() {
		return previasTodo;
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

	public void setPreviasCurso(Map<String, Asignatura> previasCurso) {
		this.previasCurso.putAll(previasCurso); 
	}
	
	public void setPreviasTodo(Map<String, Asignatura> previasTodo) {
		this.previasTodo.putAll(previasTodo); 
	}
}
