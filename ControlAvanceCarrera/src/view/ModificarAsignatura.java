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
	private JLabel agregarPreviaCursoJL;
	private JComboBox<String> asignaturasCursoJCB;
	private JLabel previasCursoAgregadasJL;
	private ArrayList<JButton> previasCursoAL;
	private JPanel previasCursoJP;
	private JScrollPane previasCursoJSP;
	
	private JLabel agregarPreviaTodoJL;
	private JComboBox<String> asignaturasTodoJCB;
	private JLabel previasTodoAgregadasJL;
	private ArrayList<JButton> previasTodoAL;
	private JPanel previasTodoJP;
	private JScrollPane previasTodoJSP;
	private JButton cancelarJB;
	private JButton confirmarJB;
	
	private ModificarAsignatura() {
		this.setLayout(new GridLayout(11, 2));
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
		this.add(getAgregarPreviaCursoJL());
		this.add(getAsignaturasCursoJCB());
		this.add(getPreviasCursoAgregadasJL());
		this.add(getPreviasCursoJSP());
		this.add(getAgregarPreviaTodoJL());
		this.add(getAsignaturasTodoJCB());
		this.add(getPreviasTodoAgregadasJL());
		this.add(getPreviasTodoJSP());
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
						getAsignaturasCursoJCB().setEnabled(true);
						for (JButton b : getPreviasCursoAL())
							b.setEnabled(true);
						getAsignaturasTodoJCB().setEnabled(true);
						for (JButton b : getPreviasTodoAL())
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
						getAsignaturasCursoJCB().setEnabled(false);
						for (JButton b : getPreviasCursoAL())
							b.setEnabled(false);
						getAsignaturasTodoJCB().setEnabled(false);
						for (JButton b : getPreviasTodoAL())
							b.setEnabled(false);
					}
			    }
			};
			tienePreviasJRB_NO.addChangeListener(cambiarEstado);
		}
		return tienePreviasJRB_NO;
	}
	
	public JLabel getAgregarPreviaCursoJL() {
		if (agregarPreviaCursoJL == null) {
			agregarPreviaCursoJL = new JLabel("Agregar previa (CURSO)");
			agregarPreviaCursoJL.setFont(Fuente.label());
		}
		return agregarPreviaCursoJL;
	}
	
	public JComboBox<String> getAsignaturasCursoJCB(){
		if (asignaturasCursoJCB == null) {
			asignaturasCursoJCB = new JComboBox<String>();
			asignaturasCursoJCB.setFont(Fuente.comboBox());
			
			ActionListener elegirAsignatura = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (asignaturasCursoJCB.getSelectedItem() != null && 
						!asignaturasCursoJCB.getSelectedItem().toString().equals("Elegir asignatura"))
						
						agregarPreviaCursoAsignatura(asignaturasCursoJCB.getSelectedItem().toString());
						//Eliminar texto por defecto del JComboBox
						for (int i = 0; i < asignaturasCursoJCB.getItemCount(); i++) {
							if (asignaturasCursoJCB.getItemAt(i).equals("Elegir asignatura")) {
								
								asignaturasCursoJCB.removeItemAt(i);
								break;
							}
						}
						///
				}
			};
			asignaturasCursoJCB.addActionListener(elegirAsignatura);
			
			agregarAsignaturasCarrera(asignaturasCursoJCB);
			asignaturasCursoJCB.setEnabled(false);
		}
		return asignaturasCursoJCB;
	}
	
	public JLabel getPreviasCursoAgregadasJL() {
		if (previasCursoAgregadasJL == null) {
			previasCursoAgregadasJL = new JLabel("Previas agregadas (CURSO)");
			previasCursoAgregadasJL.setFont(Fuente.label());
		}
		return previasCursoAgregadasJL;
	}
	
	public ArrayList<JButton> getPreviasCursoAL(){
		if (previasCursoAL == null) {
			previasCursoAL = new ArrayList<JButton>();
		}
		return previasCursoAL;
	}

	public JPanel getPreviasCursoJP() {
		if (previasCursoJP == null) {
			previasCursoJP = new JPanel();
		}
		return previasCursoJP;
	}
	
	public JScrollPane getPreviasCursoJSP() {
		if (previasCursoJSP == null) {
			previasCursoJSP = new JScrollPane(getPreviasCursoJP());
			//previasJSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //SETTING SCHEME FOR HORIZONTAL BAR
			previasCursoJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return previasCursoJSP;
	}
	
	public JLabel getAgregarPreviaTodoJL() {
		if (agregarPreviaTodoJL == null) {
			agregarPreviaTodoJL = new JLabel("Agregar previa (CURSO y EXÁMEN)");
			agregarPreviaTodoJL.setFont(Fuente.label());
		}
		return agregarPreviaTodoJL;
	}
	
	public JComboBox<String> getAsignaturasTodoJCB(){
		if (asignaturasTodoJCB == null) {
			asignaturasTodoJCB = new JComboBox<String>();
			asignaturasTodoJCB.setFont(Fuente.comboBox());
			
			ActionListener elegirAsignatura = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					if (asignaturasTodoJCB.getSelectedItem() != null && 
						!asignaturasTodoJCB.getSelectedItem().toString().equals("Elegir asignatura"))
						
						agregarPreviaTodoAsignatura(asignaturasTodoJCB.getSelectedItem().toString());
						//Eliminar texto por defecto del JComboBox
						for (int i = 0; i < asignaturasTodoJCB.getItemCount(); i++) {
							if (asignaturasTodoJCB.getItemAt(i).equals("Elegir asignatura")) {
								
								asignaturasTodoJCB.removeItemAt(i);
								break;
							}
						}
						///
				}
			};
			asignaturasTodoJCB.addActionListener(elegirAsignatura);
			
			agregarAsignaturasCarrera(asignaturasTodoJCB);
			asignaturasTodoJCB.setEnabled(false);
		}
		return asignaturasTodoJCB;
	}
	
	public JLabel getPreviasTodoAgregadasJL() {
		if (previasTodoAgregadasJL == null) {
			previasTodoAgregadasJL = new JLabel("Previas agregadas (CURSO y EXÁMEN)");
			previasTodoAgregadasJL.setFont(Fuente.label());
		}
		return previasTodoAgregadasJL;
	}
	
	public ArrayList<JButton> getPreviasTodoAL(){
		if (previasTodoAL == null) {
			previasTodoAL = new ArrayList<JButton>();
		}
		return previasTodoAL;
	}

	public JPanel getPreviasTodoJP() {
		if (previasTodoJP == null) {
			previasTodoJP = new JPanel();
		}
		return previasTodoJP;
	}
	
	public JScrollPane getPreviasTodoJSP() {
		if (previasTodoJSP == null) {
			previasTodoJSP = new JScrollPane(getPreviasTodoJP());
			//previasJSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //SETTING SCHEME FOR HORIZONTAL BAR
			previasTodoJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return previasTodoJSP;
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
		Map<String, Asignatura> previasCurso = new HashMap<String, Asignatura>();
		Map<String, Asignatura> previasTodo = new HashMap<String, Asignatura>();
		
		try {
			cantCreditos = Integer.parseInt(getCantCreditosJTF().getText());
			
			if (MetodosAux.validarNombre(nombreCarrera) &&
				MetodosAux.validarNombre(nombreMateria) &&
				MetodosAux.validarNombre(nombreAsignaturaDespues) &&
				cantCreditos >= Controlador.CREDITOS_MIN_ASIGNATURA &&
				((tienePrevias && (getPreviasCursoAL().size() > 0 || getPreviasTodoAL().size() > 0)) || (!tienePrevias))) {
				
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
									boolean esPreviaCurso = false;
									for (JButton previaButton : getPreviasCursoAL()) {
										String previa = previaButton.getText();
										if (a.getValue().getNombre().equals(previa)) {
											previasCurso.put(a.getKey(), a.getValue());
											esPreviaCurso = true;
											break;
										}
									}
									if (!esPreviaCurso) {
										//PREVIAS TODO
										for (JButton previaButton : getPreviasTodoAL()) {
											String previa = previaButton.getText();
											if (a.getValue().getNombre().equals(previa)) {
												previasTodo.put(a.getKey(), a.getValue());
												break;
											}
										}
										//
									}
								}
							}
							else {
								previasCurso = new HashMap<String, Asignatura>();
								previasTodo = new HashMap<String, Asignatura>();
							}
							Controlador.modificarAsignatura(nombreAsignaturaDespues, nombreAsignaturaAntes, nombreCarrera, nombreMateria, cantCreditos, tienePrevias, previasCurso, previasTodo);
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
				if (tienePrevias && (getPreviasCursoAL().size() == 0 || getPreviasTodoAL().size() == 0))
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
		limpiarAsignaturasCursoAgregadas();
		limpiarAsignaturasTodoJCB();
		limpiarAsignaturasTodoAgregadas();
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
		getAsignaturasCursoJCB().removeAllItems();
	}
	
	public void limpiarAsignaturasTodoJCB() {
		getAsignaturasCursoJCB().removeAllItems();
	}
	
	public void limpiarAsignaturasCursoAgregadas() {
		getPreviasCursoAL().clear();
		actualizarPreviasCursoJP();
	}
	
	public void limpiarAsignaturasTodoAgregadas() {
		getPreviasTodoAL().clear();
		actualizarPreviasTodoJP();
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
	
	public void agregarPreviaCursoAsignatura(String previa) {
		boolean existeAsignaturaCurso = verifPreviaAgregada(previa, getPreviasCursoAL());
		boolean existeAsignaturaTodo = verifPreviaAgregada(previa, getPreviasTodoAL());
		if (!existeAsignaturaCurso && !existeAsignaturaTodo) {
			Boolean esPreviaCurso = true;
			getPreviasCursoAL().add(crearBotonPrevia(previa, esPreviaCurso));
			actualizarPreviasCursoJP();
		}
		else {
			if (existeAsignaturaCurso)
				MostrarMensaje.errorAsignaturaAgregadaCurso();
			else if (existeAsignaturaTodo)
				MostrarMensaje.errorAsignaturaAgregadaTodo();
			else
				MostrarMensaje.errorImposible();
		}
	}
	
	public boolean verifPreviaAgregada(String previa, ArrayList<JButton> previasAL) {
		for (JButton asignatura : previasAL) {
			if (asignatura.getText().equals(previa)) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarPreviaTodoAsignatura(String previa) {
		boolean existeAsignaturaCurso = verifPreviaAgregada(previa, getPreviasCursoAL());
		boolean existeAsignaturaTodo = verifPreviaAgregada(previa, getPreviasTodoAL());
		if (!existeAsignaturaCurso && !existeAsignaturaTodo) {
			Boolean esPreviaCurso = false;
			getPreviasTodoAL().add(crearBotonPrevia(previa, esPreviaCurso));
			actualizarPreviasTodoJP();
		}
		else {
			if (existeAsignaturaCurso)
				MostrarMensaje.errorAsignaturaAgregadaCurso();
			else if (existeAsignaturaTodo)
				MostrarMensaje.errorAsignaturaAgregadaTodo();
			else
				MostrarMensaje.errorImposible();
		}
			
	}
	
	public JButton crearBotonPrevia(String nombre, Boolean esPreviaCurso) {
		
		JButton previaJB = new MiBoton(nombre, Colores.COLOR_BG_PREVIA, Colores.COLOR_BG_PREVIA_OVER, Colores.COLOR_BG_PREVIA_PRESIONADO);
		previaJB.setFont(Fuente.buttonPrevia());
		previaJB.setBackground(Colores.COLOR_BG_PREVIA);
		previaJB.setForeground(Colores.COLOR_FG_PREVIA);
		
		
		previaJB.addActionListener(crearActionListenerPrevia(nombre, esPreviaCurso));
		
		return previaJB;
	}
	
	public ActionListener crearActionListenerPrevia(String nombre, Boolean esPreviaCurso) {
		ActionListener metodoBoton = null;
		if (esPreviaCurso) {
			metodoBoton = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					removerPreviaCurso(nombre);
			    }
			};
		}
		else {
			metodoBoton = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					removerPreviaTodo(nombre);
			    }
			};
		}
		
		return metodoBoton;
	}
	
	public void removerPreviaCurso(String nombre) {
		boolean existeAsignatura = false;
		int pos = -1;
		for (JButton asignatura : getPreviasCursoAL()) {
			pos++;
			if (asignatura.getText().equals(nombre)) {
				existeAsignatura = true;
				break;
			}
		}
		if (existeAsignatura) {
			getPreviasCursoAL().remove(pos);
			actualizarPreviasCursoJP();
		}
		else
			MostrarMensaje.errorImposible();
	}
	
	public void removerPreviaTodo(String nombre) {
		boolean existeAsignatura = false;
		int pos = -1;
		for (JButton asignatura : getPreviasTodoAL()) {
			pos++;
			if (asignatura.getText().equals(nombre)) {
				existeAsignatura = true;
				break;
			}
		}
		if (existeAsignatura) {
			getPreviasTodoAL().remove(pos);
			actualizarPreviasTodoJP();
		}
		else
			MostrarMensaje.errorImposible();
	}
	
	public void agregarAsignaturasCarrera(JComboBox<String> asignaturas) {
		Carrera c = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString());
		
		asignaturas.setSelectedIndex(-1);
		
		ActionListener elegirAsignatura = asignaturas.getActionListeners()[0];
		asignaturas.removeActionListener(elegirAsignatura);
		
		for (Map.Entry<String, Materia> m : c.getMaterias().entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet())
				asignaturas.addItem(a.getValue().getNombre());
		}
		asignaturas.addActionListener(elegirAsignatura);
	}
	
	public void actualizarPreviasCursoJP() {
		getPreviasCursoJP().removeAll();
		for (JButton b : getPreviasCursoAL()) {
			previasCursoJP.add(b);
		}
		getPreviasCursoJP().revalidate();
		getPreviasCursoJP().repaint();
			
	}
	
	public void actualizarPreviasTodoJP() {
		getPreviasTodoJP().removeAll();
		for (JButton b : getPreviasTodoAL()) {
			previasTodoJP.add(b);
		}
		getPreviasTodoJP().revalidate();
		getPreviasTodoJP().repaint();
			
	}
	
	public void verifEstadoTienePrevias() {
		boolean estado = false;
		if (tienePreviasJRB_NO.isSelected())
			estado = false;
		else
			estado = true;
		
		for (JButton b : getPreviasCursoAL())
			b.setEnabled(estado);
		for (JButton b : getPreviasTodoAL())
			b.setEnabled(estado);
		
		actualizarPreviasCursoJP();
		actualizarPreviasTodoJP();
	}

	public void agregarPreviasCursoAsignatura() {
		Asignatura asignatura = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias().get(getMateriasJCB().getSelectedItem().toString()).getAsignaturas().get(getAsignaturasJCB().getSelectedItem().toString());
		Map<String, Asignatura> previas = asignatura.getPreviasCurso();
		
		limpiarAsignaturasCursoAgregadas();
		for (Map.Entry<String, Asignatura> a : previas.entrySet())
			agregarPreviaCursoAsignatura(a.getValue().getNombre().toString());
		actualizarPreviasCursoJP();
	}
	
	public void agregarPreviasTodoAsignatura() {
		Asignatura asignatura = Controlador.getColeccionCarreras().get(getCarrerasJCB().getSelectedItem().toString()).getMaterias().get(getMateriasJCB().getSelectedItem().toString()).getAsignaturas().get(getAsignaturasJCB().getSelectedItem().toString());
		Map<String, Asignatura> previas = asignatura.getPreviasTodo();
		
		limpiarAsignaturasTodoAgregadas();
		for (Map.Entry<String, Asignatura> a : previas.entrySet())
			agregarPreviaTodoAsignatura(a.getValue().getNombre().toString());
		actualizarPreviasTodoJP();
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
								limpiarAsignaturasTodoJCB();
								agregarAsignaturasCarrera(getAsignaturasCursoJCB());
								agregarAsignaturasCarrera(getAsignaturasTodoJCB());
								limpiarAsignaturasCursoAgregadas();
								limpiarAsignaturasTodoAgregadas();
								agregarPreviasCursoAsignatura();
								agregarPreviasTodoAsignatura();
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