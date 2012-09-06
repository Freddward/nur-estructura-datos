/**
 * 
 */
package ed.fractal.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ed.fractal.objects.*;

/**
 * Ventana principal que permite visualizar el triangulo y la alfombra de
 * Sierpinsky
 * 
 * @author Rafael Anez
 * 
 */
@SuppressWarnings("serial")
public class WinFractal extends JFrame {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuArchivo = new JMenu();
	private JMenuItem menuArchivoExit = new JMenuItem();
	private JMenuItem menuArchivoNuevoTrianguloSierpinsky = new JMenuItem();
	private JMenuItem menuArchivoNuevoAlfombraSierpinsky = new JMenuItem();

	private PanelFractal pnl;

	private static Logger logger = Logger.getLogger(WinFractal.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resource = "/auditoria.properties";
		URL configFileResource = WinFractal.class.getResource(resource);
		PropertyConfigurator.configure(configFileResource);

		WinFractal w = new WinFractal();

		w.setResizable(false);
		w.setLocation(400, 20);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
		w.pack();
	}

	/**
	 * Constructor de la clase WinFractal. Inicializa el menu.
	 */
	public WinFractal() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite crear el menu que se va a mostrar en la ventana principal. Crea
	 * los submenus Alfombra de Sierpinsky, Triangulo de Sierpinsky y Salir.
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.setJMenuBar(menuBar);
		this.getContentPane().setLayout(new BorderLayout());

		this.setTitle("Fractales");
		menuArchivo.setText("Archivo");

		menuArchivoExit.setText("Salir");
		menuArchivoExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ArchivoSalir_ActionPerformed(ae);
			}
		});

		menuArchivoNuevoTrianguloSierpinsky
				.setText("Nuevo Triangulo Sierpinsky");
		menuArchivoNuevoTrianguloSierpinsky
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						ArchivoNuevoTrianguloSierpinsky_ActionPerformed(ae);
					}
				});
		menuArchivo.add(menuArchivoNuevoTrianguloSierpinsky);

		menuArchivoNuevoAlfombraSierpinsky.setText("Nueva Alfombra Sierpinsky");
		menuArchivoNuevoAlfombraSierpinsky
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						ArchivoNuevoAlfombraSierpinsky_ActionPerformed(ae);
					}
				});
		menuArchivo.add(menuArchivoNuevoAlfombraSierpinsky);

		menuArchivo.addSeparator();
		menuArchivo.add(menuArchivoExit);
		menuBar.add(menuArchivo);

		pnl = new PanelFractal();
		getContentPane().add(pnl, BorderLayout.CENTER);

	}

	/**
	 * Permite generar una alfombra de Sierpinsky. Para ello abre un panel de
	 * opciones mediante el metodo pedirProfundidad, el usuario ingresa un valor
	 * entero entre 1 y 8 y entonces llama al metodo animarFractal.
	 * 
	 * @param ae
	 *            Objeto del tipo ActionEvent
	 */
	void ArchivoNuevoAlfombraSierpinsky_ActionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int prof = Integer.parseInt(pedirProfundidad());
		if (prof > 0 && prof <= 8) {
			logger.debug("Hace una animacion de una alfombra de Sierpinsky con una profundidad de "
					+ prof);
			animarFractal(Fractal.ALFOMBRA, prof);
		} else {
			JOptionPane.showMessageDialog(null,
					"Numero no valido. Ingrese un numero entre 1 y 8", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.error("Numero ingresado no valido");
		}
	}

	/**
	 * Permite generar un triangulo de Sierpinsky. Para ello abre un panel de
	 * opciones mediante el metodo pedirProfundidad, el usuario ingresa un valor
	 * entero entre 0 y 8 y entonces llama al metodo animarFractal.
	 * 
	 * @param ae
	 *            Objeto del tipo ActionEvent
	 */
	void ArchivoNuevoTrianguloSierpinsky_ActionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int prof = Integer.parseInt(pedirProfundidad());
		if (prof >= 1 && prof <= 8) {
			logger.debug("Hace una animacion de un triangulo de Sierpinsky con una profundidad de "
					+ prof);
			animarFractal(Fractal.TRIANGULO, prof);
		} else {
			JOptionPane.showMessageDialog(null,
					"Numero no valido. Ingrese un numero entre 1 y 8", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.error("Numero ingresado no valido");
		}
	}

	/**
	 * Permite salir del programa al apretar la opcion Salir del menu de la
	 * ventana principal.
	 * 
	 * @param ae
	 *            Objeto del tipo ActionEvent
	 */
	void ArchivoSalir_ActionPerformed(ActionEvent ae) {
		logger.debug("Aplicacion terminada");
		this.setVisible(false);
		System.exit(0);
	}

	/**
	 * Utiliza un panel de opcion para pedir la profundidad del Fractal que se
	 * desea dibujar.
	 * 
	 * @return El valor que se ingrese en el panel de opcion.
	 */
	String pedirProfundidad() {
		String prof = JOptionPane
				.showInputDialog("De que profundidad quiere dibujar el Fractal?");
		return prof;
	}

	/**
	 * animarFractal permite realizar una animacion del Fractal que se quiere
	 * dibujar. Para realizarlo utiliza un thread que dibuja sucesivamente el
	 * fractal utilizando el valor entero profundidad, valor que se utiliza para
	 * determinar cuantos frames o cuadros se van a dibujar
	 * 
	 * @param opcion
	 *            Tipo de fractal que se quiere animar
	 * @param profundidad
	 *            Cantidad de veces que se quiere dibujar recursivamente un
	 *            fractal. En este caso determina la cantidad de frames que
	 *            tendra la animacion
	 */
	void animarFractal(final int opcion, final int profundidad) {
		Runnable r = new Runnable() {
			public void run() {
				int prof = profundidad;
				for (int i = 0; i < prof; i++) {
					logger.debug("Dibujando el frame numero " + (i + 1)
							+ " de la animacion");
					pnl.opcionADibujar(opcion, i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				logger.debug("Animacion del fractal terminada");
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
