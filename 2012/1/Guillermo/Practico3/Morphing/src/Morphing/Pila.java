/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Morphing;

import org.apache.log4j.Logger;

/**
 *
 * @author G & M
 */
public class Pila<E> extends Cadena<E> {

    private static Logger logger = Logger.getLogger(Pila.class);
    
    public Pila() {
        super();
    }

    public void push(E obj) {
        this.insert(obj);
    }

    public E pop() {
        E obj = null;
        if (raiz.getSiguiente() == null) {
            obj = raiz.getContenedor();
            raiz = null;
        } else {
            Node<E> penultimo = raiz;
            while (penultimo.getSiguiente().getSiguiente() != null) {                
                penultimo = penultimo.getSiguiente();
            }
            obj = penultimo.getSiguiente().getContenedor();
            penultimo.setSiguiente(null);
        }
        return obj;
    }
}
