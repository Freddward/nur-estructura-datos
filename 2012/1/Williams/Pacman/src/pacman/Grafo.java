package pacman;

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
public class Grafo {

    private Hashtable<String, Nodo<Integer>> nodos;

    public Grafo() {
        nodos = new Hashtable<String, Nodo<Integer>>();
    }

    public int Tamaño() {
        int n = 0;
        Iterator<Nodo<Integer>> i = nodos.values().iterator();
        while (i.hasNext()) {
            n++;
        }
        return n;
    }

    public void addNodo(String id, int obj) {
        Nodo<Integer> n = new Nodo<Integer>(id, obj);
        nodos.put(id, n);
    }

    public void conectar(String de, String A) {
        Nodo<Integer> nde = nodos.get(de);
        Nodo<Integer> na = nodos.get(A);
        nde.addConexion(na);
    }

    public void conectarDoble(String de, String a) {
        conectar(de, a);
        conectar(a, de);
    }

    public Hashtable<String, Nodo<Integer>> getNodos() {
        return nodos;
    }
}