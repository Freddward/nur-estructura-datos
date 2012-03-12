/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomorphing;

/**
 *
 * @author Roberto A Pinto S
 */

public class Pila<E> extends Cadena<E> {

    public Pila() {
        super();
    }

    public void push(E obj) {
        this.insert(obj);
    }

    public E pop() {
        E obj = null;
        if (raiz.getSiguiente() == null) {
            obj = raiz.getContenido();
            raiz = null;
        } else {
            Nodo<E> penultimo = raiz;
            while (penultimo.getSiguiente().getSiguiente() != null) {                
                penultimo = penultimo.getSiguiente();
            }
            obj = penultimo.getSiguiente().getContenido();
            penultimo.setSiguiente(null);
        }
        return obj;
    }
}
