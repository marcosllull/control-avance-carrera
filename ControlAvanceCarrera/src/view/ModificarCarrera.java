package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controlador;
import model.Carrera;
import model.ManejadorCarrera;
import model.MetodosAux;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class ModificarCarrera extends JPanel{
	private static ModificarCarrera instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JLabel carreraJL;
	private JTextField carreraJTF;
	private JLabel cantCreditosMinJL;
	private JTextField cantCreditosMinJTF;	
	private JLabel cantCreditosMaxJL;
	private JTextField cantCreditosMaxJTF;
	private JButton confirmarJB;
	private JButton cancelarJB;
	
	private ModificarCarrera() {
		this.setLayout(new GridLayout(5, 2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getCarreraJL());
		this.add(getCarreraJTF());
		this.add(getCantCreditosMinJL());
		this.add(getCantCreditosMinJTF());
		this.add(getCantCreditosMaxJL());
		this.add(getCantCreditosMaxJTF());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static ModificarCarrera getInstancia() {
		if (instancia == null) {
			instancia = new ModificarCarrera();
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
			
			ActionListener elegirCarrera = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (carrerasJCB.getSelectedItem() != null) {
						completarCampos(carrerasJCB.getSelectedItem().toString());
					}
				}
			};
			carrerasJCB.addActionListener(elegirCarrera);
			
			if (carrerasJCB.getItemCount() > 0) {
				carrerasJCB.setSelectedIndex(0);
				completarCampos(carrerasJCB.getSelectedItem().toString());
			}
		}
		return carrerasJCB;
	}
	
	public JLabel getCarreraJL() {
		if (carreraJL == null) {
			carreraJL = new JLabel("Carrera");
		}
		return carreraJL;
	}
	
	public JTextField getCarreraJTF() {
		if (carreraJTF == null) {
			carreraJTF = new JTextField();
		}
		return carreraJTF;
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
					if (getCarrerasJCB().getSelectedItem() != null) {	
						if (modificarCarrera(getCarrerasJCB().getSelectedItem().toString())) {
							
							ventanaPrincipal.removerComponentesPanelCentral();
							instancia = null;
						}
					}
					else
						MostrarMensaje.errorFaltaSeleccionarCarrera();
			    }
			};
			confirmarJB.addActionListener(confirmar);
		}
		return confirmarJB;
	}
	
	public void completarCampos(String nombreCarrera) {
		
		boolean existeCarrera = ManejadorCarrera.getInstancia().getCarreras().containsKey(nombreCarrera);
		if (existeCarrera) {
			Carrera c = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera);
			getCarreraJTF().setText(c.getNombre());
			getCantCreditosMinJTF().setText(Integer.toString(c.getCantCreditosMin()));
			getCantCreditosMaxJTF().setText(Integer.toString(c.getCantCreditosMax()));
		}
	}
	
	public boolean modificarCarrera(String nombreAntes) {
		
		String nombreDespues = "";
		int creditosMin = -1;
		int creditosMax = -1;
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreAntes);
		if (existeCarrera) {
			
			if (MetodosAux.validarNombre(getCarreraJTF().getText())) {
				
				nombreDespues = getCarreraJTF().getText();
				
				try {
					
					creditosMin = Integer.parseInt(getCantCreditosMinJTF().getText());					
					try {
						
						creditosMax = Integer.parseInt(getCantCreditosMaxJTF().getText());
						if (creditosMin >= Controlador.CREDITOS_MIN_CARRERA && creditosMin <= creditosMax) {
							
							Controlador.modificarCarrera(nombreDespues, nombreAntes, creditosMin, creditosMax);
							MostrarMensaje.carreraModificada();
							return true;
						}
						else {
							if (creditosMin < Controlador.CREDITOS_MIN_CARRERA)
								MostrarMensaje.errorCreditosMinCarrera();
							if (creditosMin > creditosMax)
								MostrarMensaje.errorCreditosMax();
						}
					}
					catch(NumberFormatException e) {
						MostrarMensaje.errorFormatoCreditosMax();
					}
				}
				catch(NumberFormatException e) {
					MostrarMensaje.errorFormatoCreditosMin();
				}
			}
			else
				MostrarMensaje.errorNombre();
		}
		else
			MostrarMensaje.errorImposible();
		return false;
	}
}
