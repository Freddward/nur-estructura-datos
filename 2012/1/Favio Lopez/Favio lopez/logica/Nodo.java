package logica;

import java.util.Hashtable;

public class Nodo<E>
{
  private E contenido;
  private Hashtable <String, Nodo<E>> conexiones;
  private String id;
  public Nodo(String id, E obj)
  {
   contenido = obj;
   conexiones = new Hashtable<String, Nodo<E>>();
   this.id = id;
  }
  
  public void addConexion (Nodo<E> n)
  {
    conexiones.put(n.getId(), n);
  }

  public void setContenido(E contenido)
  {
    this.contenido = contenido;
  }

  public E getContenido()
  {
    return contenido;
  }

  public void setConexiones(Hashtable<String, Nodo<E>> conexiones)
  {
    this.conexiones = conexiones;
  }

  public Hashtable<String, Nodo<E>> getConexiones()
  {
    return conexiones;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getId()
  {
    return id;
  }
}
