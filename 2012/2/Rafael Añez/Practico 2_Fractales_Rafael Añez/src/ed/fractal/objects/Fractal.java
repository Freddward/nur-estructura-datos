package ed.fractal.objects;

import java.awt.Graphics;

/**
 * Clase abstracta para trabajar con fractales. Requiere que las clases que
 * hereden de Fractal implemente el metodo abstracto hacerFractal(Graphics g)
 * 
 */
public abstract class Fractal {
	protected int profundidad;
	public static int ALFOMBRA = 1;
	public static int TRIANGULO = 2;

	/**
	 * Metodo abstracto que hace un Fractal. Requiere ser implementado en las
	 * clases que hereden de Fractal.
	 * 
	 * @param g
	 *            Objeto del tipo Graphics que utiliza metodos para trabajar con
	 *            graficos
	 */
	public abstract void hacerFractal(Graphics g);

	/**
	 * Obtiene la profundidad del fractal
	 * 
	 * @return La cantidad de veces que se quiere hacer recursivamente un
	 *         fractal
	 */
	public int getProfundidad() {
		return profundidad;
	}

	/**
	 * Asigna un valor a la variable entera profundidad.
	 * 
	 * @param profundidad
	 *            La cantidad de veces que se quiere hacer recursivamente un
	 *            fractal
	 */
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
}
