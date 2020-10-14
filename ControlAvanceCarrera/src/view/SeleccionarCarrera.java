package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Asignatura;
import model.Fuente;
import model.ManejadorCarrera;
import model.Materia;
import model.MensajeCalculadora;
@SuppressWarnings("serial")
public class SeleccionarCarrera extends JPanel{
	
	private static SeleccionarCarrera instancia;
	private Inicio ventanaPrincipal;
	private static String nombreCarrera;
	private JPanel panelSupIzqJP;
	private JPanel panelSupDerJP;
	private JPanel panelInfIzqJP;
	private JPanel panelInfDerJP;
	private JLabel asignaturasJL;
	private JScrollPane asignaturasJSP;
	private JPanel asignaturasJP;
	
	private JPanel panelesEsqSupIzq;
	//private JScrollPane estadosJSP;
	private JPanel estadosJP;
	private ArrayList<JLabel> asignaturasUnselectedJL;
	private ArrayList<ButtonGroup> estadosAsignaturasALBG;
	private ArrayList<JRadioButton[]> estadosAsignaturasALJRB;
	
	private JLabel asignaturasSelectedJL;
	private JScrollPane asignaturasSelectedJSP;
	private JTable asignaturasSelectedJT;
	private JLabel materiasJL;
	private JScrollPane materiasJSP;
	private JTable materiasJT;
	private JPanel mensajesJP;
	private JLabel creditosCompletarCarreraJL;
	private JLabel creditosObtenidosVsNecesariosJL;
	private JLabel totalJL;
	private ArrayList<JLabel> mensajesAprobadoSiNoJL;
	private JButton salirJB;
	
	private static Map<String, Asignatura> asignaturasCarrera;
	
	private static final Color COLOR_FONDO = new Color(240,240,240);
	private static final Color COLOR_TITULO = new Color(0,0,0);
	private static final Color COLOR_CREDITOS_INSUFICIENTES = new Color(255,67,67);
	private static final Color COLOR_CREDITOS_SUFICIENTES = new Color(39, 143, 66);
	
	private static final String ESTADO_SIN_APROBAR = "Sin Aprobar";
	private static final String ESTADO_CURSO_APROBADO = "Curso";
	private static final String ESTADO_CURSO_Y_EXAMEN_APROBADO = "Curso y exámen";
	
	private SeleccionarCarrera(String nombreCarrera) {
		SeleccionarCarrera.nombreCarrera = nombreCarrera;
		SeleccionarCarrera.asignaturasCarrera = SeleccionarCarrera.obtenerAsignaturasCarrera();
		this.setLayout(new GridLayout(2, 2));
		this.add(getPanelSupIzqJP());
		this.add(getPanelSupDerJP());
		this.add(getPanelInfIzqJP());
		this.add(getPanelInfDerJP());
	}
	
	public static SeleccionarCarrera getInstancia(String nombreCarrera) {
		if (instancia == null) {	
			instancia = new SeleccionarCarrera(nombreCarrera);
		}
		return instancia;
	}
	
	public static void setInstanciaNull() {
		instancia = null;
	}
	
	public void setVentanaPrincipal(Inicio ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public JPanel getPanelSupIzqJP() {
		if (panelSupIzqJP == null) {
			panelSupIzqJP = new JPanel(new GridBagLayout());
			panelSupIzqJP.setBackground(COLOR_FONDO);
			GridBagConstraints gbc1 = new GridBagConstraints();
			GridBagConstraints gbc2 = new GridBagConstraints();
			//GridBagConstraints gbc3 = new GridBagConstraints();
			
			gbc1.gridx = 0;
			gbc1.gridy = 0;
			gbc1.gridwidth = 1;
			gbc1.insets = new Insets(20,5,0,0);
			
			gbc2.gridx = 0;
			gbc2.gridy = 2;
			gbc2.gridwidth = 3;
			gbc2.weightx = 1;
			gbc2.weighty = 1;
			gbc2.fill = GridBagConstraints.BOTH;
			gbc2.insets = new Insets(0,5,0,0);
			
			/*gbc3.gridx = 2;
			gbc3.gridy = 2;
			gbc3.gridwidth = 1;
			gbc3.weightx = 1;
			gbc3.weighty = 1;
			gbc3.fill = GridBagConstraints.BOTH;
			gbc3.insets = new Insets(0,5,0,0);*/
			
			panelSupIzqJP.add(getAsignaturasJL(), gbc1);
			panelSupIzqJP.add(getAsignaturasJSP(), gbc2);
			//panelSupIzqJP.add(getEstadosJSP(), gbc3);
		}
		return panelSupIzqJP;
	}

	public JPanel getPanelSupDerJP() {
		if (panelSupDerJP == null) {
			panelSupDerJP = new JPanel(new GridBagLayout());
			panelSupDerJP.setBackground(COLOR_FONDO);
			GridBagConstraints gbc1 = new GridBagConstraints();
			GridBagConstraints gbc2 = new GridBagConstraints();
			
			gbc1.gridx = 0;
			gbc1.gridy = 0;
			gbc1.gridwidth = 1;
			gbc1.insets = new Insets(20,5,0,0);
			
			gbc2.gridx = 0;
			gbc2.gridy = 2;
			gbc2.gridwidth = 3;
			gbc2.weightx = 1;
			gbc2.weighty = 1;
			gbc2.fill = GridBagConstraints.BOTH;
			gbc2.insets = new Insets(0,5,0,0);
			
			panelSupDerJP.add(getAsignaturasSelectedJL(), gbc1);
			panelSupDerJP.add(getAsignaturasSelectedJSP(), gbc2);
		}
		return panelSupDerJP;
	}

	public JPanel getPanelInfIzqJP() {
		if (panelInfIzqJP == null) {
			panelInfIzqJP = new JPanel(new GridBagLayout());
			panelInfIzqJP.setBackground(COLOR_FONDO);
			GridBagConstraints gbc1 = new GridBagConstraints();
			GridBagConstraints gbc2 = new GridBagConstraints();
			
			gbc1.gridx = 0;
			gbc1.gridy = 0;
			gbc1.gridwidth = 1;
			gbc1.insets = new Insets(20,5,0,0);
			
			gbc2.gridx = 0;
			gbc2.gridy = 2;
			gbc2.gridwidth = 3;
			gbc2.weightx = 1;
			gbc2.weighty = 1;
			gbc2.fill = GridBagConstraints.BOTH;
			gbc2.insets = new Insets(0,5,0,0);
			
			panelInfIzqJP.add(getMateriasJL(), gbc1);
			panelInfIzqJP.add(getMateriasJSP(), gbc2);
			
		}
		return panelInfIzqJP;
	}

	public JPanel getPanelInfDerJP() {
		if (panelInfDerJP == null) {
			panelInfDerJP = new JPanel(new GridBagLayout());
			panelInfDerJP.setBackground(COLOR_FONDO);
			GridBagConstraints gbc1 = new GridBagConstraints();
			GridBagConstraints gbc2 = new GridBagConstraints();
			
			gbc1.gridx = 0;
			gbc1.gridy = 0;
			gbc1.gridwidth = 1;
			gbc1.insets = new Insets(20,5,0,0);
			gbc1.fill = GridBagConstraints.HORIZONTAL;
			
			gbc2.gridx = 0;
			gbc2.gridy = 1;
			gbc2.gridwidth = 1;
			gbc2.weightx = 1;
			gbc2.weighty = 1;
			gbc2.fill = GridBagConstraints.BOTH;
			gbc2.insets = new Insets(20,5,0,0);
			
			panelInfDerJP.add(getMensajesJP(), gbc1);
			panelInfDerJP.add(getSalirJB(), gbc2);
		}
		return panelInfDerJP;
	}

	public JLabel getAsignaturasJL() {
		if (asignaturasJL == null) {
			asignaturasJL = new JLabel("Asignaturas");
			asignaturasJL.setFont(Fuente.titulo());
			asignaturasJL.setForeground(COLOR_TITULO);
		}
		return asignaturasJL;
	}

	public JScrollPane getAsignaturasJSP() {
		if (asignaturasJSP == null) {
			asignaturasJSP = new JScrollPane(getPanelesEsqSupIzq());
			asignaturasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			asignaturasJSP.getViewport().setBackground(COLOR_FONDO);
		}
		return asignaturasJSP;
	}
	
	public JPanel getPanelesEsqSupIzq() {
		if (panelesEsqSupIzq == null) {
			panelesEsqSupIzq = new JPanel(new GridLayout(1,2));
			panelesEsqSupIzq.add(getAsignaturasJP());
			panelesEsqSupIzq.add(getEstadosJP());
		}
		return panelesEsqSupIzq;
	}
	
	public JPanel getAsignaturasJP() {
		if (asignaturasJP == null) {
			asignaturasJP = new JPanel(new GridLayout(0, 1));
			asignaturasJP.setOpaque(false);
			asignaturasJP.setBackground(COLOR_FONDO);
			
			for (JLabel a : getAsignaturasUnselectedJL())
				asignaturasJP.add(a);
		}
		return asignaturasJP;
	}
	
	/*public JScrollPane getEstadosJSP() {
		if (estadosJSP == null) {
			estadosJSP = new JScrollPane(getEstadosJP());
			estadosJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			estadosJSP.getViewport().setBackground(COLOR_FONDO);
		}
		return estadosJSP;
	}*/
	
	public JPanel getEstadosJP() {
		if (estadosJP == null) {
			estadosJP = new JPanel(new GridLayout(0, 3));
			
			for (JRadioButton[] estado : getEstadosAsignaturasALJRB()) {
				estadosJP.add(estado[0]);
				estadosJP.add(estado[1]);
				estadosJP.add(estado[2]);
			}
		}
		return estadosJP;
	}
	
	public ArrayList<JLabel> getAsignaturasUnselectedJL() {
		if (asignaturasUnselectedJL == null) {
			asignaturasUnselectedJL = new ArrayList<JLabel>();
			
			agregarAsignaturasUnselected();
		}
		return asignaturasUnselectedJL;
	}
	
	public void agregarAsignaturasUnselected() {
		Map<String, Materia> materias = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getMaterias();
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				JLabel item = new JLabel(a.getValue().getNombre());
				item.setBackground(COLOR_FONDO);
				item.setFont(Fuente.tabla());
				asignaturasUnselectedJL.add(item);
			}
		}
	}
	
	public ArrayList<ButtonGroup> getEstadosAsignaturasALBG(){
		if (estadosAsignaturasALBG == null) {
			estadosAsignaturasALBG = new ArrayList<ButtonGroup>();
		}
		return estadosAsignaturasALBG;
	}
	
	public ArrayList<JRadioButton[]> getEstadosAsignaturasALJRB() {
		if (estadosAsignaturasALJRB == null) {
			estadosAsignaturasALJRB = new ArrayList<JRadioButton[]>();
		
			agregarRadioButtonsAsignaturas();
		}
		return estadosAsignaturasALJRB;
	}
	
	public void agregarRadioButtonsAsignaturas() {
		for (JLabel asignatura : asignaturasUnselectedJL) {
			JRadioButton sinAprobarJRB = crearRadioButtonAsignatura(asignatura, ESTADO_SIN_APROBAR);
			JRadioButton cursoJRB = crearRadioButtonAsignatura(asignatura, ESTADO_CURSO_APROBADO);
			JRadioButton cursoYExamenJRB = crearRadioButtonAsignatura(asignatura, ESTADO_CURSO_Y_EXAMEN_APROBADO);
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(sinAprobarJRB);
			bg.add(cursoJRB);
			bg.add(cursoYExamenJRB);
			getEstadosAsignaturasALBG().add(bg);
			getEstadosAsignaturasALJRB().add(new JRadioButton[] {sinAprobarJRB, cursoJRB, cursoYExamenJRB});
		}
	}
	
	public JRadioButton crearRadioButtonAsignatura(JLabel asignatura, String estado) {
		
		JRadioButton rb = new JRadioButton(estado);
		
		if (tienePrevias(asignatura.getText()))
			rb.setEnabled(false);
		if (estado.equals(ESTADO_SIN_APROBAR))
			rb.setSelected(true);
		
		rb.addItemListener(crearItemListenerRadioButton(rb, asignatura));
		
		return rb;
	}
	
	public ItemListener crearItemListenerRadioButton(JRadioButton rb, JLabel asignatura) {
		ItemListener cambiarEstado = new ItemListener() {
			public void itemStateChanged (ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					if (rb.getText().equals(ESTADO_CURSO_Y_EXAMEN_APROBADO)) {	
						if (asignaturaAgregada(asignatura.getText())) {
							//Modificar lo que dice la columna 'Aprobado' en la tabla con asignaturas seleccionadas (NO QUITAR Y AGREGAR
							//YA QUE AL QUITARLA SE QUITAN TAMBIEN TODAS LAS ASIGNATURAS QUE LA TIENEN COMO PREVIA Y LUEGO AL AGREGARLA ESTAS
							//NO SE AGREGAN
							if (obtenerEstadoAprobacion(asignatura.getText()).equals(ESTADO_CURSO_APROBADO))
								agregarCreditosMateriaYCarrera(asignatura.getText());
							cambiarEstadoAprobado(asignatura.getText(), ESTADO_CURSO_Y_EXAMEN_APROBADO);
						}
						else {
							agregarAsignaturaSeleccionada(asignatura.getText(), ESTADO_CURSO_Y_EXAMEN_APROBADO);
							agregarCreditosMateriaYCarrera(asignatura.getText());
						}
						
						verifHabilitarAsignaturas();
							
					}
					else if (rb.getText().equals(ESTADO_CURSO_APROBADO)) {
						if (asignaturaAgregada(asignatura.getText())) {
							if (obtenerEstadoAprobacion(asignatura.getText()).equals(ESTADO_CURSO_Y_EXAMEN_APROBADO))
								quitarCreditosMateriaYCarrera(asignatura.getText());
							cambiarEstadoAprobado(asignatura.getText(), ESTADO_CURSO_APROBADO);
						}
						else
							agregarAsignaturaSeleccionada(asignatura.getText(), ESTADO_CURSO_APROBADO);
						
						verifDeshabilitarAsignaturas(asignatura.getText(), ESTADO_CURSO_APROBADO);
						verifHabilitarAsignaturas();
					}
					else {
						if (asignaturaAgregada(asignatura.getText())) {
							if (obtenerEstadoAprobacion(asignatura.getText()).equals(ESTADO_CURSO_Y_EXAMEN_APROBADO))
								quitarCreditosMateriaYCarrera(asignatura.getText());
							quitarAsignaturaSeleccionada(asignatura.getText());
						}
						verifDeshabilitarAsignaturas(asignatura.getText(), ESTADO_SIN_APROBAR);
					}
					verifCambiarMensajes();
					verifCambiarEstadoMateria();
				}
		    }
		};
		rb.addItemListener(cambiarEstado);
		
		return cambiarEstado;
	}
	
	/*public ArrayList<JCheckBox> getAsignaturasJCB() {
		if (asignaturasJCB == null) {
			asignaturasJCB = new ArrayList<JCheckBox>();
			
			agregarAsignaturas();
		}
		return asignaturasJCB;
	}*/
	
	public void cambiarEstadoAprobado(String nombreAsignatura, String estado) {
		for (int i = 0; i < getAsignaturasSelectedJT().getRowCount(); i++) {
			if (getAsignaturasSelectedJT().getValueAt(i, 0).toString().equals(nombreAsignatura)) {
				getAsignaturasSelectedJT().setValueAt(estado, i, 1);
			}
		}
	}
	
	public JLabel getAsignaturasSelectedJL() {
		if (asignaturasSelectedJL == null) {
			asignaturasSelectedJL = new JLabel("Asignaturas seleccionadas");
			asignaturasSelectedJL.setFont(Fuente.titulo());
			asignaturasSelectedJL.setForeground(COLOR_TITULO);
		}
		return asignaturasSelectedJL;
	}

	public JScrollPane getAsignaturasSelectedJSP() {
		if (asignaturasSelectedJSP == null) {
			asignaturasSelectedJSP = new JScrollPane(getAsignaturasSelectedJT());
			asignaturasSelectedJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			asignaturasSelectedJSP.getViewport().setBackground(COLOR_FONDO);
		}
		return asignaturasSelectedJSP;
	}
	
	public JTable getAsignaturasSelectedJT() {
		if (asignaturasSelectedJT == null) {

			String[][] tabla = null;
			String[] columnas = {"Asignatura", "Aprobado", "Créditos", "Materia"};
			asignaturasSelectedJT = new MyJTable(new DefaultTableModel(tabla, columnas));
			
			asignaturasSelectedJT.setCellSelectionEnabled(true);  
            ListSelectionModel select= asignaturasSelectedJT.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            //ESTABLECE TODOS LOS ESTILOS QUE TENDRA LA TABLA
            setearEstilosTabla(asignaturasSelectedJT);
            
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) asignaturasSelectedJT.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		}
		return asignaturasSelectedJT;
	}

	public void setearEstilosTabla(JTable tabla) {
		tabla.setFont(Fuente.tabla());
        tabla.getTableHeader().setFont(Fuente.tablaHeader());
        //Cambia el color de fondo del header
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(107, 137, 255));
        //Al hacer click se selecciona una fila
        tabla.setCellSelectionEnabled(false);
        tabla.setRowSelectionAllowed(true);
        //Deshabilita las lineas del grid de la tabla (pero no del header)
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        //Deshabilita el foco (borde alrededor de la celda) de la celda seleccionada
        tabla.setFocusable(false);
        //Cambia el color de fondo de la fila seleccionada
        tabla.setSelectionBackground(new Color(245, 233, 186));
        //Deshabilita las lineas del grid del header de todos los JTable del programa
        UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
        //Evita mover las columnas de la tabla
        tabla.getTableHeader().setReorderingAllowed(false);
	}
	
	public JLabel getMateriasJL() {
		if (materiasJL == null) {
			materiasJL = new JLabel("Creditos por materia");
			materiasJL.setFont(Fuente.titulo());
			materiasJL.setForeground(COLOR_TITULO);
		}
		return materiasJL;
	}

	public JScrollPane getMateriasJSP() {
		if (materiasJSP == null) {
			materiasJSP = new JScrollPane(getMateriasJT());
			materiasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			materiasJSP.getViewport().setBackground(COLOR_FONDO);
		}
		return materiasJSP;
	}
	
	public JTable getMateriasJT() {
		if (materiasJT == null) {
			String[][] tabla = obtenerDatosMaterias();
			String[] columnas = {"Materia", "Créditos", "Estado"};
			materiasJT = new MyJTable(new DefaultTableModel(tabla, columnas));
			
			
			materiasJT.setCellSelectionEnabled(true);
            ListSelectionModel select= materiasJT.getSelectionModel();
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            //ESTABLECE TODOS LOS ESTILOS QUE TENDRA LA TABLA
            setearEstilosTabla(materiasJT);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            
            materiasJT.getColumn("Créditos").setCellRenderer(centerRenderer);
            materiasJT.getColumn("Estado").setCellRenderer(centerRenderer);
		}
		return materiasJT;
	}

	public JPanel getMensajesJP() {
		if (mensajesJP == null) {
			mensajesJP = new JPanel(new GridBagLayout());
			mensajesJP.setOpaque(true);
			mensajesJP.setBackground(COLOR_FONDO);

			GridBagConstraints gbc1 = new GridBagConstraints();
			GridBagConstraints gbc2 = new GridBagConstraints();
			GridBagConstraints gbc3 = new GridBagConstraints();
			GridBagConstraints gbc4 = new GridBagConstraints();
			
			gbc1.gridx = 0;
			gbc1.gridy = 0;
			gbc1.gridwidth = 1;
			gbc1.insets = new Insets(0,5,5,0);
			
			gbc2.gridx = 0;
			gbc2.gridy = 2;
			gbc2.gridwidth = 2;
			gbc2.weightx = 1;
			gbc2.weighty = 1;
			gbc2.fill = GridBagConstraints.HORIZONTAL;
			gbc2.insets = new Insets(0,5,30,0);
			
			gbc3.gridx = 0;
			gbc3.gridy = 3;
			gbc3.gridwidth = 2;
			gbc3.weightx = 1;
			gbc3.weighty = 1;
			gbc3.fill = GridBagConstraints.HORIZONTAL;
			gbc3.insets = new Insets(0,5,15,0);
			
			gbc4.gridx = 0;
			gbc4.gridy = 4;
			gbc4.gridwidth = 2;
			gbc4.weightx = 1;
			gbc4.weighty = 1;
			gbc4.fill = GridBagConstraints.HORIZONTAL;
			gbc4.insets = new Insets(0,5,0,0);
			
			mensajesJP.add(getCreditosCompletarCarreraJL(), gbc1);
			mensajesJP.add(getCreditosObtenidosVsNecesarios(), gbc2);
			mensajesJP.add(getTotalJL(), gbc3);
			for (JLabel jl : getMensajesAprobadoSiNoJL()) {
				mensajesJP.add(jl, gbc4);
				gbc4.gridy++;
			}
		}
		return mensajesJP;
	}
	
	public JLabel getCreditosCompletarCarreraJL() {
		if (creditosCompletarCarreraJL == null) {
			creditosCompletarCarreraJL = new JLabel("Créditos para completar la carrera");
			creditosCompletarCarreraJL.setFont(Fuente.titulo());
			creditosCompletarCarreraJL.setOpaque(true);
			creditosCompletarCarreraJL.setBackground(COLOR_FONDO);
			creditosCompletarCarreraJL.setForeground(COLOR_TITULO);
		}
		return creditosCompletarCarreraJL;
	}
	
	public JLabel getCreditosObtenidosVsNecesarios() {
		if (creditosObtenidosVsNecesariosJL == null) {
			creditosObtenidosVsNecesariosJL = new JLabel("(Créditos obtenidos / Créditos necesarios)");
			creditosObtenidosVsNecesariosJL.setFont(Fuente.subTitulo());
			creditosObtenidosVsNecesariosJL.setOpaque(true);
			creditosObtenidosVsNecesariosJL.setBackground(COLOR_FONDO);
			creditosObtenidosVsNecesariosJL.setForeground(COLOR_TITULO);
		}
		return creditosObtenidosVsNecesariosJL;
	}

	public JLabel getTotalJL() {
		if (totalJL == null) {
			int cantCreditos = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getCantCreditosMax();
			totalJL = new JLabel("TOTAL: 0/" + cantCreditos);
			totalJL.setFont(Fuente.total());
			totalJL.setOpaque(true);
			totalJL.setBackground(COLOR_FONDO);
			totalJL.setForeground(COLOR_TITULO);
		}
		return totalJL;
	}

	public ArrayList<JLabel> getMensajesAprobadoSiNoJL() {
		if (mensajesAprobadoSiNoJL == null) {
			String[] msjs = MensajeCalculadora.mensaje(false, false);
			mensajesAprobadoSiNoJL = new ArrayList<JLabel>();
			for (String m : msjs) {
				JLabel msj = new JLabel(m);
				msj.setFont(Fuente.mensajes());
				msj.setForeground(COLOR_CREDITOS_INSUFICIENTES);
				mensajesAprobadoSiNoJL.add(msj);
				msj.setOpaque(true);
				msj.setBackground(COLOR_FONDO);
			}
		}
		return mensajesAprobadoSiNoJL;
	}

	public JButton getSalirJB() {
		if (salirJB == null) {
			salirJB = new MiBoton("Salir", Colores.COLOR_BG_CANCELAR, Colores.COLOR_BG_CANCELAR_OVER, Colores.COLOR_BG_CANCELAR_PRESIONADO);
			salirJB.setFont(Fuente.salir());
			salirJB.setForeground(new Color(255,255,255));
			
			ActionListener salir = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					//System.exit(0); //Cierra la aplicacion
					ventanaPrincipal.removerComponentesPanelCentral();
					instancia = null;
			    }
			};
			salirJB.addActionListener(salir);
		}
		return salirJB;
	}
	
	/*public void agregarAsignaturas() {
		Map<String, Materia> materias = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getMaterias();
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				JCheckBox item = new JCheckBox(a.getValue().getNombre());
				item.setBackground(COLOR_FONDO);
				item.addActionListener(obtenerActionListenerAsignatura(item));
				item.setFont(Fuente.tabla());
				if (tienePrevias(item.getText()))
					item.setEnabled(false);
				asignaturasJCB.add(item);
			}
		}
	}*/

	public String[][] obtenerDatosMaterias(){
		
		Map<String, Materia> materias = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getMaterias();
		int cant = materias.size();
		String[][] datos = new String[cant][3];
		
		int fila = 0;
		for (Entry<String, Materia> m : materias.entrySet()) {
			datos[fila][0] = m.getValue().getNombre();
			datos[fila][1] = "0/" + Integer.toString(m.getValue().getCantCreditos());
			datos[fila][2] = "X";
			fila++;
		}
		return datos;
	}
	
	public String[][] obtenerDatosAsignaturas(){
		Map<String, Materia> materias = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getMaterias();
		
		int cant = 0;
		for (Map.Entry<String, Materia> m : materias.entrySet())
			cant += m.getValue().getAsignaturas().size();
		
		String[][] datos = new String[cant][3];
		
		int fila = 0;
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				datos[fila][0] = a.getValue().getNombre();
				datos[fila][1] = Integer.toString(a.getValue().getCantCreditos());
				datos[fila][2] = a.getValue().getNombreMateria();
				fila++;
			}
		}
		return datos;
	}
	
	public boolean tienePrevias(String item) {
		Map<String, Materia> materias = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getMaterias();
		Asignatura asignatura = null;
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				if (a.getValue().getNombre().equals(item))
					asignatura = a.getValue();
			}
		}
		
		if (asignatura != null) {
			if (asignatura.getTienePrevias())
				return true;
		}
		return false;
	}
	
	/*public ActionListener obtenerActionListenerAsignatura(JCheckBox item) {
		
		ActionListener seleccionar = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (item.isSelected()) {
					verifHabilitarAsignaturas();
					agregarCreditosMateriaYCarrera(item.getText());
					agregarAsignaturaSeleccionada(item.getText());
				}
				else {
					verifDeshabilitarAsignaturas(item.getText());
					quitarCreditosMateriaYCarrera(item.getText());
					quitarAsignaturaSeleccionada(item.getText());
				}
				verifCambiarMensajes();
				verifCambiarEstadoMateria();
		    }
		};
		
		return seleccionar;
	}*/
	
	public void verifCambiarEstadoMateria() {
		for (int i = 0; i < getMateriasJT().getRowCount(); i++) {
			String[] campoCreditosMateria = getMateriasJT().getValueAt(i, 1).toString().split("/");
			int creditosActuales = Integer.parseInt((campoCreditosMateria[0]));
			int creditosTotales = Integer.parseInt((campoCreditosMateria[1]));
			
			if (creditosActuales < creditosTotales)
				getMateriasJT().setValueAt("X", i, 2);
			else
				getMateriasJT().setValueAt("OK", i, 2);
		}
	}
	
	public void verifCambiarMensajes() {
		String totalString = getTotalJL().getText().split(":")[1].split("/")[0].replaceAll(" ", "");
		int total = Integer.parseInt(totalString);
		
		boolean creditosCarreraOK = false;
		if (total >= ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getCantCreditosMin())
			creditosCarreraOK = true;
		
		boolean creditosMateriasOK = true;
		for (int i = 0; i < getMateriasJT().getRowCount(); i++) {
			int creditosActuales = Integer.parseInt(getMateriasJT().getValueAt(i, 1).toString().split("/")[0]);
			int creditosNecesarios = Integer.parseInt(getMateriasJT().getValueAt(i, 1).toString().split("/")[1]);
			
			if (creditosActuales < creditosNecesarios) {
				creditosMateriasOK = false;
				break;
			}
		}
		
		String[] mensajes = null;
		Color colorMensajes = COLOR_CREDITOS_INSUFICIENTES;
		
		if (creditosCarreraOK && creditosMateriasOK) {
			mensajes = MensajeCalculadora.mensaje(true, true);
			colorMensajes = COLOR_CREDITOS_SUFICIENTES;
		}
		else if (creditosCarreraOK)
			mensajes = MensajeCalculadora.mensaje(true, false);
		else if (creditosMateriasOK)
			mensajes = MensajeCalculadora.mensaje(false, true);
		else
			mensajes = MensajeCalculadora.mensaje(false, false);
		
		
		//UPDATE mensajes
		int contador = 0;
		for (JLabel item : getMensajesAprobadoSiNoJL())
			item.setText("");
		for (String msj : mensajes) {
			getMensajesAprobadoSiNoJL().get(contador).setText(msj);
			getMensajesAprobadoSiNoJL().get(contador).setForeground(colorMensajes);;
			contador++;
		}
		//UPDATE mensajes
	}
	
	public void agregarCreditosMateriaYCarrera(String nombreAsignatura) {
		actualizarCreditosMateriaYCarrera(nombreAsignatura, true);
	}
	
	public void quitarCreditosMateriaYCarrera(String nombreAsignatura) {
		actualizarCreditosMateriaYCarrera(nombreAsignatura, false);
	}
	
	public void actualizarCreditosMateriaYCarrera(String nombreAsignatura, boolean agregar) {
		Asignatura asig = asignaturasCarrera.get(nombreAsignatura);
		String nombreMateria = asig.getNombreMateria();
		int cantCreditosAsignatura = asig.getCantCreditos();
		int cantActualMateria = 0;
		int cantAntesMateria = 0;
		int cantTotalMateria = 0;
		int cantActualCarrera = 0;
		int cantAntesCarrera = 0;
		int cantTotalCarrera = 0;
		
		for (int i = 0; i < getMateriasJT().getRowCount(); i++) {
			if (getMateriasJT().getValueAt(i, 0).equals(nombreMateria)) {
				
				String[] cantActualMateriaString = getMateriasJT().getValueAt(i, 1).toString().split("/");
				cantAntesMateria = Integer.parseInt(cantActualMateriaString[0]);
				cantTotalMateria = Integer.parseInt(cantActualMateriaString[1]);
				
				String[] cantActualCarreraString = getTotalJL().getText().split("/");	// 'TOTAL: 0' y '250' 
				cantAntesCarrera = Integer.parseInt(cantActualCarreraString[0].split(":")[1].replaceAll(" ", "")); // '0'
				cantTotalCarrera = Integer.parseInt(cantActualCarreraString[1]);
				
				if (agregar) {
					cantActualMateria = cantAntesMateria + cantCreditosAsignatura;
					cantActualCarrera = cantAntesCarrera + cantCreditosAsignatura;
				}
				else {
					cantActualMateria = cantAntesMateria - cantCreditosAsignatura;
					cantActualCarrera = cantAntesCarrera - cantCreditosAsignatura;
				}
				
				getMateriasJT().setValueAt(Integer.toString(cantActualMateria) + "/" + Integer.toString(cantTotalMateria), i, 1);
				getTotalJL().setText("TOTAL: " + Integer.toString(cantActualCarrera) + "/" + Integer.toString(cantTotalCarrera));
				break;
			}
		}
	}
	
	public void agregarAsignaturaSeleccionada(String nombreAsignatura, String estadoAprobado) {
		actualizarAsignaturasSeleccionadas(nombreAsignatura, true, estadoAprobado);
	}
	
	public void quitarAsignaturaSeleccionada(String nombreAsignatura) {
		actualizarAsignaturasSeleccionadas(nombreAsignatura, false, ESTADO_SIN_APROBAR);
	}
	
	public void actualizarAsignaturasSeleccionadas(String nombreAsignatura, boolean agregar, String estadoAprobado) {
		Asignatura asig = asignaturasCarrera.get(nombreAsignatura);
		String creditosAsignatura = Integer.toString(asig.getCantCreditos());
		String nombreMateria = asig.getNombreMateria();
		
		DefaultTableModel model = (DefaultTableModel) asignaturasSelectedJT.getModel();
		
		if (agregar) {
			model.addRow(new Object[]{nombreAsignatura, estadoAprobado, creditosAsignatura, nombreMateria});
		}
		else {
			for (int i = 0; i < getAsignaturasSelectedJT().getRowCount(); i++) {
				if (getAsignaturasSelectedJT().getValueAt(i, 0).toString().equals(nombreAsignatura)) {
					model.removeRow(i);
				}
			}
		}
	}
	
	public void verifHabilitarAsignaturas() {
		Map<String, String> asignaturasNoSeleccionadas = obtenerAsignaturasNoSeleccionadas();	//Las que no estan habilitadas o estan sin aprobar
		Map<String, String> asignaturasNoHabilitadas = obtenerAsignaturasNoHabilitadas();
		
		System.out.println("ASIGNATURAS NO SELECCIONADAS");
		for (Map.Entry<String, String> a : asignaturasNoSeleccionadas.entrySet()) {
			System.out.println(a.getKey());
		}
		System.out.println("_______________________________________________");
		
		System.out.println("ASIGNATURAS NO HABILITADAS");
		for (Map.Entry<String, String> a : asignaturasNoHabilitadas.entrySet()) {
			System.out.println(a.getKey());
		}
		System.out.println("_______________________________________________");
		
		for (Map.Entry<String, String> a : asignaturasNoHabilitadas.entrySet()) {
			Asignatura asig = asignaturasCarrera.get(a.getKey());
			
			boolean tienePreviasSinSeleccionar = false;
			for (Map.Entry<String, String> asignaturaNoSeleccionada : asignaturasNoSeleccionadas.entrySet()) {
				if (asig.getPreviasCurso().containsKey(asignaturaNoSeleccionada.getKey()) ||
					asig.getPreviasTodo().containsKey(asignaturaNoSeleccionada.getKey())) {	//asignatura no habilitada tiene previa sin habilitar?
					
					tienePreviasSinSeleccionar = true;
					break;
				}
				for (Map.Entry<String, String> aac : obtenerAsignaturasAprobadoCurso().entrySet()) {
					if (asig.getPreviasTodo().containsKey(aac.getKey())) {
						tienePreviasSinSeleccionar = true;
						break;
					}
				}
				if (tienePreviasSinSeleccionar)
					break;
			}
			if (!tienePreviasSinSeleccionar) {	//habilitar si todas las previas estan habilitadas
				System.out.println("##################################################");
				System.out.println("nombre asignatura: " + asig.getNombre());
				System.out.println("!tienePreviasSinSeleccionar");
				System.out.println("##################################################");
				int pos = 0;
				for (JLabel item : getAsignaturasUnselectedJL()) {
					if (item.getText().equals(asig.getNombre())) {
						getEstadosAsignaturasALJRB().get(pos)[0].setEnabled(true);
						getEstadosAsignaturasALJRB().get(pos)[1].setEnabled(true);
						getEstadosAsignaturasALJRB().get(pos)[2].setEnabled(true);
						getEstadosAsignaturasALJRB().get(pos)[0].setSelected(true);
						//item.setEnabled(true);
						//item.setSelected(false);
						break;
					}
					pos++;
				}
			}
		}
	}
	
	public void verifDeshabilitarAsignaturas(String nombreAsignaturaDeseleccionada, String estadoActual) {
		Map<String, String> asignaturasHabilitadas = obtenerAsignaturasHabilitadas();
		
		boolean yaEsta = false;
		while (!yaEsta) {
			yaEsta = true;
			for (Map.Entry<String, String> a : asignaturasHabilitadas.entrySet()) {
				Asignatura asig = asignaturasCarrera.get(a.getKey());
				if ((asig.getPreviasCurso().containsKey(nombreAsignaturaDeseleccionada) && !estadoActual.equals(ESTADO_CURSO_APROBADO)) ||
					asig.getPreviasTodo().containsKey(nombreAsignaturaDeseleccionada)) {
					
					int pos = 0;
					for (JLabel item : getAsignaturasUnselectedJL()) {
						if (item.getText().equals(asig.getNombre())) {
							
							if (getEstadosAsignaturasALJRB().get(pos)[2].isSelected()) {
								quitarCreditosMateriaYCarrera(item.getText());
								//verifDeshabilitarAsignaturas(asig.getNombre());	//YA SE LLAMA EN EL 'Else' DEL ITEMLISTENER
							}
							if (getEstadosAsignaturasALJRB().get(pos)[2].isSelected() || getEstadosAsignaturasALJRB().get(pos)[1].isSelected()) {
								quitarAsignaturaSeleccionada(item.getText());
							}
							
							getEstadosAsignaturasALJRB().get(pos)[0].setEnabled(false);
							getEstadosAsignaturasALJRB().get(pos)[1].setEnabled(false);
							getEstadosAsignaturasALJRB().get(pos)[2].setEnabled(false);
							getEstadosAsignaturasALJRB().get(pos)[0].setSelected(true);
							
							//item.setEnabled(false);
							//item.setSelected(false);
							yaEsta = false;
							break;
						}
						pos += 1;
					}
					break;
				}
			}
			asignaturasHabilitadas.clear();
			asignaturasHabilitadas = obtenerAsignaturasHabilitadas();
		}
	}
	
	public Map<String, String> obtenerAsignaturasHabilitadas() {
		Map<String, String> asignaturasHabilitadas = new HashMap<String, String>();
		int pos = 0;
		for (JLabel item : getAsignaturasUnselectedJL()) {
			boolean estadosHabilitados = this.getEstadosAsignaturasALJRB().get(pos)[0].isEnabled();
			if (estadosHabilitados)
				asignaturasHabilitadas.put(item.getText(), item.getText());
			pos += 1;
		}
		return asignaturasHabilitadas;
	}
	
	public Map<String, String> obtenerAsignaturasNoHabilitadas(){
		Map<String, String> asignaturasNoHabilitadas = new HashMap<String, String>();
		int pos = 0;
		for (JLabel item : getAsignaturasUnselectedJL()) {
			boolean estadosHabilitados = this.getEstadosAsignaturasALJRB().get(pos)[0].isEnabled();
			
			if (!estadosHabilitados)
				asignaturasNoHabilitadas.put(item.getText(), item.getText());
			pos++;
		}
		return asignaturasNoHabilitadas;
	}
	
	public Map<String, String> obtenerAsignaturasAprobadoCurso(){
		Map<String, String> asignaturasCurso = new HashMap<String, String>();
		int pos = 0;
		for (JLabel item : getAsignaturasUnselectedJL()) {
			boolean estadosHabilitados = this.getEstadosAsignaturasALJRB().get(pos)[0].isEnabled();
			boolean estadoAprobadoCurso = this.getEstadosAsignaturasALJRB().get(pos)[1].isSelected();
			
			if (estadosHabilitados && estadoAprobadoCurso)
				asignaturasCurso.put(item.getText(), item.getText());
			pos++;
		}
		return asignaturasCurso;
	}
	
	public Map<String, String> obtenerAsignaturasNoSeleccionadas() {
		Map<String, String> asignaturasNoSeleccionadas = new HashMap<String, String>();
		int pos = 0;
		for (JLabel item : getAsignaturasUnselectedJL()) {
			//boolean estadoCurso = this.getEstadosAsignaturasALJRB().get(pos)[1].isSelected();
			//boolean estadoCursoExamen = this.getEstadosAsignaturasALJRB().get(pos)[2].isSelected();
			boolean estadoSinAprobar = this.getEstadosAsignaturasALJRB().get(pos)[0].isSelected();
			boolean estadosHabilitados = this.getEstadosAsignaturasALJRB().get(pos)[0].isEnabled();

			if (!estadosHabilitados || estadoSinAprobar)
				asignaturasNoSeleccionadas.put(item.getText(), item.getText());
			pos++;
		}
		return asignaturasNoSeleccionadas;
	}
	
	public static Map<String, Asignatura> obtenerAsignaturasCarrera(){
		
		Map<String, Materia> materias = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getMaterias();
		Map<String, Asignatura> asignaturasCarrera = new HashMap<String, Asignatura>();
		
		for (Map.Entry<String, Materia> m : materias.entrySet()) {
			for (Map.Entry<String, Asignatura> a : m.getValue().getAsignaturas().entrySet()) {
				asignaturasCarrera.put(a.getKey(), a.getValue());
			}
		}
		
		return asignaturasCarrera;
	}
	
	public Map<String, String> obtenerAsignaturasAgregadas() {
		Map<String, String> asignaturasAgregadas = new HashMap<String, String>();
		
		for (int fila = 0; fila < getAsignaturasSelectedJT().getRowCount(); fila++) {
			asignaturasAgregadas.put(getAsignaturasSelectedJT().getValueAt(fila, 0).toString(), getAsignaturasSelectedJT().getValueAt(fila, 1).toString());
		}
		return asignaturasAgregadas;
	}
	
	public boolean asignaturaAgregada(String nombreAsignatura) {
		Map<String, String> asignaturasAgregadas = obtenerAsignaturasAgregadas();
		if (asignaturasAgregadas.containsKey(nombreAsignatura))
			return true;
		return false;
	}
	
	public String obtenerEstadoAprobacion(String nombreAsignatura) {
		Map<String, String> asignaturasAgregadas = obtenerAsignaturasAgregadas();
		return asignaturasAgregadas.get(nombreAsignatura);
	}
}
