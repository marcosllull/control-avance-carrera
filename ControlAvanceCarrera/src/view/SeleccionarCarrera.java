package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.Asignatura;
import model.Fuente;
import model.ManejadorCarrera;
import model.Materia;
import model.MensajeCalculadora;
import model.MostrarMensaje;

@SuppressWarnings("serial")
public class SeleccionarCarrera extends JPanel{
	
	private static SeleccionarCarrera instancia;
	private Inicio ventanaPrincipal;
	private String nombreCarrera;
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
	private JLabel totalJL;
	private JLabel mensajeAprobadoSiNoJL;
	private JButton salirJB;
	
	private SeleccionarCarrera(String nombreCarrera) {
		
		this.nombreCarrera = nombreCarrera;
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
	
	public void setVentanaPrincipal(Inicio ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public JPanel getPanelSupIzqJP() {
		if (panelSupIzqJP == null) {
			panelSupIzqJP = new JPanel(new GridBagLayout());
			GridBagConstraints cJL = new GridBagConstraints();
			cJL.gridx = 0;
			cJL.gridy = 0;
			//cJL.gridheight = 1;
			cJL.gridwidth = 3;
			cJL.fill = GridBagConstraints.HORIZONTAL;
			
			GridBagConstraints cJSP = new GridBagConstraints();
			//cJSP.gridheight = 1;
			cJSP.gridx = 2;
			cJSP.gridy = 1;
			cJSP.gridwidth = 1;
			//cJSP.fill = GridBagConstraints.HORIZONTAL;
			
			panelSupIzqJP.add(getAsignaturasJL(), cJL);
			panelSupIzqJP.add(getAsignaturasJSP(), cJSP);
			//panelSupIzqJP.add(new JButton("boton1"), cJL);
			//panelSupIzqJP.add(new JButton("boton2"), cJSP);
		}
		return panelSupIzqJP;
	}

	public JPanel getPanelSupDerJP() {
		if (panelSupDerJP == null) {
			panelSupDerJP = new JPanel(new GridLayout(0, 1));
			
			panelSupDerJP.add(getAsignaturasSelectedJL());
			panelSupDerJP.add(getAsignaturasSelectedJSP());
		}
		return panelSupDerJP;
	}

	public JPanel getPanelInfIzqJP() {
		if (panelInfIzqJP == null) {
			panelInfIzqJP = new JPanel(new GridLayout(0, 1));
			
			panelInfIzqJP.add(getMateriasJL());
			panelInfIzqJP.add(getMateriasJSP());
			
		}
		return panelInfIzqJP;
	}

	public JPanel getPanelInfDerJP() {
		if (panelInfDerJP == null) {
			panelInfDerJP = new JPanel(new GridLayout(0, 1));
			
			panelInfDerJP.add(getMensajesJP());
			panelInfDerJP.add(getSalirJB());
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
			String[][] tabla = obtenerDatosAsignaturas();
			String[] columnas = {"Asignatura", "Créditos", "Materia"};
			asignaturasSelectedJT = new JTable(tabla, columnas);
			
			asignaturasSelectedJT.setCellSelectionEnabled(true);  
            ListSelectionModel select= asignaturasSelectedJT.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);  
		}
		return asignaturasSelectedJT;
	}

	public JLabel getMateriasJL() {
		if (materiasJL == null) {
			materiasJL = new JLabel("Creditos por materia");
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
		}
		return materiasJT;
	}

	public JPanel getMensajesJP() {
		if (mensajesJP == null) {
			mensajesJP = new JPanel();
			mensajesJP.add(getCreditosCompletarCarreraJL());
			mensajesJP.add(getTotalJL());
			mensajesJP.add(getMensajeAprobadoSiNoJL());
		}
		return mensajesJP;
	}
	
	public JLabel getCreditosCompletarCarreraJL() {
		if (creditosCompletarCarreraJL == null) {
			creditosCompletarCarreraJL = new JLabel("Créditos para completar la carrera\n(Créditos obtenidos / Créditos necesarios)");
		}
		return creditosCompletarCarreraJL;
	}

	public JLabel getTotalJL() {
		if (totalJL == null) {
			int cantCreditos = ManejadorCarrera.getInstancia().getCarreras().get(nombreCarrera).getCantCreditosMax();
			totalJL = new JLabel("TOTAL: 0/" + cantCreditos);
		}
		return totalJL;
	}

	public JLabel getMensajeAprobadoSiNoJL() {
		if (mensajeAprobadoSiNoJL == null) {
			mensajeAprobadoSiNoJL = new JLabel(MensajeCalculadora.aprobadoSiNo(false, false));
		}
		return mensajeAprobadoSiNoJL;
	}

	public JButton getSalirJB() {
		if (salirJB == null) {
			salirJB = new JButton("Salir");
			
			ActionListener salir = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
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
				asignaturasJCB.add(new JCheckBox(a.getValue().getNombre()));
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
}
