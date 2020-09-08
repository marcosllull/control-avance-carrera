package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Inicio{
	
	private static Inicio instancia;
	private JFrame ventana;
	private JPanel panelCentral;
	private JMenuBar menuBarra;
	private JMenu menuArchivo, menuFunciones, menuAyuda; //Opciones de 'menuBar'
	private JMenu menuSeleccionar, menuCrear, menuEliminar, menuModificar; //Opciones de 'menuFunciones'
	private JMenuItem itemArchivoSalir;
	private JMenuItem[] itemsSeleccionar;
	private JMenuItem itemCrearCarrera, itemCrearMateria, itemCrearAsignatura;
	private JMenuItem itemEliminarCarrera, itemEliminarMateria, itemEliminarAsignatura;
	private JMenuItem itemModificarCarrera, itemModificarMateria, itemModificarAsignatura;
	private JMenuItem itemAyudaSobre;
	
	private Inicio() {
		getVentana();
	}
	
	public static Inicio getInstancia() {
		if (instancia == null) {
			instancia = new Inicio();
		}
		return instancia;
	}
	
	public JFrame getVentana() {
		if (ventana == null) {
			ventana = new JFrame("Control avance carrera - Calculadora de créditos");
			ventana.setLayout(new BorderLayout());
			ventana.add(getMenuBarra(), BorderLayout.NORTH);
			ventana.setJMenuBar(getMenuBarra());
			ventana.add(getPanelCentral(), BorderLayout.CENTER);	//LA CANTIDAD DE FILAS Y COLUMNAS DEBE DARSE DEPENDIENDO DE LA OPCION DEL PROGRAMA
			ventana.setSize(800, 600);
			//ventana.pack(); //Da las dimensiones correctas a la ventana
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

	public JMenuItem[] getItemsSeleccionar() {
		if (itemsSeleccionar == null) {
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
					new EliminarAsignatura();
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
					new ModificarCarrera();
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
					new ModificarMateria();
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
					new ModificarAsignatura();
			    }
			};
			itemModificarAsignatura.addActionListener(actionListenerIMM);
		}
		return itemModificarAsignatura;
	}

	public JMenuItem getItemAyudaSobre() {
		if (itemAyudaSobre == null) {
			itemAyudaSobre = new JMenuItem("Ayuda");
			
			ActionListener actionListenerIAS = new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					ventana.setTitle("*****************************AYUDA SOBRE******************************");
			    }
			};
			itemAyudaSobre.addActionListener(actionListenerIAS);
		}
		return itemAyudaSobre;
	}
	
	public void setearItemsSeleccionarBD() {
		////Obtener la cantidad de carreras de la base de datos
		//int cant = ControladorBD.obtenerCantCarreras();
		//itemsSeleccionar = new JMenuItem[cant];
		////Recorrer cada una de las posiciones y almacenar el nombre de la carrera en el item
		///En cada vuelta del for se debe llamar al metodo agregarActionListenerCarrera(JMenuItem itemSeleccionar[x])
		////TERMINA AQUI
		itemsSeleccionar = new JMenuItem[3];
		itemsSeleccionar[0] = new JMenuItem("Tecnologo en informatica");
		itemsSeleccionar[1] = new JMenuItem("Ingenieria de software");
		itemsSeleccionar[2] = new JMenuItem("Tecnicatura en redes");
	}
	
	public void agregarActionListenerCarrera(JMenuItem carrera) {
		ActionListener actionListenerCarrera = new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				verCarrera(carrera.getText());
			}
		};
		carrera.addActionListener(actionListenerCarrera);
	}
	
	public void verCarrera(String nombreCarrera) {
		//ACA DEBERIA CREAR TODOS LOS FORMULARIOS DEL PANEL CENTRAL IGUAL QUE EN LA CALCULADORA DE CREDITOS Y COMPLETARLOS CON LOS
		//DATOS DE LA CARRERA CONSULTANDOLO EN LA BASE DE DATOS POR SU NOMBRE
		ventana.setTitle(nombreCarrera);
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
}
