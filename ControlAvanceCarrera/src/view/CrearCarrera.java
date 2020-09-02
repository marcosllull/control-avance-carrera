package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controlador;
import model.ManejadorCarrera;
import model.Materia;
import model.MetodosAux;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class CrearCarrera extends JPanel{
	
	private static CrearCarrera instancia;
	private static JFrame padre;
	private JTextField nombreJTF;
	private JLabel nombreJL;
	private JTextField cantCreditosMinJTF;
	private JLabel cantCreditosMinJL;
	private JTextField cantCreditosMaxJTF;	
	private JLabel cantCreditosMaxJL;
	private JButton confirmarJB;
	private JButton cancelarJB;
	
	private CrearCarrera() {
		this.setLayout(new GridLayout(4, 2));
		this.add(getNombreJL());
		this.add(getNombreJTF());
		this.add(getCantCreditosMinJL());
		this.add(getCantCreditosMinJTF());
		this.add(getCantCreditosMaxJL());
		this.add(getCantCreditosMaxJTF());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static void setPadre(JFrame padre) {
		CrearCarrera.padre = padre;
	}
	
	public static CrearCarrera getInstancia() {
		if (instancia == null) {
			instancia = new CrearCarrera();
		}
		return instancia;
	}
	
	public JLabel getNombreJL() {
		if (nombreJL == null) {
			nombreJL = new JLabel("Nombre");			
		}
		return nombreJL;
	}
	
	public JTextField getNombreJTF() {
		if (nombreJTF == null) {
			nombreJTF = new JTextField();
		}
		return nombreJTF;
	}
	
	public JLabel getCantCreditosMinJL() {
		if (cantCreditosMinJL == null) {
			cantCreditosMinJL = new JLabel("Cantidad de creditos minimos");
		}
		return cantCreditosMinJL;
	}
	
	public JTextField getCantCreditosMinJTF() {
		if (cantCreditosMinJTF == null) {
			cantCreditosMinJTF = new JTextField();
		}
		return cantCreditosMinJTF;
	}
	
	public JLabel getCantCreditosMaxJL() {
		if (cantCreditosMaxJL == null) {
			cantCreditosMaxJL = new JLabel("Cantidad de creditos maximos");
		}
		return cantCreditosMaxJL;
	}
	
	public JTextField getCantCreditosMaxJTF() {
		if (cantCreditosMaxJTF == null) {
			cantCreditosMaxJTF = new JTextField();
		}
		return cantCreditosMaxJTF;
	}
	
	public JButton getCancelarJB() {
		if (cancelarJB == null) {
			cancelarJB = new JButton("Cancelar");
			
			ActionListener cancelar = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					cancelarJB.setText("Presionado");
			    }
			};
			cancelarJB.addActionListener(cancelar);
		}
		return cancelarJB;
	}
	
	public JButton getConfirmarJB() {
		if (confirmarJB == null) {
			confirmarJB = new JButton("Confirmar");
			
			ActionListener confirmar = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					confirmarJB.setText("Presionado");
					agregarCarrera(getNombreJTF(), getCantCreditosMinJTF(), getCantCreditosMaxJTF());
			    }
			};
			confirmarJB.addActionListener(confirmar);
		}
		return confirmarJB;
	}
	
	public boolean agregarCarrera(JTextField nombreJTF, JTextField creditosMinJTF, JTextField creditosMaxJTF) {
		String nombre;
		int creditosMin;
		int creditosMax;
		
		nombre = nombreJTF.getText();
		try {
			creditosMin = Integer.parseInt(creditosMinJTF.getText());
			
			try {
				creditosMax = Integer.parseInt(creditosMaxJTF.getText());
				
				if (MetodosAux.validarNombre(nombre) &&
					creditosMin >= Controlador.CREDITOS_MIN_CARRERA && creditosMin <= creditosMax) {
							
					ManejadorCarrera mc = ManejadorCarrera.getInstancia();
					
					boolean existeCarrera = mc.getCarreras().containsKey(nombre);
					if (!existeCarrera) {
						Controlador.agregarCarrera(nombre, creditosMin, creditosMax, new HashMap<String, Materia>());
						MostrarMensaje.carreraAgregada(CrearCarrera.padre);
						return true;
					}
				}
				else {
					if (!MetodosAux.validarNombre(nombre))
						MostrarMensaje.errorNombre(CrearCarrera.padre);
					if (creditosMin < Controlador.CREDITOS_MIN_CARRERA)
						MostrarMensaje.errorCreditosMin(CrearCarrera.padre);
					if (creditosMin > creditosMax)
						MostrarMensaje.errorCreditosMax(CrearCarrera.padre);
				}
			}
			catch(NumberFormatException e) {
				MostrarMensaje.errorFormatoCreditosMax(CrearCarrera.padre);
			}
		}
		catch(NumberFormatException e) {
			MostrarMensaje.errorFormatoCreditosMin(CrearCarrera.padre);
		}
		return false;
	}
}
