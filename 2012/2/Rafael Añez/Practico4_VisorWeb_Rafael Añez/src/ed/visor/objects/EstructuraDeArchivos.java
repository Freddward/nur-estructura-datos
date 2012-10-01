package ed.visor.objects;

import java.io.File;
import java.util.List;
import java.util.Observable;

import org.apache.log4j.Logger;

/**
 * Clase que permite generar la estructura de archivos que se va a mostrar en la
 * pagina web que accede a la aplicacion. Utiliza una estructura de datos basada
 * en Arbol n-ario que permite, a partir de un nodo raiz, obtener el sistema de
 * archivos, asignandolos a los hijos de la raiz, y a los hijos de estos.
 * 
 * @author Rafael Anez
 * 
 */
public class EstructuraDeArchivos {
	private Node<Archivo> raiz;
	private Tree<Archivo> arbol;
	private String[] codigo = new String[100000];
	private int posicion;

	private static Logger logger = Logger.getLogger(EstructuraDeArchivos.class);

	/**
	 * Constructor de la clase EstructuraDeArchivos. Asigna el valor de 0 a la
	 * posicion que va a utilizar el arreglo codigo[]
	 */
	public EstructuraDeArchivos() {
		this.posicion = 0;
	}

	/**
	 * Metodo que genera el sistema de archivos. Utiliza una la ruta de la
	 * carpeta base, la cual se asigna al nodo raiz de la estructura de arbol, y
	 * partir de esta se genera toda la estructura del sistema de archivos.
	 * Tambien genera el codigo html de la raiz del arbol.
	 */
	public void generarSistemaDeArchivos() {
		String carpetaBase = "C://Users/Rafael Anez/Documents/Rafael-NUR/2012-2/estructura de datos/";
		logger.info("Generando sistema de archivos. Carpeta base: "
				+ carpetaBase);
		File f = new File(carpetaBase);
		if (f.exists()) {
			Archivo base = new Archivo(f);
			raiz = new Node<Archivo>(base);
			arbol = new Tree<Archivo>();
			arbol.setRootElement(raiz);
			logger.info("Asignando nodo raiz del sistema de archivos. Nombre: "
					+ arbol.getRootElement().getData().getNombre());

			generarHtml(arbol.getRootElement());
			logger.info("Generando estructura del sistema de archivos a partir de la carpeta base.");

			crearEstructura(carpetaBase, raiz);
			codigo[posicion] = "</ul>" + "\n</li>";
		} else {
			logger.error("La carpeta base no existe");
			codigo[posicion] = "<H1>La carpeta base no existe</H2>";
		}
	}

	/**
	 * Crea recursivamente la estructura del sistema de archivos
	 * 
	 * @param dir
	 *            Es la ruta del nodo padre a partir del cual se generaran los
	 *            nodos hijos
	 * @param n
	 *            Es el nodo padre
	 */
	void crearEstructura(String dir, Node<Archivo> n) {
		File[] files = new File(n.getData().getRuta()).listFiles();

		for (File f : files) {
			Archivo a = new Archivo(f);
			Node<Archivo> hijo = new Node<Archivo>(a);
			n.addChild(hijo);
			logger.debug("Creando nodo hijo de Nombre: "
					+ hijo.getData().getNombre() + ", y asignandolo al nodo: "
					+ n.getData().getNombre());
			generarHtml(hijo);
			if (a.isEsCarpeta()) {
				crearEstructura(dir + a.getNombre() + "/", hijo);
				codigo[posicion] = "</ul>" + "\n</li>";
				posicion++;
			}
		}
	}

	/**
	 * Genera el codigo html dependiendo si es la carpeta base, una carpeta o un
	 * archivo
	 * 
	 * @param n
	 *            Es el nodo del cual se va a obtener el codigo html
	 */
	public void generarHtml(Node<Archivo> n) {
		logger.info("Generando Html del nodo: " + n.getData().getNombre());

		if (n == arbol.getRootElement()) {
			codigo[posicion] = arbol.toHtml(n.getData().getNombre());
		} else if (n.getData().isEsCarpeta() == true) {
			codigo[posicion] = n.toHtml(n.getData().getNombre().toUpperCase())
					+ "\n<ul>";
		} else {
			codigo[posicion] = n.toHtml(n.getData().getNombre()) + "</li>";
		}

		posicion++;
	}

	/**
	 * Permite obtener un arreglo con el codigo html de toda la estructura del
	 * sistema de archivos
	 * 
	 * @return El codigo html
	 */
	public String[] getCodigo() {
		return codigo;
	}
}
