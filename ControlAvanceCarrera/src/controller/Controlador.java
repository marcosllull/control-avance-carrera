package controller;

import java.util.HashMap;
import java.util.Map;

import model.Asignatura;
import model.Carrera;
import model.ManejadorCarrera;
import model.Materia;
import model.MetodosAux;

public class Controlador {
	
	private String nombreCarrera = "";
	private Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
	private Map<String, Asignatura> asignaturasSinSeleccionar = new HashMap<String, Asignatura>();
	private Map<String, Materia> materias = new HashMap<String, Materia>();
	private int creditosObtenidos = -1;
	private int creditosMin = 0;
	private int creditosMax = 0;
	private String mailContacto = "";
	
	//CONSTANTES
	public static final int CREDITOS_MIN_CARRERA = 1;
	public static final int CREDITOS_MIN_MATERIA = 1;
	public static final int CREDITOS_MIN_ASIGNATURA = 0;
	
	public Controlador() {}
	
	public Controlador(String nombreCarrera, Map<String, Asignatura> asignaturas, Map<String, Materia> materias, 
			int creditosObtenidos, int creditosMin, int creditosMax, String mailContacto) {
		this.nombreCarrera = nombreCarrera;
		this.asignaturas.putAll(asignaturas);
		this.materias = materias;
		this.creditosObtenidos = creditosObtenidos;
		this.creditosMin = creditosMin;
		this.creditosMax = creditosMax;
		this.mailContacto = mailContacto;
	}

	//GETTERS
	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public Map<String, Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public Map<String, Asignatura> getAsignaturasSinSeleccionar() {
		return asignaturasSinSeleccionar;
	}

	public Map<String, Materia> getMaterias() {
		return materias;
	}

	public int getCreditosObtenidos() {
		return creditosObtenidos;
	}

	public int getCreditosMin() {
		return creditosMin;
	}

	public int getCreditosMax() {
		return creditosMax;
	}

	public String getMailContacto() {
		return mailContacto;
	}

	//SETTERS
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public void setAsignaturas(Map<String, Asignatura> asignaturas) {
		for (Map.Entry<String, Asignatura> a : asignaturas.entrySet()) {
			this.asignaturas.put(a.getKey(), a.getValue());
		}
	}

	public void setAsignaturasSinSeleccionar(Map<String, Asignatura> asignaturasSinSeleccionar) {
		for (Map.Entry<String, Asignatura> a : asignaturasSinSeleccionar.entrySet()) {
			this.asignaturasSinSeleccionar.put(a.getKey(), a.getValue());
		}
	}

	public void setMaterias(Map<String, Materia> materias) {
		this.materias.putAll(materias);
	}

	public void setCreditosObtenidos(int creditosObtenidos) {
		this.creditosObtenidos = creditosObtenidos;
	}

	public void setCreditosMin(int creditosMin) {
		this.creditosMin = creditosMin;
	}

	public void setCreditosMax(int creditosMax) {
		this.creditosMax = creditosMax;
	}

	public void setMailContacto(String mailContacto) {
		this.mailContacto = mailContacto;
	}
	
	//OTROS METODOS
	public Map<String, Carrera> getColeccionCarreras(){
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		return mc.getCarreras();
	}
	
	public Carrera getInstanciaCarrera(String nombreCarrera) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		return mc.getCarreras().get(nombreCarrera);
	}
	
	public Materia getInstanciaMateria(String nombreCarrera, String nombreMateria) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			
			return c.getMaterias().get(nombreMateria);
		}
		return null;
	}
	
	public Asignatura getInstanciaAsignatura(String nombreCarrera, String nombreMateria, 
			String nombreAsignatura) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreMateria);
			
			if (existeMateria) {
				Materia m = c.getMaterias().get(nombreMateria);
				
				return m.getAsignaturas().get(nombreAsignatura);
			}
		}
		return null;
	}
	
	
	//LAS ASIGNATURAS DE CADA MATERIA NO PUEDEN AGREGARSE EN EL CONSTRUCTOR DE CARRERA. DEBEN
	//AGREGARSE LUEGO
	public static boolean agregarCarrera(String nombre, int creditosMin, int creditosMax, 
			Map<String, Materia> materias) {
		
		if (MetodosAux.validarNombre(nombre) &&
			creditosMin >= CREDITOS_MIN_CARRERA && creditosMin <= creditosMax) {
					
			ManejadorCarrera mc = ManejadorCarrera.getInstancia();
			
			boolean existeCarrera = mc.getCarreras().containsKey(nombre);
			if (!existeCarrera) {
				
				Carrera c = new Carrera(nombre, creditosMin, creditosMax, materias);
				mc.agregarCarrera(c);
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean removerCarrera(String nombre) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombre);
		
		if (existeCarrera) {
			mc.removerCarrera(nombre);
			return true;
		}
		return false;
	}
	
	public static boolean agregarMateria(String nombre, String nombreCarrera, 
			int cantCreditos, Map<String, Asignatura> asignaturas) {
		
		if (MetodosAux.validarNombre(nombre) &&
			MetodosAux.validarNombre(nombreCarrera) &&
			cantCreditos >= CREDITOS_MIN_MATERIA) {
				
			ManejadorCarrera mc = ManejadorCarrera.getInstancia();
			
			boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
			if (existeCarrera) {
				
				Carrera carrera = mc.getCarreras().get(nombreCarrera);
				
				boolean existeMateria = carrera.getMaterias().containsKey(nombre);
				if (!existeMateria) {
					
					Materia m = new Materia(nombre, nombreCarrera, cantCreditos,
							asignaturas);
					mc.agregarMateria(m, nombreCarrera);
					
					return true;
				}
			}
		}
		return false;
	}

	//REMUEVE UNA MATERIA DE LA COLECCION SI ESTA NO TIENE ASOCIADA NINGUNA ASIGNATURA
	public static boolean removerMateria(String nombre, String nombreCarrera, boolean seEstaModificando) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		if (existeCarrera) {
			Carrera carrera = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = carrera.getMaterias().containsKey(nombre);
			
			if (existeMateria) {
				Materia materia = carrera.getMaterias().get(nombre);
				int cantAsignaturas = materia.getAsignaturas().size();
				
				if (cantAsignaturas == 0 || seEstaModificando) {
					mc.removerMateria(nombre, nombreCarrera);
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean agregarAsignatura(String nombre, String nombreCarrera, String nombreMateria, 
			int cantCreditos, boolean tienePrevias, Map<String, Asignatura> previas) {
		
		if (MetodosAux.validarNombre(nombre) &&
			MetodosAux.validarNombre(nombreMateria) &&
			MetodosAux.validarNombre(nombreCarrera) &&
			cantCreditos >= CREDITOS_MIN_ASIGNATURA){
				
			ManejadorCarrera mc = ManejadorCarrera.getInstancia();
			
			boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
			if (existeCarrera) {
				
				Carrera carrera = mc.getCarreras().get(nombreCarrera);
				
				boolean existeMateria = carrera.getMaterias().containsKey(nombreMateria);
				if (existeMateria) {
					
					Materia materia = carrera.getMaterias().get(nombreMateria);
					
					boolean existeAsignatura = materia.getAsignaturas().containsKey(nombre);
					if (!existeAsignatura) {
						
						if (!tienePrevias) {
							Asignatura a = new Asignatura(nombre, nombreMateria, nombreCarrera, cantCreditos,
									tienePrevias, previas);
							
							mc.agregarAsignatura(a, nombreCarrera, nombreMateria);
							
							return true;
						}
						else {
							
							Map<String, Asignatura> asignaturas = materia.getAsignaturas();
							
							boolean existenPrevias = true;
							for (Map.Entry<String, Asignatura> p : previas.entrySet()) {
								if (!asignaturas.containsKey(p.getKey())) {
									existenPrevias = false;
									break;
								}
							}
							
							if (existenPrevias) {
								Asignatura a = new Asignatura(nombre, nombreMateria, nombreCarrera, 
										cantCreditos, tienePrevias, previas);
								mc.agregarAsignatura(a, nombreCarrera, nombreMateria);
								
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	//Verifica si una asignatura de nombre 'nombre' es previa de otra asignatura
	//nombre: nombre de la asignatura. materias: hashmap con todas las materias y cada una de sus asignaturas
	public static boolean verificarEsPrevia(String nombre, Map<String, Materia> materias) {
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				if (a.getValue().getPrevias().containsKey(nombre)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean removerAsignatura(String nombre, String nombreCarrera, String nombreMateria) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		if (existeCarrera) {
			
			Carrera carrera = mc.getCarreras().get(nombreCarrera);
			
			boolean existeMateria = carrera.getMaterias().containsKey(nombreMateria);
			if (existeMateria) {
				
				Materia materia = carrera.getMaterias().get(nombreMateria);
				
				boolean existeAsignatura = materia.getAsignaturas().containsKey(nombre);
		
				if (existeAsignatura) {
					
					boolean esPrevia = verificarEsPrevia(nombre, carrera.getMaterias()); 
					if (!esPrevia) {
						mc.removerAsignatura(nombre, nombreCarrera, nombreMateria);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//Permite modificar los datos de una carrera, incluyendo su nombre
	//En caso de NO modificarse el nombre, 'nombreDespues' y 'nombreAntes' seran iguales
	public static boolean modificarCarrera(String nombreDespues, String nombreAntes, int creditosMin, int creditosMax) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		boolean existeCarrera = mc.getCarreras().containsKey(nombreAntes);
		
		if (existeCarrera) {
			
			if (MetodosAux.validarNombre(nombreDespues) &&
				creditosMin >= CREDITOS_MIN_CARRERA && creditosMin <= creditosMax) {
				
				//Creo una copia de las materias y sus respectivas asignaturas
				Map<String, Materia> materias = Controlador.getCopyHashMapMaterias(nombreAntes);
				
				//Elimino la carrera
				Controlador.removerCarrera(nombreAntes);
				//Agrego la carrera modificada como una nueva con todas las materias que tenia antes y sus respectivas asignaturas
				Controlador.agregarCarrera(nombreDespues, creditosMin, creditosMax, materias);
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean modificarMateria(String nombreDespues, String nombreAntes, String nombreCarrera, int cantCreditos) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreAntes);
			
			if (existeMateria) {
				
				if (MetodosAux.validarNombre(nombreDespues) &&
					cantCreditos >= CREDITOS_MIN_MATERIA) {
					
					Map<String, Asignatura> asignaturas = getCopyHashMapAsignaturas(c.getNombre(), nombreAntes);
					
					boolean seEstaModificando = true;
					Controlador.removerMateria(nombreAntes, nombreCarrera, seEstaModificando);
					Controlador.agregarMateria(nombreDespues, nombreCarrera, cantCreditos, asignaturas);
					
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean modificarAsignatura(String nombreDespues, String nombreAntes, String nombreCarrera, 
			String nombreMateria, int cantCreditos) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreMateria);
			
			if (existeMateria) {
				
				Materia m = c.getMaterias().get(nombreMateria);
				boolean existeAsignatura = m.getAsignaturas().containsKey(nombreAntes);
				
				if (existeAsignatura) {
					
					Asignatura a = m.getAsignaturas().get(nombreAntes);
					
					if (MetodosAux.validarNombre(nombreDespues) &&
						cantCreditos >= CREDITOS_MIN_ASIGNATURA) {
						
						boolean tienePrevias = a.getTienePrevias();
						Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
						
						if (tienePrevias)
							previas = getCopyHashMapAsignaturasPrevias(c.getNombre(), nombreMateria, nombreAntes);
						
						Controlador.removerAsignatura(nombreAntes, nombreCarrera, nombreMateria);
						Controlador.agregarAsignatura(nombreDespues, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previas);
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//DEEP COPY del hashmap asignaturas previas (copia creando nuevos objetos de los atributos, sin referencias)
	public static Map<String, Asignatura> getCopyHashMapAsignaturasPrevias(String nombreAntes, String nombreMateria, String nombreAsignatura){
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
		for (Map.Entry<String, Asignatura> p : mc.getCarreras().get(nombreAntes).getMaterias().get(nombreMateria).getAsignaturas().get(nombreAsignatura).getPrevias().entrySet()) {
			Asignatura previa = new Asignatura(p.getValue().getNombre(), p.getValue().getNombreMateria(), p.getValue().getNombreCarrera(),
						p.getValue().getCantCreditos(), false, null);
			previas.put(previa.getNombre(), previa);
		}
		return previas;
	}
	
	//DEEP COPY del hashmap asignaturas (copia creando nuevos objetos de los atributos, sin referencias)
	public static Map<String, Asignatura> getCopyHashMapAsignaturas(String nombreAntes, String nombreMateria){
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
		for (Map.Entry<String, Asignatura> a : mc.getCarreras().get(nombreAntes).getMaterias().get(nombreMateria).getAsignaturas().entrySet()) {
			Asignatura original = a.getValue();
			Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
			for (Map.Entry<String, Asignatura> p : original.getPrevias().entrySet()) {
				Asignatura previa = new Asignatura(p.getValue().getNombre(), p.getValue().getNombreMateria(), p.getValue().getNombreCarrera(),
						p.getValue().getCantCreditos(), false, null);
				previas.put(previa.getNombre(), previa);
			}
			Asignatura copia = new Asignatura(original.getNombre(), original.getNombreMateria(), original.getNombreCarrera(),
					original.getCantCreditos(), original.getTienePrevias(), previas);
			asignaturas.put(copia.getNombre(), copia);
		}
		return asignaturas;
	}
	
	//DEEP COPY del hashmap materias (copia creando nuevos objetos de los atributos, sin referencias)
	public static Map<String, Materia> getCopyHashMapMaterias(String nombreAntes){
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		Map<String, Materia> materias = new HashMap<String, Materia>();
		for (Map.Entry<String, Materia> m : mc.getCarreras().get(nombreAntes).getMaterias().entrySet()) {
			Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				Asignatura original = a.getValue();
				Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
				for (Map.Entry<String, Asignatura> p : original.getPrevias().entrySet()) {
					Asignatura previa = new Asignatura(p.getValue().getNombre(), p.getValue().getNombreMateria(), p.getValue().getNombreCarrera(),
							p.getValue().getCantCreditos(), false, null);
					previas.put(previa.getNombre(), previa);
				}
				Asignatura copia = new Asignatura(original.getNombre(), original.getNombreMateria(), original.getNombreCarrera(),
						original.getCantCreditos(), original.getTienePrevias(), previas);
				asignaturas.put(copia.getNombre(), copia);
			}
			Materia materia = new Materia(m.getValue().getNombre(), m.getValue().getNombreCarrera(), m.getValue().getCantCreditos(),
					asignaturas);
			materias.put(materia.getNombre(), materia);
		}
		return materias;
	}
}