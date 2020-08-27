package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetodosAux {
	
	public static boolean validarNombre(String cadena) {
		
		if (cadena.isBlank())
			return false;
		
		Pattern patron = Pattern.compile("^([a-z]| |�|�|�|�)+$", Pattern.CASE_INSENSITIVE);
		Matcher match = patron.matcher(cadena);
		boolean nombreValido = match.find();
		
		if (nombreValido)
			return true;
		else
			return false;
	}
}
