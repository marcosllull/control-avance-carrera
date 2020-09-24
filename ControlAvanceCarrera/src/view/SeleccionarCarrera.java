package view;

import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.Asignatura;
import model.Carrera;
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
		}
		return asignaturasJL;
	}

	public JScrollPane getAsignaturasJSP() {
		if (asignaturasJSP == null) {
			asignaturasJSP = new JScrollPane(getAsignaturasJP());
			asignaturasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return asignaturasJSP;
	}
	
	public JPanel getAsignaturasJP() {
		if (asignaturasJP == null) {
			asignaturasJP = new JPanel(new GridLayout(0, 1));
			
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
		}
		return asignaturasSelectedJL;
	}

	public JScrollPane getAsignaturasSelectedJSP() {
		if (asignaturasSelectedJSP == null) {
			asignaturasSelectedJSP = new JScrollPane(getAsignaturasSelectedJT());
			asignaturasSelectedJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return asignaturasSelectedJSP;
	}
	
	public JTable getAsignaturasSelectedJT() {
		if (asignaturasSelectedJT == null) {

			String[][] tabla = null;
			String[] columnas = {"Asignatura", "Créditos", "Materia"};
			asignaturasSelectedJT = new JTable(new DefaultTableModel(tabla, columnas));
			
			asignaturasSelectedJT.setCellSelectionEnabled(true);  
            ListSelectionModel select= asignaturasSelectedJT.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            
            asignaturasSelectedJT.setFont(Fuente.tabla());
            asignaturasSelectedJT.getTableHeader().setFont(Fuente.tablaHeader());
            DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) asignaturasSelectedJT.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		}
		return asignaturasSelectedJT;
	}

	public JLabel getMateriasJL() {
		if (materiasJL == null) {
			materiasJL = new JLabel("Creditos por materia");
			materiasJL.setFont(Fuente.titulo());
		}
		return materiasJL;
	}

	public JScrollPane getMateriasJSP() {
		if (materiasJSP == null) {
			materiasJSP = new JScrollPane(getMateriasJT());
			materiasJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return materiasJSP;
	}
	
	public JTable getMateriasJT() {
		if (materiasJT == null) {
			String[][] tabla = obtenerDatosMaterias();
			String[] columnas = {"Materia", "Créditos", "Estado"};
			materiasJT = new JTable(tabla, columnas);
			
			materiasJT.setCellSelectionEnabled(true);
            ListSelectionModel select= materiasJT.getSelectionModel();
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            materiasJT.setFont(Fuente.tabla());
            materiasJT.getTableHeader().setFont(Fuente.tablaHeader());
            
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
		}
		return creditosCompletarCarreraJL;
	}
	
	public JLabel getCreditosObtenidosVsNecesarios() {
		if (creditosObtenidosVsNecesariosJL == null) {
			creditosObtenidosVsNecesariosJL = new JLabel("(Créditos obtenidos / Créditos necesarios)");
			creditosObtenidosVsNecesariosJL.setFont(Fuente.subTitulo());
		}
		return creditosObtenidosVsNecesariosJL;
	}

	public JLabel getTotalJL() {
		if (totalJL == null) {
			int cantCreditos = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getCantCreditosMax();
			totalJL = new JLabel("TOTAL: 0/" + cantCreditos);
			totalJL.setFont(Fuente.total());
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
				msj.setForeground(Color.RED);
				mensajesAprobadoSiNoJL.add(msj);
			}
		}
		return mensajesAprobadoSiNoJL;
	}

	public JButton getSalirJB() {
		if (salirJB == null) {
			salirJB = new JButton("Salir");
			salirJB.setFont(Fuente.salir());
			
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
		
		if (creditosCarreraOK && creditosMateriasOK)
			mensajes = MensajeCalculadora.mensaje(true, true);
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
		System.out.println("---------------------------------------");
		for (Map.Entry<String, Carrera> car : ManejadorCarrera.getInstancia().getCarreras().entrySet()) {
			System.out.println(car.getValue().getNombre());
		}
		System.out.println("---------------------------------------");
		if (ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera) == null) {
			System.out.println("nombreCarrera: " + nombreCarrera);
			System.out.println("ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera) = null");
		}
		
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
