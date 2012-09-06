package ed.fractal.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ed.fractal.objects.*;

/**
 * La clase PanelFractal es la encargada de mostrar en la ventana WinFractal el
 * fractal que se desee dibujar.
 * 
 */
@SuppressWarnings("serial")
public class PanelFractal extends JPanel {

	private Fractal frctl;

	/**
	 * Constructor de la clase PanelFractal. Inicializa el panel, la dimension y
	 * se asigna el color de fondo del panel.
	 */
	public PanelFractal() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setPreferredSize(getPreferedSize());
		this.setBackground(Color.BLACK);
	}

	/**
	 * Asigna la disposicion del panel.
	 * 
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		this.setLayout(null);
	}

	/**
	 * Asigna las dimensiones del panel.
	 * 
	 * @return Objeto de tipo Dimension con el ancho y el alto del panel (En
	 *         este caso 800x800)
	 */
	public Dimension getPreferedSize() {
		return new Dimension(800, 800);
	}

	/**
	 * Determina el tipo de fractal a dibujar y su profundidad. Cuando se
	 * determina el tipo, crea una instancia del fractal y lo repinta en el
	 * panel.
	 * 
	 * @param opcion
	 *            Es el tipo de fractal a dibujar.
	 * @param profundidad
	 *            Es la cantidad de veces que se quiere dibujar recursivamente
	 *            el fractal.
	 */
	public void opcionADibujar(int opcion, int profundidad) {
		switch (opcion) {
		case 1:
			frctl = new AlfombraSierpinsky(profundidad);
			break;
		case 2:
			frctl = new TrianguloSierpinsky(profundidad);
			break;
		default:
			break;
		}
		this.repaint();
	}

	public void paintComponent(Graphics gc) {
		super.paintComponent(gc);
		if (frctl != null)
			frctl.hacerFractal(gc);
	}
}
