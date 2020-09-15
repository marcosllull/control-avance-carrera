package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controlador;
import model.Carrera;
import model.ManejadorCarrera;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class EliminarCarrera extends JPanel{
	private static EliminarCarrera instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JTextField cantCreditosMinJTF;
	private JLabel cantCreditosMinJL;
	private JTextField cantCreditosMaxJTF;	
	private JLabel cantCreditosMaxJL;
	private JButton confirmarJB;
	private JButton cancelarJB;
	
	private EliminarCarrera() {
		this.setLayout(new GridLayout(4, 2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getCantCreditosMinJL());
		this.add(getCantCreditosMinJTF());
		this.add(getCantCreditosMaxJL());
		this.add(getCantCreditosMaxJTF());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static EliminarCarrera getInstancia() {
		if (instancia == null) {
			instancia = new EliminarCarrera();
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
						if (eliminarCarrera(getCarrerasJCB().getSelectedItem().toString())) {
							
							//Elimina una carrera del menu principal de forma dinamica
							String nombreCarrera = getCarrerasJCB().getSelectedItem().toString();
							for (int i = 0; i < ventanaPrincipal.getItemsSeleccionar().size(); i++) {
								if (ventanaPrincipal.getItemsSeleccionar().get(i) != null) {
									if (ventanaPrincipal.getItemsSeleccionar().get(i).getText().equals(nombreCarrera) ) {
										ventanaPrincipal.getItemsSeleccionar().remove(ventanaPrincipal.getItemsSeleccionar().get(i));
										ventanaPrincipal.getMenuSeleccionar().removeAll();
										for (JMenuItem mi : ventanaPrincipal.getItemsSeleccionar()) {
											ventanaPrincipal.getMenuSeleccionar().add(mi);
										}
										break;
									}
								}
							}
							//Elimina una carrera del menu principal de forma dinamica
							
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
	
	public boolean eliminarCarrera(String nombreCarrera) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {		
			Controlador.removerCarrera(nombreCarrera);
			MostrarMensaje.carreraEliminada();
			return true;
		}
		else
			MostrarMensaje.errorImposible();
		return false;
	}
	
	public void completarCampos(String nombreCarrera) {
		
		boolean existeCarrera = ManejadorCarrera.getInstancia().getCarreras().containsKey(nombreCarrera);
		if (existeCarrera) {
			Carrera c = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera);
			getCantCreditosMinJTF().setText(Integer.toString(c.getCantCreditosMin()));
			getCantCreditosMaxJTF().setText(Integer.toString(c.getCantCreditosMax()));
		}
	}
}
