package ed.visor.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un nodo de la clase Tree<T>. El Node<T> tambien es un contenedor.
 * 
 */
public class Node<T> {

	public T data;
	public List<Node<T>> children;

	/**
	 * Constructor predeterminado.
	 */
	public Node() {
		super();
	}

	/**
	 * Constructor que crea un Node<T> recibiendo como parametro una instancia
	 * de T
	 * 
	 * @param data
	 *            Una instancia de T.
	 */
	public Node(T data) {
		this();
		setData(data);
	}

	/**
	 * Adiciona un hijo a la lista de hijos para este Node<T>. La adicion del
	 * primer hijo creara una nueva List<Node<T>>
	 * 
	 * @param child
	 *            El objeto Node<T> a asignar.
	 */
	public void addChild(Node<T> child) {
		if (children == null) {
			children = new ArrayList<Node<T>>();
		}
		children.add(child);
	}


	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Genera el codigo html de los nodos
	 * 
	 * @param nombre
	 *            Nombre del nodo del cual genera el html
	 * @return El codigo html en String
	 */
	public String toHtml(String nombre) {
		return "<li>" + nombre;
	}
}