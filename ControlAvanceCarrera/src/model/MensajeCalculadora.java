package model;

public class MensajeCalculadora {
	private static String mensaje1 = "->Necesita mas créditos para completar la carrera";
	private static String mensaje2 = "->Necesita acumular mas créditos en una o mas materias para aprobarla/s";
	private static String mensaje3 = "->Créditos suficientes para completar la carrera";
	
	public static String[] mensaje(boolean creditosCarreraOK, boolean creditosPorMateriaOK) {
		String[] mensajes;
		if (creditosCarreraOK && creditosPorMateriaOK) {
			mensajes = new String[1];
			mensajes[0] = mensaje3;
		}
		else if (creditosPorMateriaOK) {
			mensajes = new String[1];
			mensajes[0] = mensaje2;
		}
		else if (creditosCarreraOK) {
			mensajes = new String[1];
			mensajes[0] = mensaje1;
		}
		else {
			mensajes = new String[2];
			mensajes[0] = mensaje1;
			mensajes[1] = mensaje2;
		}
		return mensajes;
	}
}
