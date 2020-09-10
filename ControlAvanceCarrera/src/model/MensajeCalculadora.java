package model;

import java.util.ArrayList;

public class MensajeCalculadora {
	private static ArrayList<String> mensajes;
	private static String mensaje1 = "Necesita acumular mas cr�ditos en una o mas materias para aprobarla/s";
	private static String mensaje2 = "Necesita mas cr�ditos para completar la carrera";
	private static String mensaje3 = "Cr�ditos suficientes para completar la carrera";
	
	public static String aprobadoSiNo(boolean creditosPorMateriaOK, boolean creditosCarreraOK) {

		if (creditosCarreraOK && creditosPorMateriaOK)
			return mensaje3;
		else if (creditosCarreraOK)
			return mensaje2;
		else if (creditosPorMateriaOK)
			return mensaje1;
		else {
			mensajes = new ArrayList<String>();
			mensajes.add(mensaje1);
			mensajes.add(mensaje2);
			String mensaje = "";
			for (String s : mensajes) {
				if (!mensaje.equals(""))
					mensaje += "\n" + s;				
				else
					mensaje = s;
			}
			return mensaje;
		}
	}
}
