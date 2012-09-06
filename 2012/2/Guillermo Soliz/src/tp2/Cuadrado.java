package tp2;

import java.awt.Color;
import java.awt.Graphics;

public class Cuadrado extends Fractal {
        /**
     * 
     * @param prof Indica las iteraciones que se haran
     */
	public Cuadrado(int prof) {
		this.profundidad = prof;
		this.nombre = "Sierpinksy";
	}
	
	public void dibujar(Graphics g) {
		hacerSierpinsky(10, 10, 300, 300, this.profundidad, g);
	}
        /**
         * 
         * @param x1 posicion para el tamaño del primer vertice del cuadrado en el eje de x
         * @param y1 posicion para el tamaño del primer vertice del cuadrado en el eje y
         * @param ancho posicion para el tamaño del ultimo vertice del cuadrado en el eje de x
         * @param alto posicion para el tamaño del ultimo vertice del cuadrado en el eje de y
         * @param n cuando esta en 1 pinta las lineas de las coordenadas y aumenta a 2 para hacer los calculos recursivos para las iteraciones
         * @param gc objeto generico de Graphics
         */
	public void hacerSierpinsky(int x1, int y1, int ancho, int alto, int n,
			Graphics gc) {
		int pAncho = ancho / 3;
		int pAlto = alto / 3;
		if (n == 1) {
                        gc.setColor(Color.BLUE.darker());
			gc.drawRect(x1, y1, ancho, alto);
			gc.drawRect(x1 + pAncho, y1 + pAlto, pAncho, pAlto);
		} // end of if (n == 1)
		else {
			/**
			 * ****************** 1 * 2 * 3 * 
			 * ****************** 4 *   * 5 *
			 * ****************** 6 * 7 * 8 * **************
			 */
			hacerSierpinsky(x1, y1, pAncho, pAlto, n - 1, gc);
			hacerSierpinsky(x1 + pAncho, y1, pAncho, pAlto, n - 1, gc);
			hacerSierpinsky(x1 + 2 * pAncho, y1, pAncho, pAlto, n - 1, gc);
			hacerSierpinsky(x1, y1 + pAlto, pAncho, pAlto, n - 1, gc); // 4
			hacerSierpinsky(x1 + 2 * pAncho, y1 + pAlto, pAncho, pAlto, n - 1, gc); // 5
			hacerSierpinsky(x1, y1 + 2 * pAlto, pAncho, pAlto, n - 1, gc); // 6
			hacerSierpinsky(x1 + pAncho, y1 + 2 * pAlto, pAncho, pAlto, n - 1, gc); // 7
			hacerSierpinsky(x1 + 2 * pAncho, y1 + 2 * pAlto, pAncho, pAlto,	n - 1, gc); // 8
		} // end of else

	}
}
