package view;

import javax.swing.JOptionPane;

public class Ayuda {
	public static void mostrarAyuda() {
		
		String mensaje = "Due�o y desarrollador: Marcos Llull"
				+ "\nVersi�n: 1.0"
				+ "\nESTO ES MIO, SI ME LO ROBAS, TE CAE DENUNCIA CRACK";
		
		JOptionPane.showMessageDialog(null,
				mensaje,
			    "Sobre Control Avance Carrera",
			    JOptionPane.INFORMATION_MESSAGE);
	}
}
