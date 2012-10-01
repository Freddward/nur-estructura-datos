/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import org.apache.log4j.Logger;

/**
 * 
 * @author G y M
 * Aqui creamos el arbol de nodos con cadena 
 */
public class Arbol<E> {

    private static Logger logger = Logger.getLogger(Arbol.class);
    private Nodo<E> raiz;
    private Recursividad recu;

    public Arbol() {
        super();


    }
    /**
     * @return nos devuelve la raiz
     */

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }
    /**
     * 
     * @return la raiz convertida a un String
     */

    public String toString() {
        logger.info("Transformando los datos de la cadena en String");
        if (raiz == null) {
            return "()";
        }
        return raiz.toString();

    }
    /**
     * 
     * @return nos devuelve el objeto de la cadena aqui lo recorremos para el llenado
     */
    public CadenaDoble<Nodo<E>> toList() {

        CadenaDoble<Nodo<E>> recorrer = new CadenaDoble<Nodo<E>>();
        leer(raiz, recorrer);
        return recorrer;
    }
/**
     * 
     * @param elemento donde inicializaremos para leer
     * @param recorrer objeto de cadena para la posicion 
     */
    private void leer(Nodo<E> elemento, CadenaDoble<Nodo<E>> recorrer) {
        logger.info("Barriendo el arbol");
        recorrer.Insertar(elemento);
        for (int i = 0; i < recu.getDescripcion().length; i++) {
            elemento.getHijo();
            leer(raiz, recorrer);
        }
    }

    public String toHtml(String nombre) {
        return "->" + nombre.toUpperCase();
    }
}
