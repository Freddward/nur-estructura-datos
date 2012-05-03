package dijkstra.Estructura;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;


public class Dijkstra
{
  private Grafo grafo;
  private int[] distancia;
  private int[] anterior;
  private Vector<Integer> recorrido;

  public Dijkstra(Grafo g)
  {
    grafo = g;
    distancia = new int[225];
    anterior = new int[225];
    Arrays.fill(distancia, 999);
    Arrays.fill(anterior, 0);
    this.distanciaMasCorta();
  }

  public Vector<Integer> getRecorrido(int de, int a)
  {
    recorrido = new Vector<Integer>();
    int fin = a;
    int o = 1;

    while (fin != de)
    {
      System.out.println(o);
      recorrido.add(fin);
      fin = anterior[fin];
    }
    return recorrido;
  }

  private void distanciaMasCorta()
  {

    Iterator<Nodo<Integer>> noVisitados =
      grafo.getNodos().values().iterator();
    while (noVisitados.hasNext())
    {
      Nodo<Integer> usare = noVisitados.next();
      Nodo<Integer> enUso = usare;
      Iterator<Nodo<Integer>> i =
        enUso.getConexiones().values().iterator();
      while (i.hasNext())
      {
        Nodo<Integer> n = i.next();
        int dist = 1;
        int distAnterior =
          distancia[anterior[enUso.getContenido()]] != 999?
          distancia[anterior[enUso.getContenido()]]: 0;
        distancia[n.getContenido()] =
            distancia[n.getContenido()] > (dist + distAnterior)?
            dist + distAnterior: distancia[n.getContenido()];
        anterior[n.getContenido()] = enUso.getContenido();
      }
    }

  }


}
