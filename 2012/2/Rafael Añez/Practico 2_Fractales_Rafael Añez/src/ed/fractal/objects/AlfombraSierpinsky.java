package ed.fractal.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que resuelve y dibuja el Fractal Alfombra de Sierpinsky. Para ello
 * utiliza dos valores enteros como puntos X e Y para empezar a dibujar un
 * cuadrado, los valores enteros ancho y alto del cuadrado, y el valor entero
 * profundidad que determina la cantidad de veces que se quiere dibujar
 * recursivamente el cuadrado.
 * 
 */
public class AlfombraSierpinsky extends Fractal {

	/**
	 * Constructor de la clase AlfombraSierpinsky. Inicializa el valor entero
	 * profundidad al recibir como parametro un valor entero y asignarselo a la
	 * variable entera profundidad.
	 */
	public AlfombraSierpinsky(int profundidad) {
		this.profundidad = profundidad;
	}

	/**
	 * Resuelve el fractal Alfombra de Sierpinsky al llamar al metodo
	 * hacerAlfombraSierpinsky.
	 */
	public void hacerFractal(Graphics g) {
		hacerAlfombraSierpinsky(0, 0, 799, 799, this.profundidad, g);
	}

	/**
	 * Metodo que permite dibujar recursivamente el fractal Alfombra de
	 * Sierpinsky.
	 * 
	 * @param puntoX1
	 *            Es el punto del eje de coordenadas x donde se empieza a
	 *            dibujar el cuadrado
	 * @param puntoY1
	 *            Es el punto del eje de coordenadas y donde se empieza a
	 *            dibujar el cuadrado
	 * @param anchoCuadrado
	 *            Es el ancho del cuadrado
	 * @param altoCuadrado
	 *            Es el alto del cuadrado
	 * @param profundidad
	 *            Es la cantidad de veces que se quiere dibujar recursivamente
	 *            el cuadrado
	 * @param gc
	 *            Objeto del tipo Graphics que permite trabajar con graficos
	 */
	public void hacerAlfombraSierpinsky(int puntoX1, int puntoY1,
			int anchoCuadrado, int altoCuadrado, int profundidad, Graphics gc) {
		if (profundidad >= 0) {
			try {
				int pAncho = anchoCuadrado / 3;
				int pAlto = altoCuadrado / 3;
				if (profundidad == 0) {
					gc.setColor(Color.WHITE);
					gc.drawRect(puntoX1, puntoY1, anchoCuadrado, altoCuadrado);
					gc.setColor(Color.RED);
					gc.drawRect(puntoX1 + pAncho, puntoY1 + pAlto, pAncho,
							pAlto);
				} // end of if (n == 1)
				else {
					/**
					 * ****************** 1 * 2 * 3 * ****************** 4 * * 5
					 * * ****************** 6 * 7 * 8 * **************
					 */
					hacerAlfombraSierpinsky(puntoX1, puntoY1, pAncho, pAlto,
							profundidad - 1, gc);
					hacerAlfombraSierpinsky(puntoX1 + pAncho, puntoY1, pAncho,
							pAlto, profundidad - 1, gc);
					hacerAlfombraSierpinsky(puntoX1 + 2 * pAncho, puntoY1,
							pAncho, pAlto, profundidad - 1, gc);
					hacerAlfombraSierpinsky(puntoX1, puntoY1 + pAlto, pAncho,
							pAlto, profundidad - 1, gc); // 4
					hacerAlfombraSierpinsky(puntoX1 + 2 * pAncho, puntoY1
							+ pAlto, pAncho, pAlto, profundidad - 1, gc); // 5
					hacerAlfombraSierpinsky(puntoX1, puntoY1 + 2 * pAlto,
							pAncho, pAlto, profundidad - 1, gc); // 6
					hacerAlfombraSierpinsky(puntoX1 + pAncho, puntoY1 + 2
							* pAlto, pAncho, pAlto, profundidad - 1, gc); // 7
					hacerAlfombraSierpinsky(puntoX1 + 2 * pAncho, puntoY1 + 2
							* pAlto, pAncho, pAlto, profundidad - 1, gc); // 8

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
