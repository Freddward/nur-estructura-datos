/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gato;

import java.util.Iterator;

/**
 *
 * @author Williams
 */


public class CadenaDoble {
    protected Nodo raiz;
    protected Nodo cola;

    public CadenaDoble() {
        super();
        raiz = null;
        cola = null;
    }

    public void setRaiz(CadenaDoble.Nodo raiz) {
        this.raiz = raiz;
    }

    public CadenaDoble.Nodo getRaiz() {
        return raiz;
    }

    public void Insertar(int [][] obj) {
        //implementar excepcion de que obj no puede ser nulo
        Nodo  n = new Nodo(obj);
        if (raiz == null) {
            raiz = n;
            cola = raiz;
            return;
        }
        n.setSiguiente(raiz);
        raiz.setAnterior(n);
        raiz = n;
    }
    
    public Iterator iterator() {
        return new IteradorCadena(raiz);
    }

    public void setCola(CadenaDoble.Nodo cola) {
        this.cola = cola;
    }

    public CadenaDoble.Nodo getCola() {
        return cola;
    }

    static class Nodo {
        private Nodo siguiente;
        private Nodo anterior;
        private int [][] contenido;

        public Nodo(int [][] o) {
            contenido = o;
            siguiente = null;
            anterior = null;
        }


        public void setSiguiente(CadenaDoble.Nodo siguiente) {
            this.siguiente = siguiente;
        }

        public CadenaDoble.Nodo getSiguiente() {
            return siguiente;
        }

        public void setContenido(int[][] contenido) {
            this.contenido = contenido;
        }

        public int[][] getContenido() {
            return contenido;
        }

        public void setAnterior(CadenaDoble.Nodo anterior) {
            this.anterior = anterior;
        }

        public CadenaDoble.Nodo getAnterior() {
            return anterior;
        }
    }

    static class IteradorCadena implements Iterator{
        private Nodo actual;

        public IteradorCadena(Nodo nodo) {
            actual = nodo;
        }

        public boolean hasNext() {
            return (actual != null);
        }

        public int[][] next() {
            Nodo nodoActual = actual;
            actual = actual.getSiguiente();
            return nodoActual.getContenido();
        }

        public void remove() {
        }

        public void setActual(CadenaDoble.Nodo actual) {
            this.actual = actual;
        }

        public CadenaDoble.Nodo getActual() {
            return actual;
        }
    }

}
