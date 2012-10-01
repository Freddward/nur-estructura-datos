/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.Iterator;

public class CadenaDoble<E> {
    protected Nodo<E> raiz;
    protected Nodo<E> cola;

    public CadenaDoble() {
        super();
        raiz = null;
        cola = null;
    }

    public void setRaiz(CadenaDoble.Nodo<E> raiz) {
        this.raiz = raiz;
    }

    public CadenaDoble.Nodo<E> getRaiz() {
        return raiz;
    }

    public void Insertar(E obj) {
        //implementar excepcion de que obj no puede ser nulo
        Nodo<E> n = new Nodo<E>(obj);
        if (raiz == null) {
            raiz = n;
            cola = raiz;
            return;
        }
        n.setSiguiente(raiz);
        raiz.setAnterior(n);
        raiz = n;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        if (raiz == null) {
            return "[VACIA]";
        }
        Nodo<E> actual = raiz;
        while (actual != null) {
            result.append(actual.toString());
            actual = actual.getSiguiente();
        }

        return result.toString();
    }

    public int tamaño() {
       if (raiz == null)
            return 0;
        int tam = 0;
        Iterator<E> i = this.iterator();
        int resul = 0;
        while(i.hasNext()){
            resul++;
            i.next();
        }
        return resul;
    }

    public Iterator<E> iterator() {
        return new IteradorCadena(raiz);
    }

    public void setCola(CadenaDoble.Nodo<E> cola) {
        this.cola = cola;
    }

    public CadenaDoble.Nodo<E> getCola() {
        return cola;
    }

    static class Nodo<E> {
        private Nodo<E> siguiente;
        private Nodo<E> anterior;
        private E contenido;

        public Nodo(E o) {
            contenido = o;
            siguiente = null;
            anterior = null;
        }

        public String toString() {
            return "(" + contenido + ") ->";
        }

        public void setSiguiente(CadenaDoble.Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }

        public CadenaDoble.Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public E getContenido() {
            return contenido;
        }

        public void setAnterior(CadenaDoble.Nodo<E> anterior) {
            this.anterior = anterior;
        }

        public CadenaDoble.Nodo<E> getAnterior() {
            return anterior;
        }
    }

    static class IteradorCadena<E> implements Iterator<E> {
        private Nodo<E> actual;

        public IteradorCadena(Nodo<E> nodo) {
            actual = nodo;
        }

        public boolean hasNext() {
            return (actual != null);
        }

        public E next() {
            Nodo<E> nodoActual = actual;
            actual = actual.getSiguiente();
            return nodoActual.getContenido();
        }

        public void remove() {
        }

        public void setActual(CadenaDoble.Nodo<E> actual) {
            this.actual = actual;
        }

        public CadenaDoble.Nodo<E> getActual() {
            return actual;
        }
    }

}
