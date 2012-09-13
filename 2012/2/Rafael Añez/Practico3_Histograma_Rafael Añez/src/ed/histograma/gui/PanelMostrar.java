package ed.histograma.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ed.histograma.objects.*;

/**
 * Clase que carga la imagen y el histograma que se visualiza en una ventana
 * creada a partir de una instancia de la clase WinHistograma. Permite escoger
 * si se desea mostrar la imagen o su histograma, no permitiendo que se muestre
 * el histograma en caso de que no se haya mostrado primero una imagen.
 * 
 * @author Rafael Anez
 * 
 */
@SuppressWarnings("serial")
public class PanelMostrar extends JPanel {
	private Histograma histograma;
	private Imagen imagen;
	private int opcion;

	/**
	 * Constructor de la clase PanelMostrar. Inicializa el panel.
	 */
	public PanelMostrar() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de la Superclase JPanel que permite determinar el tamano preferido
	 * del Panel.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(1600, 900);
	}

	/**
	 * Inicializa el panel
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.setLayout(null);
	}

	/**
	 * Permite crear una instancia de la clase Imagen y cargarla en el panel
	 * para que sea mostrada en la ventana principal
	 * 
	 * @param ruta
	 *            Es la ruta del archivo .jpg o .gif que se carga al crear una
	 *            instancia del tipo Imagen.
	 */
	public void mostrarImagen(String ruta) {
		opcion = 1;
		imagen = new Imagen(ruta);
		this.repaint();
	}

	/**
	 * Permite crear una instancia de la clase Histograma y cargarla en el panel
	 * para que sea mostrada en la ventana principal. No puede ser utilizada en
	 * caso de que no se haya cargado y mostrado antes una imagen creada
	 * mediante la clase Imagen.
	 */
	public void mostrarHistograma() {
		opcion = 2;
		if (imagen != null) {
			histograma = new Histograma(imagen);
			histograma.generarHistograma();
		} else {
			JOptionPane.showMessageDialog(null,
					"No hay ninguna imagen cargada. "
							+ "No se puede generar histograma. ", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		this.repaint();
	}

	/**
	 * Metodo de la superclase JPanel que permite pintar en el panel la imagen y
	 * el histograma, segun se seleccione el metodo mostrarImagen o
	 * mostrarHistograma.
	 */
	public void paintComponent(Graphics gc) {
		super.paintComponent(gc);
		switch (opcion) {
		case 1:
			if (imagen != null) {
				imagen.leerArchivo(gc);
				setOpaque(false);
			}
			break;
		case 2:
			if (histograma != null) {
				histograma.dibujarHistograma(gc);
				setOpaque(false);
			}
			break;
		default:
			break;
		}
	}
}
