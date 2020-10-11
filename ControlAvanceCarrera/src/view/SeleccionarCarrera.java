package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private ArrayList<JCheckBox> asignaturasJCB;
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
			
			panelSupIzqJP.add(getAsignaturasJL(), gbc1);
			panelSupIzqJP.add(getAsignaturasJSP(), gbc2);
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
			asignaturasJSP = new JScrollPane(getAsignaturasJP());
			asignaturasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			asignaturasJSP.getViewport().setBackground(COLOR_FONDO);
		}
		return asignaturasJSP;
	}
	
	public JPanel getAsignaturasJP() {
		if (asignaturasJP == null) {
			asignaturasJP = new JPanel(new GridLayout(0, 1));
			asignaturasJP.setOpaque(false);
			asignaturasJP.setBackground(COLOR_FONDO);
			
			for (JCheckBox a : getAsignaturasJCB())
				asignaturasJP.add(a);
		}
		return asignaturasJP;
	}

	public ArrayList<JCheckBox> getAsignaturasJCB() {
		if (asignaturasJCB == null) {
			asignaturasJCB = new ArrayList<JCheckBox>();
			
			agregarAsignaturas();
		}
		return asignaturasJCB;
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
			String[] columnas = {"Asignatura", "Créditos", "Materia"};
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
	
	public void agregarAsignaturas() {
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
	}

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
	
	public ActionListener obtenerActionListenerAsignatura(JCheckBox item) {
		
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
	}
	
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
	
	public void agregarAsignaturaSeleccionada(String nombreAsignatura) {
		actualizarAsignaturasSeleccionadas(nombreAsignatura, true);
	}
	
	public void quitarAsignaturaSeleccionada(String nombreAsignatura) {
		actualizarAsignaturasSeleccionadas(nombreAsignatura, false);
	}
	
	public void actualizarAsignaturasSeleccionadas(String nombreAsignatura, boolean agregar) {
		Asignatura asig = asignaturasCarrera.get(nombreAsignatura);
		String creditosAsignatura = Integer.toString(asig.getCantCreditos());
		String nombreMateria = asig.getNombreMateria();
		
		DefaultTableModel model = (DefaultTableModel) asignaturasSelectedJT.getModel();
		
		if (agregar)
			model.addRow(new Object[]{nombreAsignatura, creditosAsignatura, nombreMateria});
		else {
			for (int i = 0; i < getAsignaturasSelectedJT().getRowCount(); i++) {
				if (getAsignaturasSelectedJT().getValueAt(i, 0).toString().equals(nombreAsignatura)) {
					model.removeRow(i);
				}
			}
		}
	}
	
	public void verifHabilitarAsignaturas() {
		Map<String, String> asignaturasNoSeleccionadas = obtenerAsignaturasNoSeleccionadas();
		Map<String, String> asignaturasNoHabilitadas = obtenerAsignaturasNoHabilitadas();
		
		for (Map.Entry<String, String> a : asignaturasNoHabilitadas.entrySet()) {
			Asignatura asig = asignaturasCarrera.get(a.getKey());
			
			boolean tienePreviasSinSeleccionar = false;
			for (Map.Entry<String, String> asignaturaNoSeleccionada : asignaturasNoSeleccionadas.entrySet()) {
				if (asig.getPrevias().containsKey(asignaturaNoSeleccionada.getKey())) {	//asignatura no habilitada tiene previa sin habilitar?
					tienePreviasSinSeleccionar = true;
					break;
				}
			}
			if (!tienePreviasSinSeleccionar) {	//habilitar si todas las previas estan habilitadas
				for (JCheckBox item : getAsignaturasJCB()) {
					if (item.getText().equals(asig.getNombre())) {
						item.setEnabled(true);
						item.setSelected(false);
					}
				}
			}
		}
	}
	
	public void verifDeshabilitarAsignaturas(String nombreAsignaturaDeseleccionada) {
		Map<String, String> asignaturasHabilitadas = obtenerAsignaturasHabilitadas();
		
		boolean yaEsta = false;
		while (!yaEsta) {
			yaEsta = true;
			for (Map.Entry<String, String> a : asignaturasHabilitadas.entrySet()) {
				Asignatura asig = asignaturasCarrera.get(a.getKey());
				if (asig.getPrevias().containsKey(nombreAsignaturaDeseleccionada)) {
					for (JCheckBox item : getAsignaturasJCB()) {
						if (item.getText().equals(asig.getNombre())) {
							
							if (item.isSelected()) {
								quitarCreditosMateriaYCarrera(item.getText());
								quitarAsignaturaSeleccionada(item.getText());
								verifDeshabilitarAsignaturas(asig.getNombre());
							}
							
							item.setEnabled(false);
							item.setSelected(false);
							yaEsta = false;
							break;
						}
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
		for (JCheckBox item : getAsignaturasJCB()) {
			if (item.isEnabled())
				asignaturasHabilitadas.put(item.getText(), item.getText());
		}
		return asignaturasHabilitadas;
	}
	
	public Map<String, String> obtenerAsignaturasNoHabilitadas(){
		Map<String, String> asignaturasNoHabilitadas = new HashMap<String, String>();
		for (JCheckBox item : getAsignaturasJCB()) {
			if (!item.isEnabled())
				asignaturasNoHabilitadas.put(item.getText(), item.getText());
		}
		return asignaturasNoHabilitadas;
	}
	
	public Map<String, String> obtenerAsignaturasNoSeleccionadas() {
		Map<String, String> asignaturasNoSeleccionadas = new HashMap<String, String>();
		for (JCheckBox item : getAsignaturasJCB()) {
			if (!item.isSelected())
				asignaturasNoSeleccionadas.put(item.getText(), item.getText());
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
}
