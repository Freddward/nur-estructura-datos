/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomorphing;

import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author Roberto A Pinto S
 */

public class Cadena<E> extends Observable{

    protected Nodo<E> raiz;

    public Cadena() {
        this.raiz = null;
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }

    public void insert(E obj) {
        Nodo<E> n = new Nodo<E>(obj);
        if (raiz == null) {
            raiz = n;
            return;
        } else {
            n.setSiguiente(raiz);
            raiz = n;
        }
    }

    public int size() {
        int cont = 0;
        Nodo<E> actual = raiz;
        while (actual != null) {
            cont++;
            actual = actual.getSiguiente();
        }
        return cont;
    }

    static class IteratorCadena<E> implements Iterator<E> {

        private Nodo<E> actual;

        public IteratorCadena(Nodo<E> actual) {
            this.actual = actual;
        }

        public Nodo<E> getActual() {
            return actual;
        }

        public void setActual(Nodo<E> actual) {
            this.actual = actual;
        }

        @Override
        public boolean hasNext() {
            return (actual != null);
        }

        @Override
        public E next() {
            Nodo<E> NodoActual = actual;
            actual = actual.getSiguiente();
            return NodoActual.getContenido();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    static public class Nodo<E> {

        private Nodo<E> siguiente;
        private E contenido;

        public Nodo(E o) {
            siguiente = null;
            contenido = o;
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contain) {
            this.contenido = contain;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> next) {
            this.siguiente = next;
        }
    }
}
