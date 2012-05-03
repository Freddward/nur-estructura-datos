package Logic;

import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Williams&&Erwin&&EdwardFC
 */
public class Nodo<E> {

    private E contain;
    private Hashtable<String, Nodo<E>> conections;
    private Vector<Integer> distance;
    private int stateDraw;
    private int x, y;
    private String id;

    public Nodo(String id, E obj) {
        conections = new Hashtable<String, Nodo<E>>();
        stateDraw = 0;
        distance = new Vector<Integer>();
        contain = obj;
        x = 0;
        y = 0;
        this.id = id;
    }

    public int getStateDraw() {
        return stateDraw;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setStateDraw(int EstadoDibujo) {
        this.stateDraw = EstadoDibujo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getArea() {
        return new Rectangle(x, y, 40, 40);
    }

    public void addConection(Nodo<E> n, int dist) {
        conections.put(n.getId(), n);
        distance.add(dist);
    }

    public Hashtable<String, Nodo<E>> getConexiones() {
        return conections;
    }

    public Vector<Integer> getDistancia() {
        return distance;
    }

    public E getContenido() {
        return contain;
    }

    public void setContenido(E contain) {
        this.contain = contain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}