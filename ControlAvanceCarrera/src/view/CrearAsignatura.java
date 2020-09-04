package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Controlador;
import model.Asignatura;
import model.Carrera;
import model.ManejadorCarrera;
import model.Materia;
import model.MetodosAux;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class CrearAsignatura extends JPanel{
	
	private static CrearAsignatura instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JLabel materiasJL;
	private JComboBox<String> materiasJCB;
	private JLabel nombreJL;
	private JTextField nombreJTF;
	private JLabel cantCreditosJL;
	private JTextField cantCreditosJTF;
	private JLabel tienePreviasJL;
	private ButtonGroup tienePreviasBG;
	private JPanel tienePreviasJP;
	private JRadioButton tienePreviasJRB_SI;
	private JRadioButton tienePreviasJRB_NO;
	private JLabel agregarPreviaJL;
	private JComboBox<String> asignaturasJCB;
	private JLabel previasAgregadasJL;
	private ArrayList<JButton> previasAL;
	private JPanel previasJP;
	private JScrollPane previasJSP;
	private JButton confirmarJB;
	private JButton cancelarJB;
	
	private CrearAsignatura() {
		this.setLayout(new GridLayout(8,2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getMateriasJL());
		this.add(getMateriasJCB());
		this.add(getNombreJL());
		this.add(getNombreJTF());
		this.add(getCantCreditosJL());
		this.add(getCantCreditosJTF());
		this.add(getTienePreviasJL());
		this.add(getTienePreviasJP());
		this.add(getAgregarPreviaJL());
		this.add(getAsignaturasJCB());
		this.add(getPreviasAgregadasJL());
		this.add(getPreviasJSP());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
	}
	
	public static CrearAsignatura getInstancia() {
		if (instancia == null) {
			if (Controlador.getColeccionCarreras().size() > 0) {
				boolean existeCarreraConMaterias = false;
				for (Map.Entry<String, Carrera> carrera : Controlador.getColeccionCarreras().entrySet()) {
					if (carrera.getValue().getMaterias().size() > 0) {
						instancia = new CrearAsignatura();
						existeCarreraConMaterias = true;
						break;
					}
				}
				if (!existeCarreraConMaterias)
					MostrarMensaje.errorNoExistenCarrerasConMaterias();
			}
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
	
	public JComboBox<String> getCarrerasJCB() {
		if (carrerasJCB == null) {
			carrerasJCB = new JComboBox<String>();
			Map<String, Carrera> carreras = Controlador.getColeccionCarreras();
			for (Map.Entry<String, Carrera> c : carreras.entrySet())
				carrerasJCB.addItem(c.getValue().getNombre());
			
			ActionListener elegirCarrera = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					limpiarCampos();
					agregarMateriasCarrera();
					agregarAsignaturasCarrera();
			    }
			};
			carrerasJCB.addActionListener(elegirCarrera);
		}
		return carrerasJCB;
	}
	
	public JLabel getMateriasJL() {
		if (materiasJL == null) {
			materiasJL = new JLabel("Materias");
		}
		return materiasJL;
	}
	
	public JComboBox<String> getMateriasJCB() {
		if (materiasJCB == null) {
			materiasJCB = new JComboBox<String>();
		}
		return materiasJCB;
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
	
	public JLabel getTienePreviasJL() {
		if (tienePreviasJL == null) {
			tienePreviasJL = new JLabel("Tiene previas?");
		}
		return tienePreviasJL;
	}
	
	public ButtonGroup getTienePreviasBG() {
		if (tienePreviasBG == null) {
			tienePreviasBG = new ButtonGroup();
			tienePreviasBG.add(getTienePreviasJRB_SI());
			tienePreviasBG.add(getTienePreviasJRB_NO());
		}
		return tienePreviasBG;
	}
	
	public JPanel getTienePreviasJP() {
		if (tienePreviasJP == null) {
			tienePreviasJP = new JPanel();
			getTienePreviasBG();	//Agregar previas al button group
			tienePreviasJP.add(getTienePreviasJRB_SI());
			tienePreviasJP.add(getTienePreviasJRB_NO());
		}
		return tienePreviasJP;
	}
	
	public JRadioButton getTienePreviasJRB_SI() {
		if (tienePreviasJRB_SI == null) {
			tienePreviasJRB_SI = new JRadioButton("SI");
		}
		return tienePreviasJRB_SI;
	}
	
	public JRadioButton getTienePreviasJRB_NO() {
		if (tienePreviasJRB_NO == null) {
			tienePreviasJRB_NO = new JRadioButton("NO");
		}
		return tienePreviasJRB_NO;
	}
	
	public JLabel getAgregarPreviaJL() {
		if (agregarPreviaJL == null) {
			agregarPreviaJL = new JLabel("Agregar previa");
		}
		return agregarPreviaJL;
	}
	
	public JComboBox<String> getAsignaturasJCB(){
		if (asignaturasJCB == null) {
			asignaturasJCB = new JComboBox<String>();

			agregarAsignaturasCarrera();
			
			ActionListener elegirAsignatura = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (asignaturasJCB.getSelectedItem() != null)
						agregarPreviaAsignatura(asignaturasJCB.getSelectedItem().toString());
				}
			};
			asignaturasJCB.addActionListener(elegirAsignatura);
		}
		return asignaturasJCB;
	}
	
	public JLabel getPreviasAgregadasJL() {
		if (previasAgregadasJL == null) {
			previasAgregadasJL = new JLabel("Previas agregadas");
		}
		return previasAgregadasJL;
	}
	
	public ArrayList<JButton> getPreviasAL(){
		if (previasAL == null) {
			previasAL = new ArrayList<JButton>();
		}
		return previasAL;
	}

	public JPanel getPreviasJP() {
		if (previasJP == null) {
			previasJP = new JPanel();
		}
		return previasJP;
	}
	
	public JScrollPane getPreviasJSP() {
		if (previasJSP == null) {
			previasJSP = new JScrollPane(getPreviasJP());
			//previasJSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //SETTING SCHEME FOR HORIZONTAL BAR
			previasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return previasJSP;
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
					if (agregarAsignatura()) {
						ventanaPrincipal.removerComponentesPanelCentral();
						instancia = null;
					}
			    }
			};
			confirmarJB.addActionListener(confirmar);
		}
		return confirmarJB;
	}
	
	public boolean agregarAsignatura() {
		String nombreCarrera;
		String nombreMateria;
		String nombreAsignatura;
		int cantCreditos;
		boolean tienePrevias;
		Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
		
		nombreCarrera = getCarrerasJCB().getSelectedItem().toString();
		nombreMateria = getMateriasJCB().getSelectedItem().toString();
		nombreAsignatura = getNombreJTF().getText();
		tienePrevias = getTienePreviasJRB_SI().isSelected();
		try {
			cantCreditos = Integer.parseInt(getCantCreditosJTF().getText());
			
			if (MetodosAux.validarNombre(nombreCarrera) &&
				MetodosAux.validarNombre(nombreMateria) &&
				MetodosAux.validarNombre(nombreAsignatura) &&
				cantCreditos >= Controlador.CREDITOS_MIN_ASIGNATURA &&
				((tienePrevias && getPreviasAL().size() > 0) || (!tienePrevias))) {
						
				ManejadorCarrera mc = ManejadorCarrera.getInstancia();
				
				boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
				if (existeCarrera) {
					
					boolean existeMateria = mc.getCarreras().get(nombreCarrera).getMaterias().containsKey(nombreMateria);
					if (existeMateria) {
						
						Map<String, Asignatura> asignaturas = mc.getCarreras().get(nombreCarrera).getMaterias().get(nombreMateria).getAsignaturas();
						boolean existeAsignatura = asignaturas.containsKey(nombreAsignatura);
						if (!existeAsignatura) {
							
							for (Map.Entry<String, Asignatura> a : asignaturas.entrySet()) {
								for (JButton previaButton : getPreviasAL()) {
									String previa = previaButton.getText();
									if (a.getValue().getNombre().equals(previa))
										previas.put(a.getKey(), a.getValue());
								}
							}
							
							if (!tienePrevias)
								previas = new HashMap<String, Asignatura>();
							Controlador.agregarAsignatura(nombreAsignatura, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previas);
							MostrarMensaje.asignaturaAgregada();
							return true;
						}
					}
					else
						MostrarMensaje.errorImposible();
				}
				else
					MostrarMensaje.errorImposible();
			}
			else {
				if (!MetodosAux.validarNombre(nombreCarrera))
					MostrarMensaje.errorNombre();
				if (!MetodosAux.validarNombre(nombreMateria))
					MostrarMensaje.errorNombre();
				if (!MetodosAux.validarNombre(nombreAsignatura))
					MostrarMensaje.errorNombre();
				if (cantCreditos < Controlador.CREDITOS_MIN_ASIGNATURA)
					MostrarMensaje.errorCreditosMinMateria();
				if (tienePrevias && getPreviasAL().size() == 0)
					MostrarMensaje.errorPreviasSinAgregar();
			}
		}
		catch(NumberFormatException e) {
			MostrarMensaje.errorFormatoCreditos();
		}
		return false;
	}
	
	public void agregarMateriasCarrera() {
		Map<String, Materia> materias = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias();
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			materiasJCB.addItem(m.getValue().getNombre());
		}
	}
	
	public void agregarPreviaAsignatura(String previa) {
		boolean existeAsignatura = false;
		for (JButton asignatura : getPreviasAL()) {
			if (asignatura.getText().equals(previa)) {
				existeAsignatura = true;
				break;
			}
		}
		if (!existeAsignatura) {
			getPreviasAL().add(new JButton(previa));
			actualizarPreviasJP();
		}
		else
			MostrarMensaje.errorAsignaturaAgregada();
	}
	
	public void agregarAsignaturasCarrera() {
		Carrera c = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString());
		
		for (Map.Entry<String, Materia> m : c.getMaterias().entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet())
				asignaturasJCB.addItem(a.getValue().getNombre());
		}
	}
	
	public void actualizarPreviasJP() {
		getPreviasJP().removeAll();
		for (JButton b : getPreviasAL()) {
			previasJP.add(b);
		}
		getPreviasJP().revalidate();
		getPreviasJP().repaint();
			
	}
	
	public void limpiarCampos() {
		getMateriasJCB().removeAllItems();
		getNombreJTF().setText("");
		getCantCreditosJTF().setText("");
		getTienePreviasBG().clearSelection();
		getAsignaturasJCB().removeAllItems();
		getPreviasAL().clear();
		getPreviasJP().removeAll();
	}
}
