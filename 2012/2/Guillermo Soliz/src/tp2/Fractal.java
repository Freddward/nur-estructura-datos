package tp2;

public abstract class Fractal implements Dibujo {

	public static final int CUADRADO = 1;
        public static final int TRIANGULO = 2;

	protected int profundidad;
	protected String nombre;
	/**
	 * @return Returns retorna la profundidad.
	 */
	public int getProfundidad() {
		return profundidad;
	}
	/**
	 * @param profundidad Indica cuantas iteraciones hara.
	 */
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	/**
	 * @return Returns el nombre.
	 */
	public String getNombre() {
		return nombre;
	}
}
