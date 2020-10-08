package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

import controller.Controlador;
import model.Carrera;

public class Inicio{
	
	private static Inicio instancia;
	private JFrame ventana;
	private JPanel panelCentral;
	private JMenuBar menuBarra;
	private JMenu menuArchivo, menuFunciones, menuAyuda; //Opciones de 'menuBar'
	private JMenu menuSeleccionar, menuCrear, menuEliminar, menuModificar; //Opciones de 'menuFunciones'
	private JMenuItem itemArchivoSalir;
	private ArrayList<JMenuItem> itemsSeleccionar;
	private JMenuItem itemCrearCarrera, itemCrearMateria, itemCrearAsignatura;
	private JMenuItem itemEliminarCarrera, itemEliminarMateria, itemEliminarAsignatura;
	private JMenuItem itemModificarCarrera, itemModificarMateria, itemModificarAsignatura;
	private JMenuItem itemAyudaSobre;
	
	private int anchoCrearCarrera = 1000; private int altoCrearCarrera = 400;
	private int anchoCrearMateria = 1000; private int altoCrearMateria = 400;
	private int anchoCrearAsignatura = 1200; private int altoCrearAsignatura = 600;
	
	private int anchoEliminarCarrera = 1000; private int altoEliminarCarrera = 400;
	private int anchoEliminarMateria = 1000; private int altoEliminarMateria = 400;
	private int anchoEliminarAsignatura = 1000; private int altoEliminarAsignatura = 400;
	
	private int anchoModificarCarrera = 1000; private int altoModificarCarrera = 400;
	private int anchoModificarMateria = 1000; private int altoModificarMateria = 400;
	private int anchoModificarAsignatura = 1200; private int altoModificarAsignatura = 600;
	
	private Inicio() {
		getVentana();
	}
	
	public static Inicio getInstancia() {
		if (instancia == null) {
			instancia = new Inicio();
			
			instancia.removerComponentesPanelCentral();
		}
		return instancia;
	}
	
	public JFrame getVentana() {
		if (ventana == null) {
			ventana = new JFrame("Control avance carrera - Calculadora de créditos");
			ventana.setLayout(new BorderLayout());
			ventana.add(getMenuBarra(), BorderLayout.NORTH);
			ventana.setJMenuBar(getMenuBarra());
			ventana.add(getPanelCentral(), BorderLayout.CENTER);
			ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
			ventana.setVisible(true);
		}
		return ventana;
	}

	public JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new BorderLayout());
		}
		return panelCentral;
	}

	public JMenuBar getMenuBarra() {
		if (menuBarra == null) {
			menuBarra = new JMenuBar();
			menuBarra.add(getMenuArchivo());
			menuBarra.add(getMenuFunciones());
			menuBarra.add(getMenuAyuda());
		}
		return menuBarra;
	}

	public JMenu getMenuArchivo() {
		if (menuArchivo == null) {
			menuArchivo = new JMenu("Archivo");
			menuArchivo.add(getItemArchivoSalir());
		}
		return menuArchivo;
	}

	public JMenu getMenuFunciones() {
		if (menuFunciones == null) {
			menuFunciones = new JMenu("Funciones");
			menuFunciones.add(getMenuSeleccionar());
			menuFunciones.add(getMenuCrear());
			menuFunciones.add(getMenuEliminar());
			menuFunciones.add(getMenuModificar());
		}
		return menuFunciones;
	}

	public JMenu getMenuAyuda() {
		if (menuAyuda == null) {
			menuAyuda = new JMenu("Ayuda");
			menuAyuda.add(getItemAyudaSobre());
		}
		return menuAyuda;
	}

	public JMenu getMenuSeleccionar() {
		if (menuSeleccionar == null) {
			menuSeleccionar = new JMenu("Seleccionar");
			for (JMenuItem mi : getItemsSeleccionar())
				menuSeleccionar.add(mi);
		}
		return menuSeleccionar;
	}

	public JMenu getMenuCrear() {
		if (menuCrear == null) {
			menuCrear = new JMenu("Crear");
			menuCrear.add(getItemCrearCarrera());
			menuCrear.add(getItemCrearMateria());
			menuCrear.add(getItemCrearAsignatura());
		}
		return menuCrear;
	}

	public JMenu getMenuEliminar() {
		if (menuEliminar == null) {
			menuEliminar = new JMenu("Eliminar");
			menuEliminar.add(getItemEliminarCarrera());
			menuEliminar.add(getItemEliminarMateria());
			menuEliminar.add(getItemEliminarAsignatura());
		}
		return menuEliminar;
	}

	public JMenu getMenuModificar() {
		if (menuModificar == null) {
			menuModificar = new JMenu("Modificar");
			menuModificar.add(getItemModificarCarrera());
			menuModificar.add(getItemModificarMateria());
			menuModificar.add(getItemModificarAsignatura());
		}
		return menuModificar;
	}

	public JMenuItem getItemArchivoSalir() {
		if (itemArchivoSalir == null) {
			itemArchivoSalir = new JMenuItem("Salir");
			
			ActionListener actionListenerAS = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					System.exit(0); //Cierra la aplicacion
			    }
			};
			itemArchivoSalir.addActionListener(actionListenerAS);
		}
		return itemArchivoSalir;
	}

	public ArrayList<JMenuItem> getItemsSeleccionar() {
		if (itemsSeleccionar == null) {
			itemsSeleccionar = new ArrayList<JMenuItem>();
			setearItemsSeleccionarBD();
		}
		return itemsSeleccionar;
	}
	
	public JMenuItem getItemCrearCarrera() {
		if (itemCrearCarrera == null) {
			itemCrearCarrera = new JMenuItem("Crear carrera");
			
			ActionListener actionListenerICC = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					removerComponentesPanelCentral();
					getPanelCentral().add(CrearCarrera.getInstancia());
					CrearCarrera.getInstancia().setVentanaPrincipal(getInstancia());
					getVentana().setSize(anchoCrearCarrera, altoCrearCarrera);
					centrarVentana();
					getPanelCentral().revalidate();
					getPanelCentral().repaint();
			    }
			};
			itemCrearCarrera.addActionListener(actionListenerICC);
		}
		return itemCrearCarrera;
	}

	public JMenuItem getItemCrearMateria() {
		if (itemCrearMateria == null) {
			itemCrearMateria = new JMenuItem("Crear materia");
			
			ActionListener actionListenerICM = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					removerComponentesPanelCentral();
					if (CrearMateria.getInstancia() != null) {
						getPanelCentral().add(CrearMateria.getInstancia());
						CrearMateria.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoCrearMateria, altoCrearMateria);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemCrearMateria.addActionListener(actionListenerICM);
		}
		return itemCrearMateria;
	}

	public JMenuItem getItemCrearAsignatura() {
		if (itemCrearAsignatura == null) {
			itemCrearAsignatura = new JMenuItem("Crear asignatura");
			
			ActionListener actionListenerICA = new ActionListener() {
				public void actionPerformed (ActionEvent e) {

					removerComponentesPanelCentral();
					
					if (CrearAsignatura.getInstancia() != null) {
						getPanelCentral().add(CrearAsignatura.getInstancia());
						CrearAsignatura.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoCrearAsignatura, altoCrearAsignatura);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemCrearAsignatura.addActionListener(actionListenerICA);
		}
		return itemCrearAsignatura;
	}

	public JMenuItem getItemEliminarCarrera() {
		if (itemEliminarCarrera == null) {
			itemEliminarCarrera = new JMenuItem("Eliminar carrera");
			
			ActionListener actionListenerIEC = new ActionListener() {
				public void actionPerformed (ActionEvent e) {

					removerComponentesPanelCentral();
					
					if (EliminarCarrera.getInstancia() != null) {
						getPanelCentral().add(EliminarCarrera.getInstancia());
						EliminarCarrera.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoEliminarCarrera, altoEliminarCarrera);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemEliminarCarrera.addActionListener(actionListenerIEC);
		}
		return itemEliminarCarrera;
	}

	public JMenuItem getItemEliminarMateria() {
		if (itemEliminarMateria == null) {
			itemEliminarMateria = new JMenuItem("Eliminar materia");
			
			ActionListener actionListenerIEM = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					removerComponentesPanelCentral();
					
					if (EliminarMateria.getInstancia() != null) {
						getPanelCentral().add(EliminarMateria.getInstancia());
						EliminarMateria.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoEliminarMateria, altoEliminarMateria);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemEliminarMateria.addActionListener(actionListenerIEM);
		}
		return itemEliminarMateria;
	}

	public JMenuItem getItemEliminarAsignatura() {
		if (itemEliminarAsignatura == null) {
			itemEliminarAsignatura = new JMenuItem("Eliminar asignatura");
			
			ActionListener actionListenerIEA = new ActionListener() {
				public void actionPerformed (ActionEvent e) {

					removerComponentesPanelCentral();
					
					if (EliminarAsignatura.getInstancia() != null) {
						getPanelCentral().add(EliminarAsignatura.getInstancia());
						EliminarAsignatura.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoEliminarAsignatura, altoEliminarAsignatura);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemEliminarAsignatura.addActionListener(actionListenerIEA);
		}
		return itemEliminarAsignatura;
	}

	public JMenuItem getItemModificarCarrera() {
		if (itemModificarCarrera == null) {
			itemModificarCarrera = new JMenuItem("Modificar carrera");
			
			ActionListener actionListenerIMC = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					removerComponentesPanelCentral();
					
					if (ModificarCarrera.getInstancia() != null) {
						getPanelCentral().add(ModificarCarrera.getInstancia());
						ModificarCarrera.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoModificarCarrera, altoModificarCarrera);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemModificarCarrera.addActionListener(actionListenerIMC);
		}
		return itemModificarCarrera;
	}

	public JMenuItem getItemModificarMateria() {
		if (itemModificarMateria == null) {
			itemModificarMateria = new JMenuItem("Modificar materia");
			
			ActionListener actionListenerIMM = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					removerComponentesPanelCentral();
					
					if (ModificarMateria.getInstancia() != null) {
						getPanelCentral().add(ModificarMateria.getInstancia());
						ModificarMateria.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoModificarMateria, altoModificarMateria);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemModificarMateria.addActionListener(actionListenerIMM);
		}
		return itemModificarMateria;
	}

	public JMenuItem getItemModificarAsignatura() {
		if (itemModificarAsignatura == null) {
			itemModificarAsignatura = new JMenuItem("Modificar asignatura");
			
			ActionListener actionListenerIMM = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					removerComponentesPanelCentral();
					
					if (ModificarAsignatura.getInstancia() != null) {
						getPanelCentral().add(ModificarAsignatura.getInstancia());
						ModificarAsignatura.getInstancia().setVentanaPrincipal(getInstancia());
						getVentana().setSize(anchoModificarAsignatura, altoModificarAsignatura);
						centrarVentana();
						getPanelCentral().revalidate();
						getPanelCentral().repaint();
					}
			    }
			};
			itemModificarAsignatura.addActionListener(actionListenerIMM);
		}
		return itemModificarAsignatura;
	}

	public JMenuItem getItemAyudaSobre() {
		if (itemAyudaSobre == null) {
			itemAyudaSobre = new JMenuItem("Sobre Control Avance Carrera");
			
			ActionListener actionListenerIAS = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
					Ayuda.mostrarAyuda();
			    }
			};
			itemAyudaSobre.addActionListener(actionListenerIAS);
		}
		return itemAyudaSobre;
	}
	
	public void setearItemsSeleccionarBD() {

		itemsSeleccionar = new ArrayList<JMenuItem>();
		for (Map.Entry<String, Carrera> carrera : Controlador.obtenerCarrerasControlador().entrySet()) {
			JMenuItem jmi = new JMenuItem(carrera.getValue().getNombre());
			itemsSeleccionar.add(jmi);
			agregarActionListenerCarrera(jmi);
			
		}
	}
	
	public void agregarActionListenerCarrera(JMenuItem carrera) {
		
		ActionListener actionListenerCarrera = new ActionListener() {
			public void actionPerformed (ActionEvent e) {

				removerComponentesPanelCentral();
				
				String carreraSeleccionada = obtenerNombreCarrera(e); 
				SeleccionarCarrera.setInstanciaNull();
				
				if (SeleccionarCarrera.getInstancia(carreraSeleccionada) != null) {
					getPanelCentral().add(SeleccionarCarrera.getInstancia(carreraSeleccionada));
					SeleccionarCarrera.getInstancia(carreraSeleccionada).setVentanaPrincipal(getInstancia());
					maximizarVentana();
					getPanelCentral().revalidate();
					getPanelCentral().repaint();
				}
		    }
		};
		carrera.addActionListener(actionListenerCarrera);
	}
	
	public String obtenerNombreCarrera(ActionEvent e) {
		for (JMenuItem jmi : itemsSeleccionar) {
			if (e.getSource() == jmi)
				return jmi.getText();
		}
		return "";
	}
	
	public void removerComponentesPanelCentral() {
		getPanelCentral().removeAll();
		getPanelCentral().revalidate();
		getPanelCentral().repaint();
	}
	
	public void redibujarPanelCentral() {
		getPanelCentral().revalidate();
		getPanelCentral().repaint();
	}
	
	public void centrarVentana() {
		getVentana().setVisible(false);		//Para que no se vea el trayecto de cambio de posicion de la ventana
		getVentana().setLocationRelativeTo(null);	//Centra la ventana
		getVentana().setVisible(true);	//Muestra la ventana
	}
	
	public void maximizarVentana() {
		getVentana().setVisible(false);		//Para que no se vea el trayecto de cambio de posicion de la ventana
		getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
		getVentana().setVisible(true);	//Muestra la ventana
	}
}
