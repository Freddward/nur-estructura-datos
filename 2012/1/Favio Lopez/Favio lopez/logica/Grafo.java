package logica;

import java.util.Hashtable;

public class Grafo<E>
{
  private Hashtable<String, Nodo<E>> nodos;
  public Grafo()
  {
   this.nodos = new Hashtable<String, Nodo<E>>();
  }
  
  public void addNodo(String id, E obj)
  {
    Nodo<E> n = new Nodo<E>(id,obj);
    nodos.put(id,n);
  }
  
  public void conectar(Clase de, Clase a)
  {
    Nodo<E> nde = nodos.get(de);
    Nodo<E> na = nodos.get(a);
    
    nde.addConexion(na);
  }
}
