package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controlador;
import model.Asignatura;
import model.Carrera;
import model.ManejadorCarrera;
import model.Materia;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class EliminarAsignatura extends JPanel{
	private static EliminarAsignatura instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JLabel materiasJL;
	private JComboBox<String> materiasJCB;
	private JLabel asignaturasJL;
	private JComboBox<String> asignaturasJCB;	
	private JButton cancelarJB;
	private JButton confirmarJB;
	
	private EliminarAsignatura() {
		this.setLayout(new GridLayout(4, 2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getMateriasJL());
		this.add(getMateriasJCB());
		this.add(getAsignaturasJL());
		this.add(getAsignaturasJCB());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static EliminarAsignatura getInstancia() {
		if (instancia == null) {
			instancia = new EliminarAsignatura();
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
		}
		return materiasJL;
	}
	
	public JComboBox<String> getMateriasJCB(){
		if (materiasJCB == null) {
			
			materiasJCB = new JComboBox<String>();
			
			ActionListener elegirMateria = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (materiasJCB.getSelectedItem() != null) {
						getAsignaturasJCB();
						limpiarAsignaturas();
						agregarAsignaturas();
					}
				}
			};
			materiasJCB.addActionListener(elegirMateria);
		}
		return materiasJCB;
	}
	
	public JLabel getAsignaturasJL() {
		if (asignaturasJL == null) {
			asignaturasJL = new JLabel("Asignatura");
		}
		return asignaturasJL;
	}
	
	public JComboBox<String> getAsignaturasJCB() {
		if (asignaturasJCB == null) {
			asignaturasJCB = new JComboBox<String>();
		}
		return asignaturasJCB;
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
					
					if (getCarrerasJCB().getSelectedItem() != null && 
						getMateriasJCB().getSelectedItem() != null) {
						
						String nombreCarrera = getCarrerasJCB().getSelectedItem().toString();
						String nombreMateria = getMateriasJCB().getSelectedItem().toString();
						String nombreAsignatura = getAsignaturasJCB().getSelectedItem().toString();
						if (eliminarAsignatura(nombreCarrera, nombreMateria, nombreAsignatura)) {
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
	
	public boolean eliminarAsignatura(String nombreCarrera, String nombreMateria, String nombreAsignatura) {
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
		
		if (existeCarrera) {		
			
			Carrera c = mc.getCarreras().get(nombreCarrera);
			boolean existeMateria = c.getMaterias().containsKey(nombreMateria);
			if (existeMateria) {
				
				Materia m = c.getMaterias().get(nombreMateria);
				boolean existeAsignatura = m.getAsignaturas().containsKey(nombreAsignatura);
				if (existeAsignatura) {
					if (Controlador.removerAsignatura(nombreAsignatura, nombreCarrera, nombreMateria)) {
						MostrarMensaje.asignaturaEliminada();
						return true;
					}
					else
						MostrarMensaje.debug("Esta mal el metodo 'Controlador.removerAsignatura'");
				}
				else
					MostrarMensaje.debug("No seas cabeza de huevo");
			}
		}
		else
			MostrarMensaje.errorImposible();
		return false;
	}

	public void limpiarCampos() {
		limpiarMaterias();
		limpiarAsignaturas();
	}
	
	public void limpiarMaterias() {
		getMateriasJCB().removeAllItems();
	}
	
	public void limpiarAsignaturas() {
		getAsignaturasJCB().removeAllItems();
	}
	
	public void agregarMaterias() {
		Map<String, Materia> materias = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias();
		
		for (Map.Entry<String, Materia> m : materias.entrySet())
			materiasJCB.addItem(m.getValue().getNombre().toString());
	}
	
	public void agregarAsignaturas() {
		Map<String, Asignatura> asignaturas = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias().get(getMateriasJCB().getSelectedItem().toString()).getAsignaturas();
		
		for (Map.Entry<String, Asignatura> a : asignaturas.entrySet())
			asignaturasJCB.addItem(a.getValue().getNombre().toString());
	}
	
}

