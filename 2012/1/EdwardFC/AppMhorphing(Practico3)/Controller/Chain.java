/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.ScreenMain;
import java.util.Iterator;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class Chain<E> extends Observable{

    protected Node<E> root;
    private static Logger logger = Logger.getLogger(ScreenMain.class);

    public Chain() {
        this.root = null;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public void insert(E obj) {
        Node<E> n = new Node<E>(obj);
        if (root == null) {
            root = n;
            return;
        } else {
            n.setNext(root);
            root = n;
        }
        logger.info("Se insertó un nuevo nodo");
    }

    public int size() {
        int cont = 0;
        Node<E> actual = root;
        while (actual != null) {
            cont++;
            actual = actual.getNext();
        }
        return cont;
    }

    static class IteratorChain<E> implements Iterator<E> {

        private Node<E> actual;

        public IteratorChain(Node<E> actual) {
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
            actual = actual.getNext();
            return NodeActual.getContain();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    static public class Node<E> {

        private Node<E> next;
        private E contain;

        public Node(E o) {
            next = null;
            contain = o;
        }

        public E getContain() {
            logger.info("Se extrajo el contenido del nodo");
            return contain;
        }

        public void setContain(E contain) {
            this.contain = contain;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
