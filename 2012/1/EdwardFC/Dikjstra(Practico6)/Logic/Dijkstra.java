/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author Williams&&Erwin&&EdwardFc
 */
public class Dijkstra {

    private Grafo graph;
    private int[] distance;
    private int[] last;
    private Vector<Integer> track;

    public Dijkstra(Grafo g) {
        graph = g;
        distance = new int[225];
        last = new int[225];
        Arrays.fill(distance, 999);
        Arrays.fill(last, 0);
        this.distanciaMasCorta();
    }

    public Vector<Integer> getRecorrido(int de, int a) {
        track = new Vector<Integer>();
        int fin = a;
        while (fin != de) {
            track.add(fin);
            fin = last[fin];
        }
        return track;
    }

    private void distanciaMasCorta() {

        Iterator<Nodo<Integer>> noVisitados = graph.getNodos().values().iterator();
        while (noVisitados.hasNext()) {
            Nodo<Integer> usare = noVisitados.next();
            Nodo<Integer> enUso = usare;
            Iterator<Nodo<Integer>> i = enUso.getConexiones().values().iterator();
            while (i.hasNext()) {
                Nodo<Integer> n = i.next();
                int dist = 1;
                int distAnterior = distance[last[enUso.getContenido()]] != 999 ? distance[last[enUso.getContenido()]] : 0;
                distance[n.getContenido()] = distance[n.getContenido()] > (dist + distAnterior) ? dist + distAnterior : distance[n.getContenido()];
                last[n.getContenido()] = enUso.getContenido();
            }
        }

    }
}
