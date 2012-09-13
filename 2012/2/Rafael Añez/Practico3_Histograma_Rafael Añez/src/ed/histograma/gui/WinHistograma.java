package ed.histograma.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ed.histograma.objects.Histograma;
import ed.histograma.objects.Imagen;

/**
 * Clase principal del proyecto Histograma. Permite mostrar en pantalla una
 * imagen que ha sido cargada en el programa, y tambien permite mostrar un
 * histograma con la cantidad de pixeles que tienen las distintas tonalidades de
 * rojo, verde y azul.
 * 
 * @author Rafael Anez
 * 
 */
public class WinHistograma extends JFrame {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuArchivo = new JMenu();
	private JMenuItem menuArchivoSalir = new JMenuItem();
	private JMenuItem menuArchivoCargarImagen = new JMenuItem();
	private JMenuItem menuArchivoMostrarHistograma = new JMenuItem();

	private PanelMostrar pnl;
	private String ruta;

	private static Logger logger = Logger.getLogger(WinHistograma.class);

	public static void main(String[] args) {
		String resource = "/auditoria.properties";
		URL configFileResource = WinHistograma.class.getResource(resource);
		PropertyConfigurator.configure(configFileResource);

		WinHistograma w = new WinHistograma();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
		w.pack();
	}

	/**
	 * Constructor de la clase WinHistograma. Crea e inicializa el menu.
	 */
	public WinHistograma() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite crear el menu que se va a mostrar en la ventana principal. Crea
	 * los submenus Cargar Imagen, Mostrar Histograma y Salir.
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.setJMenuBar(menuBar);
		this.getContentPane().setLayout(new BorderLayout());

		this.setTitle("Histograma");
		menuArchivo.setText("Archivo");

		menuArchivoSalir.setText("Salir");
		menuArchivoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				archivoSalir_ActionPerformed(ae);
			}
		});

		menuArchivoCargarImagen.setText("Cargar Imagen");
		menuArchivoCargarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				archivoCargarImagen_ActionPerformed(ae);
			}
		});
		menuArchivo.add(menuArchivoCargarImagen);

		menuArchivoMostrarHistograma.setText("Mostrar Histograma");
		menuArchivoMostrarHistograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				archivoMostrarHistograma_ActionPerformed(ae);
			}
		});
		menuArchivo.add(menuArchivoMostrarHistograma);

		menuArchivo.addSeparator();
		menuArchivo.add(menuArchivoSalir);
		menuBar.add(menuArchivo);

		pnl = new PanelMostrar();
		getContentPane().add(pnl, BorderLayout.CENTER);
	}

	/**
	 * Muestra un histograma con la cantidad de pixeles que tienen las distintas
	 * tonalidades de rojo, verde y azul que tiene la imagen mostrada en
	 * pantalla.
	 * 
	 * @param ae
	 *            Objeto de tipo ActionEvent
	 */
	void archivoMostrarHistograma_ActionPerformed(ActionEvent ae) {
		try {
			pnl.mostrarHistograma();
			logger.debug("Se muestra el histograma generado");
		} catch (Exception e) {
			logger.error("No se pudo mostrar el Histograma.", e);
		}
	}

	/**
	 * Permite mostrar en la ventana una imagen en formato jpg o gif de
	 * cualquier tamano. Para cargar la imagen abre una ventana que permite
	 * navegar en los distintos directorios del sistema de archivos de la
	 * computadora hasta encontrar la imagen que se desea mostrar.
	 * 
	 * @param ae
	 *            Objeto del tipo ActionEvent
	 */
	void archivoCargarImagen_ActionPerformed(ActionEvent ae) {
		abrirArchivo();
		if (ruta != null) {
			try {
				pnl.mostrarImagen(ruta);
				logger.debug("Imagen cargada correctamente");
			} catch (Exception e) {
				logger.error("No se pudo cargar la imagen", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Permite salir del programa al apretar la opcion Salir del menu de la
	 * ventana principal.
	 * 
	 * @param ae
	 *            Objeto del tipo ActionEvent
	 */
	void archivoSalir_ActionPerformed(ActionEvent ae) {
		logger.debug("Aplicacion terminada.");
		setVisible(false);
		System.exit(0);
	}

	/**
	 * Abre una ventana que permite navegar por los directorios del disco duro,
	 * permitiendole al usuario escoger la imagen que desea cargar. Restringe
	 * los tipos de archivos que muestra a los formatos .jpg y .gif, para que el
	 * usuario no se preocupe de cargar formatos incompatibles.
	 */
	private void abrirArchivo() {
		ruta = null;
		// Crear un objeto FileChooser
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(
				".jpg & .gif", "jpg", "gif");
		fc.setFileFilter(filtro);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);
		int respuesta = fc.showOpenDialog(this);
		logger.debug("Se abre la ventana de seleccion");
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			try {
				File name = fc.getSelectedFile();
				ruta = fc.getSelectedFile().getPath();
				logger.debug("Se carga el archivo " + name
						+ " que se encuentra en la ruta " + ruta);
			} catch (Exception e) {
				logger.error("No se pudo cargar el archivo", e);
			}
		} else
			logger.debug("La seleccion de la imagen fue cancelada");

	}
}
