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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controlador;
import model.Asignatura;
import model.Carrera;
import model.Fuente;
import model.ManejadorCarrera;
import model.Materia;
import model.MetodosAux;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class ModificarAsignatura extends JPanel{
	
	private static ModificarAsignatura instancia;
	private Inicio ventanaPrincipal;
	private JLabel carrerasJL;
	private JComboBox<String> carrerasJCB;
	private JLabel materiasJL;
	private JComboBox<String> materiasJCB;
	private JLabel asignaturasJL;
	private JComboBox<String> asignaturasJCB;
	private JLabel asignaturaJL;
	private JTextField asignaturaJTF;
	private JLabel cantCreditosJL;
	private JTextField cantCreditosJTF;
	private JLabel tienePreviasJL;
	private ButtonGroup tienePreviasBG;
	private JPanel tienePreviasJP;
	private JRadioButton tienePreviasJRB_SI;
	private JRadioButton tienePreviasJRB_NO;
	private JLabel agregarPreviaJL;
	private JComboBox<String> asignaturasPreviasJCB;
	private JLabel previasAgregadasJL;
	private ArrayList<JButton> previasAL;
	private JPanel previasJP;
	private JScrollPane previasJSP;
	private JButton cancelarJB;
	private JButton confirmarJB;
	
	private ModificarAsignatura() {
		this.setLayout(new GridLayout(9, 2));
		this.add(getCarrerasJL());
		this.add(getCarrerasJCB());
		this.add(getMateriasJL());
		this.add(getMateriasJCB());
		this.add(getAsignaturasJL());
		this.add(getAsignaturasJCB());
		this.add(getAsignaturaJL());
		this.add(getAsignaturaJTF());
		this.add(getCantCreditosJL());
		this.add(getCantCreditosJTF());
		this.add(getTienePreviasJL());
		this.add(getTienePreviasJP());
		this.add(getAgregarPreviaJL());
		this.add(getAsignaturasPreviasJCB());
		this.add(getPreviasAgregadasJL());
		this.add(getPreviasJSP());
		this.add(getCancelarJB());
		this.add(getConfirmarJB());
		
		autocompletarValores();
	}
	
	public static ModificarAsignatura getInstancia() {
		if (instancia == null) {
			instancia = new ModificarAsignatura();
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
						autocompletarValores();
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
						getAsignaturasJCB();
						limpiarAsignaturas();
						agregarAsignaturas();
						autocompletarValores();
					}
				}
			};
			materiasJCB.addActionListener(elegirMateria);
		}
		return materiasJCB;
	}
	
	public JLabel getAsignaturasJL() {
		if (asignaturasJL == null) {
			asignaturasJL = new JLabel("Asignaturas");
			asignaturasJL.setFont(Fuente.label());
		}
		return asignaturasJL;
	}
	
	public JComboBox<String> getAsignaturasJCB() {
		if (asignaturasJCB == null) {
			asignaturasJCB = new JComboBox<String>();
			asignaturasJCB.setFont(Fuente.comboBox());
			
			ActionListener elegirAsignatura = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (asignaturasJCB.getSelectedItem() != null) {
						autocompletarValores();
					}
				}
			};
			asignaturasJCB.addActionListener(elegirAsignatura);
		}
		return asignaturasJCB;
	}
	
	public JLabel getAsignaturaJL() {
		if (asignaturaJL == null) {
			asignaturaJL = new JLabel("Nombre asignatura");
			asignaturaJL.setFont(Fuente.label());
		}
		return asignaturaJL;
	}
	
	public JTextField getAsignaturaJTF() {
		if (asignaturaJTF == null) {
			asignaturaJTF = new JTextField();
			asignaturaJTF.setFont(Fuente.textField());
		}
		return asignaturaJTF;
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
	
	public JLabel getTienePreviasJL() {
		if (tienePreviasJL == null) {
			tienePreviasJL = new JLabel("Tiene previas?");
			tienePreviasJL.setFont(Fuente.label());
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
			tienePreviasJRB_SI.setFont(Fuente.radioButton());
			
			ChangeListener cambiarEstado = new ChangeListener() {
				public void stateChanged (ChangeEvent e) {
					if (tienePreviasJRB_SI.isSelected()) {
						getAsignaturasPreviasJCB().setEnabled(true);
						for (JButton b : getPreviasAL())
							b.setEnabled(true);
					}
			    }
			};
			tienePreviasJRB_SI.addChangeListener(cambiarEstado);
		}
		return tienePreviasJRB_SI;
	}
	
	public JRadioButton getTienePreviasJRB_NO() {
		if (tienePreviasJRB_NO == null) {
			tienePreviasJRB_NO = new JRadioButton("NO");
			tienePreviasJRB_NO.setFont(Fuente.radioButton());
			
			tienePreviasJRB_NO.setSelected(true);
			
			ChangeListener cambiarEstado = new ChangeListener() {
				public void stateChanged (ChangeEvent e) {
					if (tienePreviasJRB_NO.isSelected()) {
						getAsignaturasPreviasJCB().setEnabled(false);
						for (JButton b : getPreviasAL())
							b.setEnabled(false);
					}
			    }
			};
			tienePreviasJRB_NO.addChangeListener(cambiarEstado);
		}
		return tienePreviasJRB_NO;
	}
	
	public JLabel getAgregarPreviaJL() {
		if (agregarPreviaJL == null) {
			agregarPreviaJL = new JLabel("Agregar previa");
			agregarPreviaJL.setFont(Fuente.label());
		}
		return agregarPreviaJL;
	}
	
	public JComboBox<String> getAsignaturasPreviasJCB(){
		if (asignaturasPreviasJCB == null) {
			asignaturasPreviasJCB = new JComboBox<String>();
			asignaturasPreviasJCB.setFont(Fuente.comboBox());
			
			ActionListener elegirAsignatura = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (asignaturasPreviasJCB.getSelectedItem() != null && 
						!asignaturasPreviasJCB.getSelectedItem().toString().equals("Elegir asignatura"))
						
						agregarPreviaAsignatura(asignaturasPreviasJCB.getSelectedItem().toString());
						//Eliminar texto por defecto del JComboBox
						for (int i = 0; i < asignaturasPreviasJCB.getItemCount(); i++) {
							if (asignaturasPreviasJCB.getItemAt(i).equals("Elegir asignatura")) {
								
								asignaturasPreviasJCB.removeItemAt(i);
								break;
							}
						}
						///
				}
			};
			asignaturasPreviasJCB.addActionListener(elegirAsignatura);
			
			agregarAsignaturasCarrera();
			asignaturasPreviasJCB.setEnabled(false);
		}
		return asignaturasPreviasJCB;
	}
	
	public JLabel getPreviasAgregadasJL() {
		if (previasAgregadasJL == null) {
			previasAgregadasJL = new JLabel("Previas agregadas");
			previasAgregadasJL.setFont(Fuente.label());
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
			previasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return previasJSP;
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
						getMateriasJCB().getSelectedItem() != null &&
						getAsignaturasJCB().getSelectedItem() != null) {
						
						String nombreCarrera = getCarrerasJCB().getSelectedItem().toString();
						String nombreMateria = getMateriasJCB().getSelectedItem().toString();
						String nombreAsignaturaAntes = getAsignaturasJCB().getSelectedItem().toString();
						if (modificarAsignatura(nombreCarrera, nombreMateria, nombreAsignaturaAntes)) {
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
	
	public boolean modificarAsignatura(String nombreCarrera, String nombreMateria, String nombreAsignaturaAntes) {
		
		String nombreAsignaturaDespues = getAsignaturaJTF().getText();
		int cantCreditos = -1;
		boolean tienePrevias = this.getTienePreviasJRB_SI().isSelected();
		Map<String, Asignatura> previas = new HashMap<String, Asignatura>();
		
		try {
			cantCreditos = Integer.parseInt(getCantCreditosJTF().getText());
			
			if (MetodosAux.validarNombre(nombreCarrera) &&
				MetodosAux.validarNombre(nombreMateria) &&
				MetodosAux.validarNombre(nombreAsignaturaDespues) &&
				cantCreditos >= Controlador.CREDITOS_MIN_ASIGNATURA &&
				((tienePrevias && getPreviasAL().size() > 0) || (!tienePrevias))) {
				
				ManejadorCarrera mc = ManejadorCarrera.getInstancia();
				
				boolean existeCarrera = mc.getCarreras().containsKey(nombreCarrera);
				if (existeCarrera) {
					
					boolean existeMateria = mc.getCarreras().get(nombreCarrera).getMaterias().containsKey(nombreMateria);
					if (existeMateria) {
						
						Map<String, Asignatura> asignaturasCarrera = obtenerAsignaturasCarrera(nombreCarrera);
						boolean existeAsignatura = asignaturasCarrera.containsKey(nombreAsignaturaAntes);
						if (existeAsignatura) {
							
							if (tienePrevias) {
								
								for (Map.Entry<String, Asignatura> a : asignaturasCarrera.entrySet()) {
									for (JButton previaButton : getPreviasAL()) {
										String previa = previaButton.getText();
										if (a.getValue().getNombre().equals(previa)) {
											previas.put(a.getKey(), a.getValue());
											break;
										}
									}
								}
							}
							else
								previas = new HashMap<String, Asignatura>();
							
							Controlador.modificarAsignatura(nombreAsignaturaDespues, nombreAsignaturaAntes, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previas);
							MostrarMensaje.asignaturaModificada();
							
							return true;
						}
						else
							MostrarMensaje.errorImposible();
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
				if (!MetodosAux.validarNombre(nombreAsignaturaDespues))
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
	
	public void limpiarCampos() {
		limpiarMaterias();
		limpiarAsignaturas();
		limpiarAsignatura();
		limpiarCantCreditos();
		limpiarTienePrevias();
		limpiarAsignaturasPreviasJCB();
		limpiarAsignaturasPreviasAgregadas();
	}
		
	public void limpiarMaterias() {
		getMateriasJCB().removeAllItems();
	}
	
	public void limpiarAsignaturas() {
		getAsignaturasJCB().removeAllItems();
	}
	
	public void limpiarAsignatura() {
		getAsignaturaJTF().setText("");
	}
	
	public void limpiarCantCreditos() {
		getCantCreditosJTF().setText("");
	}
	
	public void limpiarTienePrevias() {
		getTienePreviasJRB_NO().setSelected(true);
	}
	
	public void limpiarAsignaturasPreviasJCB() {
		getAsignaturasPreviasJCB().removeAllItems();
	}
	
	public void limpiarAsignaturasPreviasAgregadas() {
		getPreviasAL().clear();
		actualizarPreviasJP();
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
	
	public void agregarMateriasCarrera() {
		Map<String, Materia> materias = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias();
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			getMateriasJCB().addItem(m.getValue().getNombre());
		}
	}
	
	public void agregarPreviasAsignatura() {
		Asignatura asignatura = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias().get(getMateriasJCB().getSelectedItem().toString()).getAsignaturas().get(getAsignaturasJCB().getSelectedItem().toString());
		Map<String, Asignatura> previas = asignatura.getPrevias();
		
		limpiarAsignaturasPreviasAgregadas();
		for (Map.Entry<String, Asignatura> a : previas.entrySet())
			agregarPreviaAsignatura(a.getValue().getNombre().toString());
		actualizarPreviasJP();
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
			getPreviasAL().add(crearBotonPrevia(previa));
			actualizarPreviasJP();
		}
		else
			MostrarMensaje.errorAsignaturaAgregada();
	}
	
	public JButton crearBotonPrevia(String nombre) {
		
		JButton previaJB = new MiBoton(nombre, Colores.COLOR_BG_PREVIA, Colores.COLOR_BG_PREVIA_OVER, Colores.COLOR_BG_PREVIA_PRESIONADO);
		previaJB.setFont(Fuente.buttonPrevia());
		
		ActionListener metodoBoton = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				removerPrevia(nombre);
		    }
		};
		previaJB.addActionListener(metodoBoton);
		
		return previaJB;
	}
	
	public void removerPrevia(String nombre) {
		boolean existeAsignatura = false;
		int pos = -1;
		for (JButton asignatura : getPreviasAL()) {
			pos++;
			if (asignatura.getText().equals(nombre)) {
				existeAsignatura = true;
				break;
			}
		}
		if (existeAsignatura) {
			getPreviasAL().remove(pos);
			actualizarPreviasJP();
		}
		else
			MostrarMensaje.errorImposible();
	}
	
	public void agregarAsignaturasCarrera() {
		Carrera c = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString());
		
		getAsignaturasPreviasJCB().setSelectedIndex(-1);
		
		ActionListener elegirAsignatura = getAsignaturasPreviasJCB().getActionListeners()[0];
		getAsignaturasPreviasJCB().removeActionListener(elegirAsignatura);
		
		for (Map.Entry<String, Materia> m : c.getMaterias().entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet())
				getAsignaturasPreviasJCB().addItem(a.getValue().getNombre());
		}
		getAsignaturasPreviasJCB().addActionListener(elegirAsignatura);
	}
	
	public void actualizarPreviasJP() {
		getPreviasJP().removeAll();
		for (JButton b : getPreviasAL()) {
			previasJP.add(b);
		}
		getPreviasJP().revalidate();
		getPreviasJP().repaint();
			
	}
	
	public void verifEstadoTienePrevias() {
		boolean estado = false;
		if (tienePreviasJRB_NO.isSelected())
			estado = false;
		else
			estado = true;
		
		for (JButton b : getPreviasAL())
			b.setEnabled(estado);
		
		actualizarPreviasJP();
	}
	
	public void autocompletarValores() {
		
		if (getCarrerasJCB().getSelectedItem() != null) {
			if (Controlador.getColeccionCarreras().containsKey(getCarrerasJCB().getSelectedItem().toString())){
				
				Carrera c = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString());
				if (getMateriasJCB().getSelectedItem() != null) {
					if (c.getMaterias().containsKey(getMateriasJCB().getSelectedItem().toString())) {
						
						Materia m = c.getMaterias().get(getMateriasJCB().getSelectedItem().toString());
						if (getAsignaturasJCB().getSelectedItem() != null) {
							if (m.getAsignaturas().containsKey(getAsignaturasJCB().getSelectedItem().toString())) {
								
								Asignatura a = m.getAsignaturas().get(getAsignaturasJCB().getSelectedItem().toString());
								
								asignaturaJTF.setText(a.getNombre());
								cantCreditosJTF.setText(Integer.toString(a.getCantCreditos()));
								
								limpiarAsignaturasPreviasJCB();
								agregarAsignaturasCarrera();
								limpiarAsignaturasPreviasAgregadas();
								agregarPreviasAsignatura();
								verifEstadoTienePrevias();
							}
						}
					}
				}
			}
		}
	}
	
	public Map<String, Asignatura> obtenerAsignaturasCarrera(String nombreCarrera){
		
		ManejadorCarrera mc = ManejadorCarrera.getInstancia();
		
		Map<String, Asignatura> asignaturasCarrera = new HashMap<String, Asignatura>();
		Map<String, Materia> materiasCarrera = mc.getCarreras().get(nombreCarrera).getMaterias();
		for (Map.Entry<String, Materia> m : materiasCarrera.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()){
				asignaturasCarrera.put(a.getKey(), a.getValue());
			}
		}
		
		return asignaturasCarrera;
	}
}
