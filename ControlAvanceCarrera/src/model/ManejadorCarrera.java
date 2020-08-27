package model;

import java.util.HashMap;
import java.util.Map;

public class ManejadorCarrera {
	
	private static ManejadorCarrera instancia = null;
	private Map<String, Carrera> carreras;
	
	private ManejadorCarrera() {
		carreras = new HashMap<String, Carrera>();
	}
	
	public static ManejadorCarrera getInstancia() {
		if (instancia == null)
			instancia = new ManejadorCarrera();
		return instancia;
	}
	
	//GETTERS
	public Map<String, Carrera> getCarreras() {
		return carreras;
	}

	//SETTERS
	public void setCarreras(Map<String, Carrera> carreras) {
		this.carreras = carreras;
	}
	
	//OTROS METODOS
	public void agregarCarrera(Carrera carrera) {
		this.carreras.put(carrera.getNombre(), carrera);
	}
	
	public void removerCarrera(String nombreCarrera) {
		Carrera carrera = this.getCarreras().get(nombreCarrera);
		for (Map.Entry<String, Materia> m : carrera.getMaterias().entrySet()) {
			m.getValue().getAsignaturas().clear();
		}
		carrera.getMaterias().clear();
		
		this.carreras.remove(nombreCarrera);
	}
	
	public void agregarMateria(Materia materia, String nombreCarrera) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		Map<String, Materia> materiasCarrera = mc.getCarreras().get(nombreCarrera).getMaterias();
		materiasCarrera.put(materia.getNombre(), materia);
	}
	
	public void removerMateria(String nombreMateria, String nombreCarrera) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		Carrera c = mc.getCarreras().get(nombreCarrera);
		Materia m = c.getMaterias().get(nombreMateria);
		
		Map<String, Asignatura> asignaturasDeLaMateria = m.getAsignaturas();
		asignaturasDeLaMateria.clear();
		
		c.getMaterias().remove(nombreMateria);
	}
	
	public void agregarAsignatura(Asignatura asignatura, String nombreCarrera, String nombreMateria) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		Map<String, Materia> materiasCarrera = mc.getCarreras().get(nombreCarrera).getMaterias();
		Map<String, Asignatura> asignaturasCarrera = materiasCarrera.get(nombreMateria).getAsignaturas(); 
		asignaturasCarrera.put(asignatura.getNombre(), asignatura);
	}
	
	public void removerAsignatura(String nombreAsignatura, String nombreCarrera, String nombreMateria) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		Carrera c = mc.getCarreras().get(nombreCarrera);
		Materia m = c.getMaterias().get(nombreMateria);
		
		m.getAsignaturas().remove(nombreAsignatura);
	}
}