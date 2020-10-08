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
import model.Fuente;
import model.ManejadorCarrera;
import model.Materia;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class EliminarMateria extends JPanel{
	private static EliminarMateria instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JLabel materiasJL;
	private JComboBox<String> materiasJCB;
	private JLabel cantCreditosJL;
	private JTextField cantCreditosJTF;	
	private JButton cancelarJB;
	private JButton confirmarJB;
	
	private EliminarMateria() {
		this.setLayout(new GridLayout(4, 2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getMateriasJL());
		this.add(getMateriasJCB());
		this.add(getCantCreditosJL());
		this.add(getCantCreditosJTF());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static EliminarMateria getInstancia() {
		if (instancia == null) {
			instancia = new EliminarMateria();
		}
		return instancia;
	}
	
	public void setVentanaPrincipal(Inicio ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	public JLabel getCarrerasJL() {
		if (carrerasJL == null) {
			carrerasJL = new JLabel("Carreras");
			carrerasJL.setFont(Fuente.label());
		}
		return carrerasJL;
	}
	
	public JComboBox<String> getCarrerasJCB(){
		if (carrerasJCB == null) {
			carrerasJCB = new JComboBox<String>();
			carrerasJCB.setFont(Fuente.comboBox());
			
			Map<String, Carrera> carreras = Controlador.getColeccionCarreras();
			for (Map.Entry<String, Carrera> c : carreras.entrySet())
				carrerasJCB.addItem(c.getValue().getNombre());
			
			ActionListener elegirCarrera = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (carrerasJCB.getSelectedItem() != null) {
						getMateriasJCB();
						limpiarCampos();
						agregarMaterias();
					}
				}
			};
			carrerasJCB.addActionListener(elegirCarrera);
			
			if (carrerasJCB.getItemCount() > 0) {
				carrerasJCB.setSelectedIndex(0);
			}
		}
		return carrerasJCB;
	}
	
	public JLabel getMateriasJL() {
		if (materiasJL == null) {
			materiasJL = new JLabel("Materias");
			materiasJL.setFont(Fuente.label());
		}
		return materiasJL;
	}
	
	public JComboBox<String> getMateriasJCB(){
		if (materiasJCB == null) {
			
			materiasJCB = new JComboBox<String>();
			materiasJCB.setFont(Fuente.comboBox());
			
			ActionListener elegirMateria = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (materiasJCB.getSelectedItem() != null) {
						completarCampos(carrerasJCB.getSelectedItem().toString(), materiasJCB.getSelectedItem().toString());
					}
				}
			};
			materiasJCB.addActionListener(elegirMateria);
		}
		return materiasJCB;
	}
	
	public JLabel getCantCreditosJL() {
		if (cantCreditosJL == null) {
			cantCreditosJL = new JLabel("Cantidad de creditos");
			cantCreditosJL.setFont(Fuente.label());
		}
		return cantCreditosJL;
	}
	
	public JTextField getCantCreditosJTF() {
		if (cantCreditosJTF == null) {
			cantCreditosJTF = new JTextField();
			cantCreditosJTF.setFont(Fuente.textField());
		}
		return cantCreditosJTF;
	}
	
	public JButton getCancelarJB() {
		if (cancelarJB == null) {
			cancelarJB = new MiBoton("Cancelar", Colores.COLOR_BG_CANCELAR, Colores.COLOR_BG_CANCELAR_OVER, Colores.COLOR_BG_CANCELAR_PRESIONADO);
			cancelarJB.setFont(Fuente.button());
			
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
			confirmarJB = new MiBoton("Confirmar", Colores.COLOR_BG_CONFIRMAR, Colores.COLOR_BG_CONFIRMAR_OVER, Colores.COLOR_BG_CONFIRMAR_PRESIONADO);
			confirmarJB.setFont(Fuente.button());
			
			ActionListener confirmar = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					if (getCarrerasJCB().getSelectedItem() != null && 
						getMateriasJCB().getSelectedItem() != null) {
						
						String nombreCarrera = getCarrerasJCB().getSelectedItem().toString();
						String nombreMateria = getMateriasJCB().getSelectedItem().toString(); 
						if (eliminarMateria(nombreCarrera, nombreMateria)) {
							ventanaPrincipal.removerComponentesPanelCentral();
							instancia = null;
						}
					}
			    }
			};
			confirmarJB.addActionListener(confirmar);
		}
		return confirmarJB;
	}
	
	public boolean eliminarMateria(String nombreCarrera, String nombreMateria) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {		
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreMateria);
			if (existeMateria) {
				if (Controlador.removerMateria(nombreMateria, nombreCarrera, false)) {
					MostrarMensaje.materiaEliminada();
					return true;
				}
				else
					MostrarMensaje.errorFaltaEliminarAsignaturasAsociadas();
			}
		}
		else
			MostrarMensaje.errorImposible();
		return false;
	}
	
	public void completarCampos(String nombreCarrera, String nombreMateria) {
		
		boolean existeCarrera = ManejadorCarrera.getInstancia().getCarreras().containsKey(nombreCarrera);
		if (existeCarrera) {
			Carrera c = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreMateria);
			if (existeMateria) {
				getCantCreditosJTF().setText(Integer.toString(c.getMaterias().get(nombreMateria).getCantCreditos()));
			}
		}
	}
	
	public void limpiarCampos() {
		getMateriasJCB().removeAllItems();
		getCantCreditosJTF().setText("");
	}
	
	public void agregarMaterias() {
		Map<String, Materia> materias = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias();
		
		for (Map.Entry<String, Materia> m : materias.entrySet())
			materiasJCB.addItem(m.getValue().getNombre().toString());
	}
	
}
