package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controlador;
import model.Asignatura;
import model.Carrera;
import model.ManejadorCarrera;
import model.MetodosAux;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class CrearMateria extends JPanel{
	
	private static CrearMateria instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JLabel nombreJL;
	private JTextField nombreJTF;
	private JLabel cantCreditosJL;
	private JTextField cantCreditosJTF;
	private JButton confirmarJB;
	private JButton cancelarJB;
	
	private CrearMateria() {
		this.setLayout(new GridLayout(4, 2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getNombreJL());
		this.add(getNombreJTF());
		this.add(getCantCreditosJL());
		this.add(getCantCreditosJTF());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static CrearMateria getInstancia() {
		if (instancia == null) {
			if (Controlador.getColeccionCarreras().size() > 0)
				instancia = new CrearMateria();
			else
				MostrarMensaje.errorNoExistenCarreras();
				
		}
		return instancia;
	}
	
	public void setVentanaPrincipal(Inicio ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	public JLabel getCarrerasJL() {
		if (carrerasJL == null) {
			carrerasJL = new JLabel("Carreras");
		}
		return carrerasJL;
	}
	
	public JComboBox<String> getCarrerasJCB(){
		if (carrerasJCB == null) {
			carrerasJCB = new JComboBox<String>();
			Map<String, Carrera> carreras = Controlador.getColeccionCarreras();
			for (Map.Entry<String, Carrera> c : carreras.entrySet())
				carrerasJCB.addItem(c.getValue().getNombre());
		}
		return carrerasJCB;
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
	
	public JLabel getCantCreditosJL() {
		if (cantCreditosJL == null) {
			cantCreditosJL = new JLabel("Cantidad de creditos");
		}
		return cantCreditosJL;
	}
	
	public JTextField getCantCreditosJTF() {
		if (cantCreditosJTF == null) {
			cantCreditosJTF = new JTextField();
		}
		return cantCreditosJTF;
	}
	
	public JButton getCancelarJB() {
		if (cancelarJB == null) {
			cancelarJB = new JButton("Cancelar");
			
			ActionListener cancelar = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					ventanaPrincipal.removerComponentesPanelCentral();
					instancia = null;
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
					if (agregarMateria(getCarrerasJCB(), getNombreJTF(), getCantCreditosJTF())) {
						ventanaPrincipal.removerComponentesPanelCentral();
						instancia = null;
					}
			    }
			};
			confirmarJB.addActionListener(confirmar);
		}
		return confirmarJB;
	}
	
	public boolean agregarMateria(JComboBox<String> carreras, JTextField nombreJTF, JTextField creditosJTF) {
		String nombreCarrera;
		String nombreMateria;
		int cantCreditos;
		
		nombreCarrera = carreras.getSelectedItem().toString();
		nombreMateria = nombreJTF.getText();
		try {
			cantCreditos = Integer.parseInt(creditosJTF.getText());
			
			if (MetodosAux.validarNombre(nombreCarrera) &&
				MetodosAux.validarNombre(nombreMateria) &&
				MetodosAux.validarSizeNombre(nombreCarrera) &&
				MetodosAux.validarSizeNombre(nombreMateria) &&
				cantCreditos >= Controlador.CREDITOS_MIN_MATERIA) {
						
				ManejadorCarrera mc = ManejadorCarrera.getInstancia();
				
				boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
				if (existeCarrera) {
					
					boolean existeMateria = mc.getCarreras().get(nombreCarrera).getMaterias().containsKey(nombreMateria);
					if (!existeMateria) {

						Controlador.agregarMateria(nombreMateria, nombreCarrera, cantCreditos, new HashMap<String, Asignatura>());
						MostrarMensaje.materiaAgregada();
						return true;
					}
					else
						MostrarMensaje.errorExisteMateria();
				}
				else
					MostrarMensaje.errorImposible();
			}
			else {
				if (!MetodosAux.validarNombre(nombreCarrera))
					MostrarMensaje.errorNombre();
				if (!MetodosAux.validarNombre(nombreMateria))
					MostrarMensaje.errorNombre();
				if (!MetodosAux.validarSizeNombre(nombreCarrera))
					MostrarMensaje.errorSizeNombreCarrera();
				if (!MetodosAux.validarSizeNombre(nombreMateria))
					MostrarMensaje.errorSizeNombreMateria();
				if (cantCreditos < Controlador.CREDITOS_MIN_MATERIA)
					MostrarMensaje.errorCreditosMinMateria();
			}
		}
		catch(NumberFormatException e) {
			MostrarMensaje.errorFormatoCreditos();
		}
		return false;
	}
}
