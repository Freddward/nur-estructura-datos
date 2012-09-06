package ed.fractal.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase que resuelve y dibuja el Fractal Triangulo de Sierpinsky. Para hacerlo,
 * utiliza cuatro valores enteros que forman dos puntos X e Y que permiten
 * dibujar una recta. Mediante el metodo hacerTrianguloSierpinsky, y tomando
 * como referencia esos cuatro valores enteros, se obtienen otros cuatro valores
 * enteros que son los puntos X e Y que permiten determinar las otras dos rectas
 * para dibujar un triangulo equilatero, y utilizando la recursividad se dibuja
 * el Triangulo de Sierpinsky
 * 
 */
public class TrianguloSierpinsky extends Fractal {
	private static final double SIN60 = Math.sin(3.15 / 3.);

	/**
	 * Constructor de la clase TrianguloSierpinsky. Inicializa el valor entero
	 * profundidad al recibir como parametro un valor entero y asignarselo a la
	 * variable entera profundidad.
	 */
	public TrianguloSierpinsky(int profundidad) {
		this.profundidad = profundidad;
	}

	/**
	 * Resuelve el fractal Triangulo de Sierpinsky al llamar al metodo
	 * hacerTrianguloSierpinsky.
	 */
	public void hacerFractal(Graphics g) {
		hacerTrianguloSierpinsky(g, this.profundidad, 750, 750, 50, 750);
	}

	/**
	 * Metodo que permite dibujar recursivamente el fractal Triangulo de
	 * Sierpinsky
	 * 
	 * @param g
	 *            Objeto del tipo Graphics que utiliza metodos para trabajar con
	 *            graficos
	 * @param profundidad
	 *            Es la cantidad de veces que se quiere dibujar recursivamente
	 *            el triangulo.
	 * @param xp12
	 *            Valor x del Punto 1 de la linea recta
	 * @param yp12
	 *            Valor y del Punto 1 de la linea recta
	 * @param xp22
	 *            Valor x del Punto 2 de la linea recta
	 * @param yp22
	 *            Valor y del Punto 2 de la linea recta
	 */
	public void hacerTrianguloSierpinsky(Graphics g, int profundidad,
			double xp12, double yp12, double xp22, double yp22) {
		double dx = (xp22 - xp12) / 2.;
		double dy = (yp22 - yp12) / 2.;
		double xp32 = xp12 + dx - 2 * dy * SIN60;
		double yp32 = yp12 + dy + 2 * dx * SIN60;

		double dx1 = (xp22 + xp12) / 2.;
		double dy1 = (yp22 + yp12) / 2.;
		double dx2 = (xp32 + xp22) / 2.;
		double dy2 = (yp32 + yp22) / 2.;
		double dx3 = (xp12 + xp32) / 2.;
		double dy3 = (yp12 + yp32) / 2.;

		if (profundidad <= 0) {
			g.setColor(Color.WHITE);
			g.drawLine((int) xp12, (int) yp12, (int) xp22, (int) yp22);
			g.drawLine((int) xp22, (int) yp22, (int) xp32, (int) yp32);
			g.drawLine((int) xp32, (int) yp32, (int) xp12, (int) yp12);
		} else {
			hacerTrianguloSierpinsky(g, profundidad - 1, xp12, yp12, dx1, dy1);
			hacerTrianguloSierpinsky(g, profundidad - 1, dx1, dy1, xp22, yp22);
			hacerTrianguloSierpinsky(g, profundidad - 1, dx3, dy3, dx2, dy2);
		}

	}

}
