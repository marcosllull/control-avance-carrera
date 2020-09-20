package controller;

import model.Asignatura;
import model.Carrera;
import model.Materia;
import model.MetodosAux;
import model.Tipo;

import view.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	private static Scanner entrada = new Scanner(System.in);
	private static Controlador controlador = new Controlador();
	
	//*****************************************************************METODOS AUXILIARES***************************************************************
	//*****************************************************************METODOS AUXILIARES***************************************************************
	//*****************************************************************METODOS AUXILIARES***************************************************************
	
	public static boolean verifExisteAsignaturaEnMateriaEnCarrera(String nombreAsignatura, String nombreCarrera, String nombreMateria) {
		if (controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().get(nombreMateria).getAsignaturas().containsKey(nombreAsignatura))
			return true;
		else
			return false;
	}
	
	public static boolean verifExisteMateriaEnCarrera(String nombreMateria, String nombreCarrera) {
		if (controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().containsKey(nombreMateria))
			return true;
		else
			return false;
	}
	public static boolean verifExisteCarrera(String nombreCarrera) {
		return controlador.getColeccionCarreras().containsKey(nombreCarrera);
	}
	
	public static boolean existeUnaMateria(Map<String, Carrera> carreras) {
		for (Map.Entry<String, Carrera> c : carreras.entrySet()) {
			if (c.getValue().getMaterias().size() > 0)
				return true;
		}
		return false;
	}
	
	public static boolean existeUnaAsignatura() {
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			for (Map.Entry<String, Materia> m : c.getValue().getMaterias().entrySet()) {
				if (m.getValue().getAsignaturas().size() > 0)
						return true;
			}
		}
		return false;
	}
	
	public static String pedirCarrera() {
		String nombreCarrera = "";
		String opcion = "";
		
		imprimirCarreras();
		boolean carreraEncontrada = false;
		while (!carreraEncontrada) {
			
			System.out.print("Ingrese el valor numerico de la carrera: " ); opcion = entrada.nextLine();
			try {
				nombreCarrera = buscarCarrera(Integer.valueOf(opcion));
				carreraEncontrada = controlador.getColeccionCarreras().containsKey(nombreCarrera);
						
				if (!carreraEncontrada)
					System.out.println("ERROR. Debe ingresar un numero que pertenezca a una carrera");
			}
			catch (NumberFormatException ex) {
				System.out.println("ERROR. Solo puede ingresar numeros enteros");
			}
		}
		return nombreCarrera;
	}
	
	public static String pedirMateria(String nombreCarrera) {
		String nombreMateria = "";
		String opcion = "";
		
		imprimirMateriasDeCarrera(nombreCarrera);
		boolean materiaEncontrada = false;
		while (!materiaEncontrada) {
			
			System.out.print("Ingrese el valor numerico de la materia: " ); opcion = entrada.nextLine();
			try {
				nombreMateria = buscarMateriaDeCarrera(Integer.valueOf(opcion), nombreCarrera);
						
				if (nombreMateria.equals(""))
					System.out.println("ERROR. Debe ingresar un numero que pertenezca a una materia");
				else
					materiaEncontrada = true;
			}
			catch (NumberFormatException ex) {
				System.out.println("ERROR. Solo puede ingresar numeros enteros");
			}
		}
		
		return nombreMateria;
	}
	
	public static String pedirAsignatura(String nombreCarrera, String nombreMateria) {
		
		String nombreAsignatura = "";
		String opcion = "";
		
		imprimirAsignaturasDeMateriaDeCarrera(nombreCarrera, nombreMateria);
		boolean asignaturaEncontrada = false;
		while (!asignaturaEncontrada) {
			
			System.out.print("Ingrese el valor numerico de la asignatura: " ); opcion = entrada.nextLine();
			try {
				nombreAsignatura = buscarAsignaturaDeMateriaDeCarrera(Integer.valueOf(opcion), nombreCarrera, nombreMateria);
						
				if (nombreAsignatura.equals(""))
					System.out.println("ERROR. Debe ingresar un numero que pertenezca a una asignatura");
				else
					asignaturaEncontrada = true;
			}
			catch (NumberFormatException ex) {
				System.out.println("ERROR. Solo puede ingresar numeros enteros");
			}
		}
		
		return nombreAsignatura;
	}
	
	public static int pedirCantCreditos(String mensajeInput, String mensajeError, Tipo tipo) {
		int cantCreditos = 0;
		
		boolean valorValido = false;
		while (!valorValido) {
			String auxCantCreditos = "";
			System.out.print(mensajeInput); auxCantCreditos = entrada.nextLine();
			try {
				cantCreditos = Integer.parseInt(auxCantCreditos);
				if (cantCreditos >= Controlador.CREDITOS_MIN_CARRERA && (tipo == Tipo.CARRERA || tipo == Tipo.MATERIA))
					valorValido = true;
				else if (cantCreditos >= Controlador.CREDITOS_MIN_ASIGNATURA && tipo == Tipo.ASIGNATURA)
					valorValido = true;
				else
					System.out.println(mensajeError);
			}
			catch (NumberFormatException ex) {
				System.out.println("ERROR. Solo puede ingresar numeros enteros");
			}
		}
		
		return cantCreditos;
	}
	
	public static Asignatura pedirAsignatura() {
		
		String opcion = "";
		Asignatura previa = null;
		
		imprimirAsignaturas();
		
		System.out.print("Ingrese el valor numerico de la asignatura: " ); opcion = entrada.nextLine();
		previa = buscarAsignatura(Integer.valueOf(opcion));
		
		return previa;
	}
	
	public static String ingresarNombreCarreraDisponible(String nombreAntes) {
		String nombre = "";
		
		boolean existeCarrera = true;
		while (existeCarrera && !nombre.equals(nombreAntes)) {
			//VERIFICAR NOMBRE
			boolean valorValido = false;
			while (!valorValido) {
				System.out.print("Ingrese el nombre de la carrera: " ); nombre = entrada.nextLine();
				
				if (MetodosAux.validarNombre(nombre))
					valorValido = true;
				else
					System.out.println("ERROR. Nombre invalido");
			}
			
			existeCarrera = verifExisteCarrera(nombre);
			
			if (existeCarrera && !nombre.equals(nombreAntes))
				System.out.print("ERROR. La carrera ingresada ya existe");
		}
		
		return nombre;
	}	
	
	public static String ingresarNombreCarreraDisponible() {
		String nombre = "";
		
		boolean existeCarrera = true;
		while (existeCarrera) {
			//VERIFICAR NOMBRE
			boolean valorValido = false;
			while (!valorValido) {
				System.out.print("Ingrese el nombre de la carrera: " ); nombre = entrada.nextLine();
				
				if (MetodosAux.validarNombre(nombre))
					valorValido = true;
				else
					System.out.println("ERROR. Nombre invalido");
			}
			
			existeCarrera = verifExisteCarrera(nombre);
			
			if (existeCarrera)
				System.out.print("ERROR. La carrera ingresada ya existe");
		}
		
		return nombre;
	}
	
	public static String ingresarNombreMateriaDisponible(String nombreAntes, String nombreCarrera) {
		String nombre = "";
		
		boolean existeMateria = true;
			
		while (existeMateria && !nombre.equals(nombreAntes)) {
			//VERIFICAR NOMBRE
			boolean valorValido = false;
			while (!valorValido) {
				System.out.print("Ingrese el nombre de la materia: " ); nombre = entrada.nextLine();
				
				if (MetodosAux.validarNombre(nombre))
					valorValido = true;
				else
					System.out.println("ERROR. Nombre invalido");
			}
			
			existeMateria = verifExisteMateriaEnCarrera(nombre, nombreCarrera);
			
			if (existeMateria && !nombre.equals(nombreAntes))
				System.out.print("ERROR. La materia ingresada ya existe");
		}
		
		return nombre;
	}
	
	public static String ingresarNombreMateriaDisponible(String nombreCarrera) {
		String nombre = "";
		
		boolean existeMateria = true;
		while (existeMateria) {
			//VERIFICAR NOMBRE
			boolean valorValido = false;
			while (!valorValido) {
				System.out.print("Ingrese el nombre de la materia: " ); nombre = entrada.nextLine();
				
				if (MetodosAux.validarNombre(nombre))
					valorValido = true;
				else
					System.out.println("ERROR. Nombre invalido");
			}
			
			existeMateria = verifExisteMateriaEnCarrera(nombre, nombreCarrera);
			
			if (existeMateria)
				System.out.print("ERROR. La materia ingresada ya existe");
		}
		
		return nombre;
	}
	
	public static String ingresarNombreAsignaturaDisponible(String nombreAntes, String nombreCarrera, String nombreMateria) {
		String nombre = "";
		
		boolean existeAsignatura = true;

		while (existeAsignatura && !nombre.equals(nombreAntes)) {
			//VERIFICAR NOMBRE
			boolean valorValido = false;
			while (!valorValido) {
				System.out.print("Ingrese el nombre de la asignatura: " ); nombre = entrada.nextLine();
				
				if (MetodosAux.validarNombre(nombre))
					valorValido = true;
				else
					System.out.println("ERROR. Nombre invalido");
			}
			
			existeAsignatura = verifExisteAsignaturaEnMateriaEnCarrera(nombre, nombreCarrera, nombreMateria);
			
			if (existeAsignatura && !nombre.equals(nombreAntes))
				System.out.print("ERROR. La asignatura ingresada ya existe");
		}
		
		return nombre;
	}
	
	public static String ingresarNombreAsignaturaDisponible(String nombreCarrera, String nombreMateria) {
		String nombre = "";
		
		boolean existeAsignatura = true;
		while (existeAsignatura) {
			//VERIFICAR NOMBRE
			boolean valorValido = false;
			while (!valorValido) {
				System.out.print("Ingrese el nombre de la asignatura: " ); nombre = entrada.nextLine();
				
				if (MetodosAux.validarNombre(nombre))
					valorValido = true;
				else
					System.out.println("ERROR. Nombre invalido");
			}
			
			existeAsignatura = verifExisteAsignaturaEnMateriaEnCarrera(nombre, nombreCarrera, nombreMateria);
			
			if (existeAsignatura)
				System.out.print("ERROR. La asignatura ingresada ya existe");
		}
		
		return nombre;
	}
	
	public static boolean preguntarUsuarioBoolean(String pregunta, String error) {
		String answer = "";
		boolean retorno = false;
		boolean valorValido = false;
		while (!valorValido) {
			
			valorValido = true;
			answer = "";
			System.out.println(pregunta); answer = entrada.nextLine();
			if (answer.equalsIgnoreCase("no"))
				retorno =  false;
			else if (answer.equalsIgnoreCase("si"))
				retorno = true;
			else {
				System.out.println(error);
				valorValido = false;
			}
		}
		return retorno;
	}
	
	public static void imprimirCarreras() {
		int contador = 1;
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			System.out.println(Integer.toString(contador) + "- " + c.getValue().getNombre());
			contador++;
		}
	}
	
	public static void imprimirMaterias() {
		int contador = 1;
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			for (Map.Entry<String, Materia> m : c.getValue().getMaterias().entrySet()) {
				System.out.println(Integer.toString(contador) + "- " + m.getValue().getNombre());
				contador++;
			}
		}
	}
	
	public static void imprimirAsignaturas() {
		int contador = 1;
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			for (Map.Entry<String, Materia> m : c.getValue().getMaterias().entrySet()) {
				for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
					System.out.println(Integer.toString(contador) + "- " + a.getValue().getNombre());
					contador++;
				}
			}
		}
	}
	
	//Retorna el nombre de la carrera dado el indice
	public static String buscarCarrera(int index) {
		int contador = 1;
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			if (contador == index)
				return c.getValue().getNombre();
			contador++;
		}
		return "";
	}
	
	public static String buscarMateria(int index) {
		int contador = 1;
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			for (Map.Entry<String, Materia> m : c.getValue().getMaterias().entrySet()) {
				if (contador == index)
					return m.getValue().getNombre();
				contador++;
			}
		}
		return "";
	}
	
	public static Asignatura buscarAsignatura(int index) {
		int contador = 1;
		for (Map.Entry<String, Carrera> c : controlador.getColeccionCarreras().entrySet()) {
			for (Map.Entry<String, Materia> m : c.getValue().getMaterias().entrySet()) {
				for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
					if (contador == index)
						return a.getValue();
					contador++;
				}
			}
		}
		return null;
	}
	
	//BUSCAR MATERIA EN UNA CARRERA
	public static String buscarMateriaDeCarrera(int index, String nombreCarrera) {
		int contador = 1;
		for (Map.Entry<String, Materia> m : controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().entrySet()) {
			if (contador == index)
				return m.getValue().getNombre();
			contador++;
		}
		return "";
	}
	
	//BUSCAR ASIGNATURA DE UNA MATERIA EN UNA CARRERA
	public static String buscarAsignaturaDeMateriaDeCarrera(int index, String nombreCarrera, String nombreMateria) {
		
		int contador = 1;
		Materia materia = controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().get(nombreMateria);
		for (Map.Entry<String, Asignatura> a : materia.getAsignaturas().entrySet()) {
			if (contador == index)
				return a.getValue().getNombre();
			contador++;
		}
		
		return "";
	}
	
	public static void imprimirMateriasDeCarrera(String nombreCarrera) {
		int contador = 1;
		for (Map.Entry<String, Materia> m: controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().entrySet()) {
			System.out.println(Integer.toString(contador) + "- " + m.getValue().getNombre());
			contador++;
		}
	}
	
	public static void imprimirAsignaturasDeMateriaDeCarrera(String nombreCarrera, String nombreMateria) {
		int contador = 1;
		Materia materia = controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().get(nombreMateria);
		for (Map.Entry<String, Asignatura> a : materia.getAsignaturas().entrySet()) {
			System.out.println(Integer.toString(contador) + "- " + a.getValue().getNombre());
			contador++;
		}
	}
	
	public static String pedirMateriaDeCarrera(String nombreCarrera) {
		String nombreMateria = "";
		String opcion = "";
		
		imprimirMateriasDeCarrera(nombreCarrera);
		
		boolean materiaEncontrada = false;
		while (!materiaEncontrada) {
			
			System.out.print("Ingrese el valor numerico de la materia: " ); opcion = entrada.nextLine();
			nombreMateria = buscarMateriaDeCarrera(Integer.valueOf(opcion), nombreCarrera);
					
			if (nombreMateria.equals(""))
				System.out.println("ERROR. Debe ingresar un numero que pertenezca a una materia");
			else
				materiaEncontrada = true;
		}
		
		return nombreMateria;
	}
	
	public static String pedirAsignaturaDeMateriaDeCarrera(String nombreCarrera, String nombreMateria) {
		String nombreAsignatura = "";
		String opcion = "";
		
		imprimirAsignaturasDeMateriaDeCarrera(nombreCarrera, nombreMateria);
		
		boolean asignaturaEncontrada = false;
		while (!asignaturaEncontrada) {
			
			System.out.print("Ingrese el valor numerico de la asignatura: " ); opcion = entrada.nextLine();
			nombreAsignatura = buscarAsignaturaDeMateriaDeCarrera(Integer.valueOf(opcion), nombreCarrera, nombreMateria);
					
			if (nombreAsignatura.equals(""))
				System.out.println("ERROR. Debe ingresar un numero que pertenezca a una asignatura");
			else
				asignaturaEncontrada = true;
		}
		
		return nombreAsignatura;
	}
	
	//*****************************************************************METODOS AUXILIARES***************************************************************
	//*****************************************************************METODOS AUXILIARES***************************************************************
	//*****************************************************************METODOS AUXILIARES***************************************************************
	
	public static void iniciarApliacion() {
		
		boolean cerrarAplicacion = false;
		
		while (!cerrarAplicacion) {
			
			menuPrincipal();
			System.out.print("Elija una opcion: "); String opcion = entrada.nextLine();
			
			if (opcion.equals("1"))
				menuSeleccionar();
			else if (opcion.equals("2"))
				menuCrear();
			else if (opcion.equals("3"))
				menuEliminar();
			else if (opcion.equals("4"))
				menuModificar();
			else if (opcion.equals("5"))
				cerrarAplicacion = true;
			else
				System.out.println("ERROR. Opcion invalida");
		}
	}
	
	public static void menuPrincipal() {
		System.out.println("************* MENU PRINCIPAL **************");
		System.out.println("1- Menu seleccionar");
		System.out.println("2- Menu crear");
		System.out.println("3- Menu eliminar");
		System.out.println("4- Menu modificar");
		System.out.println("5- Salir");
	}
	
	public static void menuSeleccionar() {
		System.out.println("************* MENU SELECCIONAR **************");
		System.out.println("1- Seleccionar carrera");
		System.out.println("2- Volver");
		
		
		boolean opcionValida = false;
		
		while (!opcionValida) {
			opcionValida = true;
			
			System.out.print("Elija una opcion: "); String opcion = entrada.nextLine();
			
			if (opcion.equals("1")) {
				if (controlador.getColeccionCarreras().size() > 0)
					seleccionarCarrera(controlador.getColeccionCarreras());
				else
					System.out.println("No hay carreras en el sistema");
			}
			else if (!opcion.equals("2")) {
				System.out.println("ERROR. Opcion invalida");
				opcionValida = false;
			}	
		}
	}
	
	public static void menuCrear() {
		System.out.println("************* MENU CREAR **************");
		System.out.println("1- Crear carrera");
		System.out.println("2- Crear materia");
		System.out.println("3- Crear asignatura");
		System.out.println("4- Volver");
		
		boolean opcionValida = false;
		
		while (!opcionValida) {
			opcionValida = true;
			
			System.out.print("Elija una opcion: "); String opcion = entrada.nextLine();
			
			if (opcion.equals("1"))
				crearCarrera();
			else if (opcion.equals("2"))
				crearMateria();
			else if (opcion.equals("3"))
				crearAsignatura();
			else if (!opcion.equals("4")) {
				System.out.println("ERROR. Opcion invalida");
				opcionValida = false;
			}
		}
	}
	
	public static void menuEliminar() {
		System.out.println("************* MENU ELIMINAR **************");
		System.out.println("1- Eliminar carrera");
		System.out.println("2- Eliminar materia");
		System.out.println("3- Eliminar asignatura");
		System.out.println("4- Salir");
		
		boolean opcionValida = false;
		
		while (!opcionValida) {
			opcionValida = true;
			
			System.out.print("Elija una opcion: "); String opcion = entrada.nextLine();
			
			if (opcion.equals("1"))
				eliminarCarrera();
			else if (opcion.equals("2"))
				eliminarMateria();
			else if (opcion.equals("3"))
				eliminarAsignatura();
			else if (!opcion.equals("4")) {
				System.out.println("ERROR. Opcion invalida");
				opcionValida = false;
			}
		}
	}
	
	public static void menuModificar() {
		System.out.println("************* MENU MODIFICAR **************");
		System.out.println("1- Modificar carrera");
		System.out.println("2- Modificar materia");
		System.out.println("3- Modificar asignatura");
		System.out.println("4- Salir");
		
		boolean opcionValida = false;
		
		while (!opcionValida) {
			opcionValida = true;
			
			System.out.print("Elija una opcion: "); String opcion = entrada.nextLine();
			
			if (opcion.equals("1"))
				modificarCarrera();
			else if (opcion.equals("2"))
				modificarMateria();
			else if (opcion.equals("3"))
				modificarAsignatura();
			else if (!opcion.equals("4")) {
				System.out.println("ERROR. Opcion invalida");
				opcionValida = false;
			}
		}
	}
	
	//FUNCION PRINCIPAL DEL PROGRAMA
	public static String seleccionarCarrera(Map<String, Carrera> carreras) {
		
		String nombreCarrera = "";
		System.out.println("CARRERAS");
		int contador = 1;
		for (Map.Entry<String, Carrera> c : carreras.entrySet()) {
			System.out.println(Integer.toString(contador) + "- " + c.getValue().getNombre());
			contador++;
		}
		
		boolean opcionValida = false;
		
		while (!opcionValida) {
			System.out.print("Elija una opcion: " ); String opcion = entrada.nextLine();
			contador = 1;
			for (Map.Entry<String, Carrera> c : carreras.entrySet()) {
				if (Integer.toString(contador).equals(opcion)) {
					mostrarCarrera(c.getValue());
					opcionValida = true;
					nombreCarrera = c.getKey();
				}
				contador++;
			}
			if (!opcionValida) {
				System.out.println("ERROR. Debe seleccionar una de las opciones disponibles.");
			}
		}
		
		return nombreCarrera;
	}
	
	public static void mostrarCarrera(Carrera c) {
		
		//COMPLETAR ATRIBUTOS
		controlador.setNombreCarrera(c.getNombre());
		controlador.setMaterias(c.getMaterias());
		for (Map.Entry<String, Materia> m: c.getMaterias().entrySet()) {
			controlador.setAsignaturas(m.getValue().getAsignaturas());
		}
		controlador.setCreditosObtenidos(0);
		controlador.setCreditosMin(c.getCantCreditosMin());
		controlador.setCreditosMax(c.getCantCreditosMax());
		controlador.setMailContacto("llullmarcos@gmail.com");
		
		
		//IMPRIMIR
		System.out.println("Carrera: " + controlador.getNombreCarrera());
		System.out.println("Creditos obtenidos: " + Integer.toString(controlador.getCreditosObtenidos()));
		System.out.println("Creditos minimos: " + Integer.toString(controlador.getCreditosMin()));
		System.out.println("Creditos maximos: " + Integer.toString(controlador.getCreditosMax()));
		System.out.println("Contacto: " + controlador.getMailContacto());
		System.out.println("MATERIAS");
		for (Map.Entry<String, Materia> m: c.getMaterias().entrySet()) {
			System.out.println(m.getValue().getNombre());
			System.out.println("   ASIGNATURAS");
			for (Map.Entry<String, Asignatura> a: m.getValue().getAsignaturas().entrySet())
				System.out.println("   -" + a.getValue().getNombre());
		}
	}
	
	public static void mostrarMateria(Materia m) {
		//IMPRIMIR
		System.out.println("Carrera: " + m.getNombreCarrera());
		System.out.println("Materia: " + m.getNombre());
		System.out.println("Cantidad de creditos: " + Integer.toString(m.getCantCreditos()));
		System.out.println("   ASIGNATURAS");
		for (Map.Entry<String, Asignatura> a: m.getAsignaturas().entrySet())
			System.out.println("   -" + a.getValue().getNombre());
	}
	
	public static void mostrarAsignatura(Asignatura a) {
		//IMPRIMIR
		System.out.println("Carrera: " + a.getNombreCarrera());
		System.out.println("Materia: " + a.getNombre());
		System.out.println("Cantidad de creditos: " + Integer.toString(a.getCantCreditos()));
		System.out.println("   PREVIAS");
		for (Map.Entry<String, Asignatura> p: a.getPrevias().entrySet())
			System.out.println("   -" + p.getValue().getNombre());
	}
	
	public static void crearCarrera() {
		String nombre = "";
		int creditosMin = -1;
		int creditosMax = -1;
		Map<String, Materia> materias = new HashMap<String, Materia>();
		
		nombre = ingresarNombreCarreraDisponible();
		
		String mensajeInput = "";
		String mensajeError = "";
		String mensajeErrorMax = "";
		Tipo tipo = Tipo.CARRERA;
		
		//CREDITOS MINIMOS
		mensajeInput = "Ingrese la cantidad de creditos minimos de la carrera: ";
		mensajeError = "La cantidad de creditos minimos de la carrera no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_CARRERA);
		creditosMin =  pedirCantCreditos(mensajeInput, mensajeError, tipo);
		
		//CREDITOS MAXIMOS
		mensajeInput = "Ingrese el total de creditos de la carrera: ";
		mensajeErrorMax = "La cantidad de creditos maximos no puede ser menor que los minimos";
		
		while (creditosMin > creditosMax) {
			creditosMax =  pedirCantCreditos(mensajeInput, mensajeError, tipo);
			if (creditosMin > creditosMax)
				System.out.println(mensajeErrorMax);
		}
		
		boolean carreraAgregada = controlador.agregarCarrera(nombre, creditosMin, creditosMax, materias);
		
		if (carreraAgregada) 
			System.out.println("Carrera creada con exito");
		else
			System.out.println("ERROR. No se pudo agregar la carrera a la coleccion");
	}
	
	public static void crearMateria() {
		
		if (controlador.getColeccionCarreras().size() > 0) {
			String nombreCarrera = "";
			String nombre = "";
			int cantCreditos = -1;
			Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
			
			//BUSCAR CARRERA
			nombreCarrera = pedirCarrera();
			
			//VERIFICAR NOMBRE
			nombre = ingresarNombreMateriaDisponible(nombreCarrera);
			
			//VERIFICAR CANT CREDITOS
			String mensajeInput = "Ingrese la cantidad de creditos de la materia: ";
			String mensajeError = "La cantidad de creditos de la materia no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_MATERIA);
			Tipo tipo = Tipo.MATERIA;
			cantCreditos = pedirCantCreditos(mensajeInput, mensajeError, tipo);
			
			controlador.agregarMateria(nombre, nombreCarrera, cantCreditos, asignaturas);
			System.out.println("Materia creada con exito");
		}
		else {
			System.out.println("ERROR. Debe crear una carrera primero");
		}
	}
	
	public static void crearAsignatura() {
		if (controlador.getColeccionCarreras().size() > 0) {
			
			Map<String, Carrera> carreras = controlador.getColeccionCarreras();
			if (existeUnaMateria(carreras)) {
				
				String nombreCarrera = "";
				String nombreMateria = "";
				String nombre = "";
				int cantCreditos = -1;
				boolean tienePrevias = false;
				Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
				
				//AUXILIARES
				String pregunta = "";
				String error = "";
				String mensajeInput = "Ingrese la cantidad de creditos de la asignatura: ";
				String mensajeError = "La cantidad de creditos de la asignatura no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_ASIGNATURA);
				
				nombreCarrera = pedirCarrera();
				nombreMateria = pedirMateria(nombreCarrera);
				Tipo tipo = Tipo.ASIGNATURA;
				cantCreditos = pedirCantCreditos(mensajeInput, mensajeError, tipo);
				nombre = ingresarNombreAsignaturaDisponible(nombreCarrera, nombreMateria);
				
				
				if (existeUnaAsignatura()) {
					pregunta = "La asignatura tiene previas (si/no)?: ";
					error = "ERROR. Debe responder 'si' o 'no'";
					
					tienePrevias = preguntarUsuarioBoolean(pregunta, error);
					if (!tienePrevias) {
						previas = new HashMap<String, Asignatura>();
						
						controlador.agregarAsignatura(nombre, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previas);
						
						System.out.println("Asignatura creada con exito");
					}
					else{
						boolean deseaAgregarPrevia = true;
						while (deseaAgregarPrevia) {
							
							Asignatura previa = null;
							while (previa == null) {
								previa = pedirAsignatura();
								
								if (previa == null)
									System.out.println("ERROR. Debe ingresar un numero que pertenezca a una asignatura");
								else
									previas.put(previa.getNombre(), previa);
							}
								
							pregunta = "Desea seguir agregando previas (si/no)?: ";
							error = "ERROR. Debe responder 'si' o 'no'";
							
							deseaAgregarPrevia = preguntarUsuarioBoolean(pregunta, error);
							if (!deseaAgregarPrevia) {
								controlador.agregarAsignatura(nombre, nombreMateria, nombreCarrera, cantCreditos, tienePrevias, 
										previas);
								
								System.out.println("Asignatura creada con exito");
							}
						}
					}
				}
				else {
					tienePrevias = false;
					previas = new HashMap<String, Asignatura>();
					controlador.agregarAsignatura(nombre, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previas);
					
					System.out.println("Asignatura creada con exito");
				}
			}
			else
				System.out.println("ERROR. Debe crear una materia primero");
		}
		else
			System.out.println("ERROR. Debe crear una carrera primero");
	}
	
	public static void eliminarCarrera() {
		String nombreCarrera = seleccionarCarrera(controlador.getColeccionCarreras());
		
		String mensajeInput = "Desea eliminar la carrera (si/no)? ";
		String mensajeError = "ERROR. Debe responder 'si' o 'no'";
		boolean deseaEliminar = preguntarUsuarioBoolean(mensajeInput, mensajeError);
		
		if (deseaEliminar) {
			controlador.removerCarrera(nombreCarrera);
			System.out.println("Se ha eliminado la carrera con exito");
		}
		else
			System.out.println("Se ha cancelado la operacion");
	}
	
	public static void eliminarMateria() {
		String nombreCarrera = pedirCarrera();
		String nombreMateria = pedirMateriaDeCarrera(nombreCarrera);
		
		//IMPRIMIR DATOS
		Carrera c = controlador.getColeccionCarreras().get(nombreCarrera);
		Materia m = c.getMaterias().get(nombreMateria);
		System.out.println("CARRERA: " + c.getNombre());
		System.out.println("MATERIA: " + m.getNombre());
		System.out.println("ASIGNATURAS: ");
		imprimirAsignaturasDeMateriaDeCarrera(nombreCarrera, nombreMateria);
		System.out.println("---------------------------------------------");
		//IMPRIMIR DATOS
		
		String mensajeInput = "Desea eliminar la materia (si/no)? ";
		String mensajeError = "ERROR. Debe responder 'si' o 'no'";
		boolean deseaEliminar = preguntarUsuarioBoolean(mensajeInput, mensajeError);
		
		if (deseaEliminar) {
			boolean seEstaModificando = false; //Se esta eliminando, no modificando
			boolean seRemovio = controlador.removerMateria(nombreMateria, nombreCarrera, seEstaModificando);
			if (seRemovio)
				System.out.println("Se ha eliminado la materia con exito");
			else
				System.out.println("ERROR. Debe eliminar las asignaturas de la materia para poder eliminarla");
		}
		else
			System.out.println("Se ha cancelado la operacion");
	}
	
	public static void eliminarAsignatura() {
		String nombreCarrera = pedirCarrera();
		String nombreMateria = pedirMateriaDeCarrera(nombreCarrera);
		String nombreAsignatura = pedirAsignaturaDeMateriaDeCarrera(nombreCarrera, nombreMateria);
		
		//IMPRIMIR DATOS
		Carrera c = controlador.getColeccionCarreras().get(nombreCarrera);
		Materia m = c.getMaterias().get(nombreMateria);
		Asignatura a = m.getAsignaturas().get(nombreAsignatura);
		System.out.println("CARRERA: " + c.getNombre());
		System.out.println("MATERIA: " + m.getNombre());
		System.out.println("ASIGNATURA: ");
		System.out.println("Nombre: " + a.getNombre());
		System.out.println("Cantidad de creditos: " + a.getCantCreditos());
		System.out.println("---------------------------------------------");
		//IMPRIMIR DATOS
		
		String mensajeInput = "Desea eliminar la asignatura (si/no)? ";
		String mensajeError = "ERROR. Debe responder 'si' o 'no'";
		boolean deseaEliminar = preguntarUsuarioBoolean(mensajeInput, mensajeError);
		
		if (deseaEliminar) {
			controlador.removerAsignatura(nombreAsignatura, nombreCarrera, nombreMateria);
			System.out.println("Se ha eliminado la asignatura con exito");
		}
		else
			System.out.println("Se ha cancelado la operacion");
	}
	
	public static void modificarCarrera() {
		String nombre = "";
		String nombreAntes = "";
		int creditosMin = -1;
		int creditosMax = -1;
		
		nombreAntes = pedirCarrera();
		
		String mensajeInput = "";
		String mensajeError = "";
		String mensajeErrorMax = "";
		Tipo tipo = Tipo.CARRERA;
		
		
		//CREDITOS MINIMOS
		mensajeInput = "Ingrese la cantidad de creditos minimos de la carrera: ";
		mensajeError = "La cantidad de creditos minimos de la carrera no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_CARRERA);
		creditosMin =  pedirCantCreditos(mensajeInput, mensajeError, tipo);
		
		//CREDITOS MAXIMOS
		mensajeInput = "Ingrese el total de creditos de la carrera: ";
		mensajeErrorMax = "La cantidad de creditos maximos no puede ser menor que los minimos";
		
		while (creditosMin > creditosMax) {
			creditosMax =  pedirCantCreditos(mensajeInput, mensajeError, tipo);
			if (creditosMin > creditosMax)
				System.out.println(mensajeErrorMax);
		}
		
		//String nombreAntes = seleccionarCarrera(controlador.getColeccionCarreras());
		nombre = ingresarNombreCarreraDisponible(nombreAntes);
		boolean carreraModificada = controlador.modificarCarrera(nombre, nombreAntes, creditosMin, creditosMax);
		
		if (carreraModificada) {
			System.out.println("Carrera modificada con exito");
			System.out.println("-------------------------------------------------");
			
			mostrarCarrera(controlador.getColeccionCarreras().get(nombre));
		}
		else
			System.out.println("ERROR. No se pudo modificar la carrera de la coleccion");	
	}
	
	public static void modificarMateria() {
		String nombre = "";
		String nombreAntes = "";
		String nombreCarrera = "";
		int cantCreditos = -1;
		
		nombreCarrera = pedirCarrera();
		nombreAntes = pedirMateria(nombreCarrera);
		
		String mensajeInput = "";
		String mensajeError = "";
		Tipo tipo = Tipo.MATERIA;
		
		mensajeInput = "Ingrese la cantidad de creditos de la materia: ";
		mensajeError = "La cantidad de creditos de la materia no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_MATERIA);
		cantCreditos = pedirCantCreditos(mensajeInput, mensajeError, tipo);
		
		nombre = ingresarNombreMateriaDisponible(nombreAntes, nombreCarrera);
		boolean materiaModificada = controlador.modificarMateria(nombre, nombreAntes, nombreCarrera, cantCreditos);
		
		if (materiaModificada) {
			System.out.println("Materia modificada con exito");
			System.out.println("-------------------------------------------------");
			
			mostrarMateria(controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().get(nombre));
		}
		else
			System.out.println("ERROR. No se pudo modificar la materia de la coleccion");
	}
	public static void modificarAsignatura() {
		String nombre = "";
		String nombreAntes = "";
		String nombreCarrera = "";
		String nombreMateria = "";
		int cantCreditos = -1;
		
		nombreCarrera = pedirCarrera();
		nombreMateria = pedirMateria(nombreCarrera);
		nombreAntes = pedirAsignatura(nombreCarrera, nombreMateria);
		
		String mensajeInput = "";
		String mensajeError = "";
		Tipo tipo = Tipo.ASIGNATURA;
		
		mensajeInput = "Ingrese la cantidad de creditos de la asignatura: ";
		mensajeError = "La cantidad de creditos de la asignatura no puede ser menor a " + Integer.toString(Controlador.CREDITOS_MIN_ASIGNATURA);
		cantCreditos = pedirCantCreditos(mensajeInput, mensajeError, tipo);
		
		nombre = ingresarNombreAsignaturaDisponible(nombreAntes, nombreCarrera, nombreMateria);
		/*boolean asignaturaModificada = controlador.modificarAsignatura(nombre, nombreAntes, nombreCarrera, nombreMateria, cantCreditos);
		
		if (asignaturaModificada) {
			System.out.println("Asignatura modificada con exito");
			System.out.println("-------------------------------------------------");
			
			mostrarAsignatura(controlador.getColeccionCarreras().get(nombreCarrera).getMaterias().get(nombreMateria).getAsignaturas().get(nombre));
		}
		else
			System.out.println("ERROR. No se pudo modificar la asignatura de la coleccion");*/
	}
	
	public void iniciarVentana() {
		Inicio ventana = Inicio.getInstancia();
	}
	
	public static void main(String[] args) {
		//Main.iniciarApliacion();
		//System.out.println("Ha salido del programa");
		Main m = new Main();
		Controlador.agregarCarrera("Tecnologo en informatica", 150, 250, new HashMap<String, Materia>());
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
		Controlador.agregarAsignatura("Matematicas discretas y logica I", "Tecnologo en informatica", "Matematicas", 12, false, new HashMap<String, Asignatura>());
		
		/*System.out.println("-------------------------------------------------------------");
		for (Map.Entry<String, Carrera> c : Controlador.getColeccionCarreras().entrySet()) {
			System.out.println("Carrera: " + c.getValue().getNombre());
			for (Map.Entry<String, Materia> ma : c.getValue().getMaterias().entrySet())
				System.out.println("	Materia: " + ma.getValue().getNombre());
		}
		System.out.println("-------------------------------------------------------------");*/
		m.iniciarVentana();
	}
}
