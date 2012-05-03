package dijkstra.Estructura;

import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @Author Erwin Justiniano
 */
public class Grafo
{

  private Hashtable<String, Nodo<Integer>> nodos;

  public Grafo()
  {
    nodos = new Hashtable<String, Nodo<Integer>>();
  }

  public int Tamano()
  {
    int n = 0;
    Iterator<Nodo<Integer>> i = nodos.values().iterator();
    while (i.hasNext())
      n++;
    return n;
  }

  public void addNodo(String id, int obj)
  {
    Nodo<Integer> n = new Nodo<Integer>(id, obj);
    nodos.put(id, n);
  }

  public void conectar(String de, String A, int distancia)
  {
    Nodo<Integer> nde = nodos.get(de);
    Nodo<Integer> na = nodos.get(A);
    nde.addConexion(na, distancia);
  }

  public void conectarDoble(String de, String a, int distancia)
  {
    conectar(de, a, distancia);
    conectar(a, de, distancia);
  }

  public Hashtable<String, Nodo<Integer>> getNodos()
  {
    return nodos;
  }

}
