package pacman;


import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;


public class Nodo<E> {

    private E contenido;
    private Hashtable<String, Nodo<E>> conexiones;
    private boolean visitado;
    private int estadoDibujo;
    private int x,y;
    private String id;
    public Nodo(String id, E obj) {
        conexiones = new Hashtable<String, Nodo<E>>();
        estadoDibujo=0;
        visitado=false;
        contenido = obj;
        x=0;y=0;
        this.id = id;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    public int getEstadoDibujo() {
        return estadoDibujo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEstadoDibujo(int EstadoDibujo) {
        this.estadoDibujo = EstadoDibujo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Rectangle getArea(){
        return new Rectangle(x,y,40,40);	
    }
    public void addConexion(Nodo<E> n) {
        conexiones.put(n.getId(), n);
       
    }

    public Hashtable<String, Nodo<E>> getConexiones() {
        return conexiones;
    }
    
    
    public E getContenido() {
        return contenido;
    }
    public void setContenido(E contenido) {
        this.contenido = contenido;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}