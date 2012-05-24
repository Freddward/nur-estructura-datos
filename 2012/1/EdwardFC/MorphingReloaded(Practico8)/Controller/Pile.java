/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author EdwardFC
 */
public class Pile <E> extends Chain<E> {
    public Pile() {
            super();
        }
        public void push (E obj){
            this.insert(obj);
        }
        public E pop (){
            E top = root.getContain();
            root = root.getNext();
            return top;
        }

    
}
