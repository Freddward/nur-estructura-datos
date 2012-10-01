package ed.visor.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite representar un arbol de objetos genericos. El arbol es
 * representado por un elemento raiz que apunta a una lista de nodos hijos. No
 * hay restricciones en le numero de hijos que cada nodo en particular puede
 * tener.
 */
public class Tree<T> {

	private Node<T> rootElement;

	/**
	 * Constructor de la clase Tree<T>
	 */
	public Tree() {
		super();
	}

	/**
	 * Obtiene el nodo raiz del arbol
	 * 
	 * @return El elemento raiz.
	 */
	public Node<T> getRootElement() {
		return this.rootElement;
	}

	/**
	 * Asigna el elemento raiz del arbol
	 * 
	 * @param rootElement
	 *            El elemento raiz a asignar.
	 */
	public void setRootElement(Node<T> rootElement) {
		this.rootElement = rootElement;
	}


	/**
	 * Genera el codigo html del nodo raiz
	 * 
	 * @param nombre
	 *            Nombre del nodo del cual genera el html
	 * @return El codigo html en String
	 */
	public String toHtml(String nombre) {
		return "<li>" + nombre.toUpperCase() + "\n<ul>";
	}
}