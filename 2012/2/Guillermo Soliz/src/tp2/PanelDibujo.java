package tp2;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import org.apache.log4j.Logger;

public class PanelDibujo extends JPanel {
        private Logger logger= Logger.getLogger(PanelDibujo.class);
	private int ancho = 600;

	private int alto = 400;
	
	private static final long serialVersionUID = 1L;
	
	private Dibujo objeto;
	
	public PanelDibujo() {
		objeto = null;
		setPreferredSize(new Dimension(ancho, alto));
	}
        /**
         * 
         * @param nro Indica el numero del panel de cuadrado o triangulo
         * @param profundidad Le indica el numero de iteraciones a realizar
         */
	public void cambiarA(int nro, int profundidad) {
		if (nro == Fractal.CUADRADO)
			objeto = new Cuadrado(profundidad);
		else if (nro == Fractal.TRIANGULO)
                        objeto = new Triangulo(profundidad);
		this.repaint();
	}
	/**
         * 
         * @param gc es un objeto generico
         */
	public void paintComponent(Graphics gc) {
		super.paintComponent(gc);
		if (objeto != null)
			objeto.dibujar(gc);
	}
}
