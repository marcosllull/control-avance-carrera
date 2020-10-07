package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodosAux {
	
	public static boolean validarNombre(String cadena) {
		
		if (cadena.isBlank())
			return false;
		
		Pattern patron = Pattern.compile("^([a-z]| |á|é|í|ó)+$", Pattern.CASE_INSENSITIVE);
		Matcher match = patron.matcher(cadena);
		boolean nombreValido = match.find();
		
		if (nombreValido)
			return true;
		else
			return false;
	}
	
	public static boolean validarSizeNombre(String cadena) {
		return (cadena.length() <= 80) ? true : false; 
	}
}
