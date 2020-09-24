package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Asignatura;
import model.BaseDeDatos;
import model.Carrera;
import model.ManejadorCarrera;
import model.Materia;
import model.MetodosAux;

public class Controlador {
	
	//CONSTANTES
	public static final int CREDITOS_MIN_CARRERA = 1;
	public static final int CREDITOS_MIN_MATERIA = 1;
	public static final int CREDITOS_MIN_ASIGNATURA = 0;
	
	private Controlador() {}
	
	//OTROS METODOS
	public static Map<String, Carrera> getColeccionCarreras(){
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
	
	public static void cargarCarrerasBD() {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		ArrayList<String[]> carrerasStr = obtenerCarrerasBD();
		
		for (String[] carrera : carrerasStr) {
			String nombreCarrera = carrera[0];
			int creditosMin = Integer.parseInt(carrera[1]);
			int creditosMax = Integer.parseInt(carrera[2]);
			Map<String, Materia> materias = new HashMap<String, Materia>();
			
			Carrera c = new Carrera(nombreCarrera, creditosMin, creditosMax, materias);
			mc.agregarCarrera(c);
		}
	}
	
	public static void cargarMateriasBD() {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		ArrayList<String[]> materiasStr = obtenerMateriasBD();
		
		for (String[] materia : materiasStr) {
			String nombreMateria = materia[0];
			String nombreCarrera = materia[1];
			int cantCreditos = Integer.parseInt(materia[2]);
			Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
			
			Materia m = new Materia(nombreMateria, nombreCarrera, cantCreditos, asignaturas);
			mc.agregarMateria(m, nombreCarrera);
		}
		
	}
	
	public static void cargarAsignaturasBD() {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		ArrayList<String[]> asignaturasStr = obtenerAsignaturasBD();
		ArrayList<String[]> asignaturasConPrevias = obtenerAsignaturasConPreviasBD();
		
		//Primero agrego a la colecccion todas las asignaturas con el hashmap de previas vacio
		for (String[] asignatura : asignaturasStr) {
			
			String nombreAsignatura = asignatura[0];
			String nombreCarrera = asignatura[1];
			String nombreMateria = asignatura[2];
			int cantCreditos = Integer.parseInt(asignatura[3]);
			boolean tienePrevias = Boolean.parseBoolean(asignatura[4]);
			
			Asignatura a = new Asignatura(nombreAsignatura, nombreMateria, nombreCarrera, cantCreditos,
					tienePrevias, new HashMap<String, Asignatura>());

			mc.agregarAsignatura(a, nombreCarrera, nombreMateria);
		}
		
		boolean agregado = false;
		while (asignaturasConPrevias.size() > 0) {
			agregado = false;
			for (String[] acp : asignaturasConPrevias) {
				for (Map.Entry<String, Materia> materia : mc.getCarreras().get(acp[0]).getMaterias().entrySet()) {
					for (Map.Entry<String, Asignatura> asignatura : materia.getValue().getAsignaturas().entrySet()) {
						if (asignatura.getValue().getNombre().equals(acp[1])) {
							if (!tienePreviasLaPreviaBD(acp[0], acp[2], asignaturasConPrevias) ||
								previaEstaAgregadaComoAsignatura(acp[0], acp[2])) {
								
								Asignatura previa = obtenerPreviaBD(acp[0], acp[2]);
								asignatura.getValue().getPrevias().put(previa.getNombre(), previa);
								asignaturasConPrevias.remove(acp);
								agregado = true;
								break;
							}
						}
					}
					if (agregado)
						break;
				}
				if (agregado)
					break;
			}
		}
	}
	
	public static boolean previaEstaAgregadaComoAsignatura(String nombreCarrera, String nombrePrevia) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		Carrera c = mc.getCarreras().get(nombreCarrera);
		for (Map.Entry<String, Materia> materia : c.getMaterias().entrySet()) {
			if (materia.getValue().getAsignaturas().containsKey(nombrePrevia)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean tienePreviasLaPreviaBD(String nombreCarrera, String nombreAsignatura, ArrayList<String[]> asignaturasConPrevias) {
		for (String[] acp : asignaturasConPrevias) {
			if (acp[1].equals(nombreCarrera) && acp[2].equals(nombreAsignatura))
				return true;
		}
		
		return false;
	}
	
	public static Asignatura obtenerPreviaBD(String nombreCarrera, String nombreAsignatura) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		for (Map.Entry<String, Materia> materia : mc.getCarreras().get(nombreCarrera).getMaterias().entrySet()) {
			for (Map.Entry<String, Asignatura> asignatura : materia.getValue().getAsignaturas().entrySet()) {
				if (asignatura.getValue().getNombreCarrera().equals(nombreCarrera) && asignatura.getValue().getNombre().equals(nombreAsignatura)) {
					return asignatura.getValue();
				}
			}
		}
		return null;
	}
	
	public static ArrayList<String[]> obtenerCarrerasBD() {
		return BaseDeDatos.getCarreras();
	}
	
	public static ArrayList<String[]> obtenerMateriasBD(){
		return BaseDeDatos.getMaterias();
	}
	
	public static ArrayList<String[]> obtenerAsignaturasBD(){
		return BaseDeDatos.getAsignaturas();
	}
	
	public static ArrayList<String[]> obtenerAsignaturasConPreviasBD(){
		return BaseDeDatos.getAsignaturasConPrevias();
	}
	
	public static Map<String, Carrera> obtenerCarrerasControlador(){
		return ManejadorCarrera.getInstancia().getCarreras();
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
				BaseDeDatos.insertarCarreraBD(nombre, creditosMin, creditosMax);
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean removerCarrera(String nombre) {
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombre);
		
		if (existeCarrera) {
			for (Map.Entry<String, Carrera> carrera : mc.getCarreras().entrySet()) {
				if (carrera.getValue().getNombre().equals(nombre)) {
					for (Map.Entry<String, Materia> materia : carrera.getValue().getMaterias().entrySet()) {
						for (Map.Entry<String, Asignatura> asignatura : materia.getValue().getAsignaturas().entrySet()) {
							BaseDeDatos.eliminarAsignaturaBD(asignatura.getValue().getNombre());
							//removerAsignatura(String nombre, String nombreCarrera, String nombreMateria)
						}
						BaseDeDatos.eliminarMateriaBD(materia.getValue().getNombre());
					}
					break;
				}
			}
			mc.removerCarrera(nombre);
			BaseDeDatos.eliminarCarreraBD(nombre);
			
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
					BaseDeDatos.insertarMateriaBD(nombre, nombreCarrera, cantCreditos);
					
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
					BaseDeDatos.eliminarMateriaBD(nombre);
					
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
					
					Map<String, Asignatura> asignaturasCarrera = new HashMap<String, Asignatura>();
					Map<String, Materia> materiasCarrera = mc.getCarreras().get(nombreCarrera).getMaterias();
					for (Map.Entry<String, Materia> m : materiasCarrera.entrySet()) {
						for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()){
							asignaturasCarrera.put(a.getKey(), a.getValue());
						}
					}
					
					boolean existeAsignatura = asignaturasCarrera.containsKey(nombre);
					if (!existeAsignatura) {
						
						if (!tienePrevias) {
							Asignatura a = new Asignatura(nombre, nombreMateria, nombreCarrera, cantCreditos,
									tienePrevias, previas);

							mc.agregarAsignatura(a, nombreCarrera, nombreMateria);
							BaseDeDatos.insertarAsignaturaBD(nombre, nombreCarrera, nombreMateria, cantCreditos, tienePrevias);
							
							return true;
						}
						else {
							
							boolean existenPrevias = true;
							for (Map.Entry<String, Asignatura> p : previas.entrySet()) {
								if (!asignaturasCarrera.containsKey(p.getKey())) {
									existenPrevias = false;
									break;
								}
							}
							
							if (existenPrevias) {
								Asignatura a = new Asignatura(nombre, nombreMateria, nombreCarrera, 
										cantCreditos, tienePrevias, previas);
								mc.agregarAsignatura(a, nombreCarrera, nombreMateria);
								
								BaseDeDatos.insertarAsignaturaBD(nombre, nombreCarrera, nombreMateria, cantCreditos, tienePrevias);
								//SE INSERTA LA ASIGNATURA Y LUEGO TODAS SUS PREVIAS
								for (Map.Entry<String, Asignatura> p : previas.entrySet()) {
									BaseDeDatos.insertarPreviaAsignaturaBD(nombreCarrera, nombre, p.getValue().getNombre());
								}
								
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
						
						BaseDeDatos.eliminarPreviaAsignaturaBD(nombreCarrera, nombre);
						BaseDeDatos.eliminarAsignaturaBD(nombre);
						
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
				
				Carrera c = mc.getCarreras().get(nombreAntes);
				c.setNombre(nombreDespues);
				c.setCantCreditosMin(creditosMin);
				c.setCantCreditosMax(creditosMax);
				
				mc.getCarreras().remove(nombreAntes);
				mc.getCarreras().put(c.getNombre(), c);
				
				BaseDeDatos.modificarCarreraBD(nombreAntes, nombreDespues, creditosMin, creditosMax);
				
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
					
					Materia m = c.getMaterias().get(nombreAntes);
					m.setNombre(nombreDespues);
					m.setCantCreditos(cantCreditos);
					
					mc.getCarreras().get(nombreCarrera).getMaterias().remove(nombreAntes);
					mc.getCarreras().get(nombreCarrera).getMaterias().put(m.getNombre(), m);
					
					Map<String, Asignatura> asignaturasMateria = mc.getCarreras().get(nombreCarrera).getMaterias().get(m.getNombre()).getAsignaturas();
					for (Map.Entry<String, Asignatura> am : asignaturasMateria.entrySet()) {
						am.getValue().setNombreMateria(nombreDespues);
					}
					
					BaseDeDatos.modificarMateriaBD(nombreAntes, nombreDespues, nombreCarrera, cantCreditos);
					
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean modificarAsignatura(String nombreDespues, String nombreAntes, String nombreCarrera, 
			String nombreMateria, int cantCreditos, boolean tienePrevias, Map<String, Asignatura> previas) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreMateria);
			
			if (existeMateria) {
				
				Materia m = c.getMaterias().get(nombreMateria);
				boolean existeAsignatura = m.getAsignaturas().containsKey(nombreAntes);
				
				if (existeAsignatura) {
					
					if (MetodosAux.validarNombre(nombreDespues) &&
						cantCreditos >= CREDITOS_MIN_ASIGNATURA) {
						
						Controlador.removerAsignatura(nombreAntes, nombreCarrera, nombreMateria);
						//SE ELIMINAN PRIMERO LAS PREVIAS DE LA BASE DE DATOS YA QUE HACEN REFERENCIA A LA ASIGNATURA A ELIMINAR POR LO QUE HASTA
						//QUE LAS PREVIAS NO SE ELIMINEN, ESTA NO SE PODRA ELIMINAR
						BaseDeDatos.eliminarPreviaAsignaturaBD(nombreCarrera, nombreAntes);
						BaseDeDatos.eliminarAsignaturaBD(nombreAntes);
						
						Controlador.agregarAsignatura(nombreDespues, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previas);
						BaseDeDatos.insertarAsignaturaBD(nombreDespues, nombreCarrera, nombreMateria, cantCreditos, tienePrevias);
						//SE INSERTA LA ASIGNATURA Y LUEGO TODAS SUS PREVIAS
						for (Map.Entry<String, Asignatura> p : previas.entrySet()) {
							BaseDeDatos.insertarPreviaAsignaturaBD(nombreCarrera, nombreDespues, p.getValue().getNombre());
						}
						
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