/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Morphing;

import java.util.Iterator;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 * pppp
 * @author G & M
 */
public class Cadena<E> extends Observable{

    private static Logger logger = Logger.getLogger(Cadena.class);
    protected Node<E> raiz;

    public Cadena() {
        this.raiz = null;
    }

    public Node<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Node<E> raiz) {
        this.raiz = raiz;
    }

    public void insert(E obj) {
        Node<E> n = new Node<E>(obj);
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
        Node<E> actual = raiz;
        
        while (actual != null) {
            cont++;
            actual = actual.getSiguiente();
        }

        return cont;
    }

    static class IteratorCadena<E> implements Iterator<E> {

        private Node<E> actual;

        public IteratorCadena(Node<E> actual) {
            this.actual = actual;
        }

        public Node<E> getActual() {
            return actual;
        }

        public void setActual(Node<E> actual) {
            this.actual = actual;
        }

        @Override
        public boolean hasNext() {
            return (actual != null);
        }

        @Override
        public E next() {
            Node<E> NodeActual = actual;
            actual = actual.getSiguiente();
            return NodeActual.getContenedor();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    static public class Node<E> {

        private Node<E> next;
        private E Contenedor;

        public Node(E o) {
            next = null;
            Contenedor = o;
        }

        public E getContenedor() {
            return Contenedor;
        }

        public void setContenedor(E Contenedor) {
            this.Contenedor = Contenedor;
        }

        public Node<E> getSiguiente() {
            return next;
        }

        public void setSiguiente(Node<E> Siguiente) {
            this.next = Siguiente;
        }
    }
}
