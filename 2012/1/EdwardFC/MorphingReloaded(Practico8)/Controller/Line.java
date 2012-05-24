/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author EdwardFC
 */
public class Line<E> extends Chain<E> {

    public Line() {
        super();
    }

    public void push(E obj) {
        this.insert(obj);
    }

    public E pop() {
        E obj = null;
        if (root.getNext() == null) {
            obj = root.getContain();
            root = null;
        } else {
            Node<E> penultimate = root;
            while (penultimate.getNext().getNext() != null) {                
                penultimate = penultimate.getNext();
            }
            obj = penultimate.getNext().getContain();
            penultimate.setNext(null);
        }
        return obj;
    }
}
