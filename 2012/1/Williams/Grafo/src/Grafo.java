
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
public class Grafo<E> {

    private Hashtable<String, Nodo<E>> nodos;
    public Grafo() {
        nodos = new Hashtable<String, Nodo<E>>();
    }

    public void addNodo(String id, E obj) {
        Nodo<E> n = new Nodo<E>(id, obj);
        nodos.put(id, n);
    }

    public void  conectar (String de, String A){
        Nodo <E> nde = nodos.get(de);
        Nodo <E> na = nodos.get(A);
        nde.addConexion(na);
    }
    public void conectarDoble(String de, String a) {
        conectar (de, a);
        conectar (a, de);
    }

    public Hashtable<String, Nodo<E>> getNodos() {
        return nodos;
    }
    
}