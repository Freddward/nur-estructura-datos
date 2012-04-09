
import java.util.Hashtable;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Williams
 */
public class Nodo<E> {

    private E contenido;
    private Hashtable<String, Nodo<E>> conexiones;
    private String id;
    public Nodo(String id, E obj) {
        conexiones = new Hashtable<String, Nodo<E>>();
        contenido = obj;
        this.id = id;
    }
    public void addConexion(Nodo<E> n) {
        conexiones.put(n.getId(), n);
    }
    public Hashtable<String, Nodo<E>> getConexiones() {
        return conexiones;
    }
    public void setConexiones(Hashtable<String, Nodo<E>> conexiones) {
        this.conexiones = conexiones;
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