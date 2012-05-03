package Logic;

import java.util.Hashtable;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Williams&&Erwin&&EdwardFC
 */
public class Grafo {

    private Hashtable<String, Nodo<Integer>> nodos;

    public Grafo() {
        nodos = new Hashtable<String, Nodo<Integer>>();
    }

    public int tam() {
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

    public void conect(String de, String A, int distancia) {
        Nodo<Integer> nde = nodos.get(de);
        Nodo<Integer> na = nodos.get(A);
        nde.addConection(na, distancia);
    }

    public void conectDouble(String de, String a, int distancia) {
        conect(de, a, distancia);
        conect(a, de, distancia);
    }

    public Hashtable<String, Nodo<Integer>> getNodos() {
        return nodos;
    }
}