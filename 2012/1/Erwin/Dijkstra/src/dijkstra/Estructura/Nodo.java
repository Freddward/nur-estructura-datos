package dijkstra.Estructura;

import java.awt.Rectangle;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @param <E>
 * @Author Erwin Justiniano
 */
public class Nodo<E>
{

  private E contenido;
  private Hashtable<String, Nodo<E>> conexiones;
  private Vector<Integer> distancia;
  private int estadoDibujo;
  private int x, y;
  private String id;

  public Nodo(String id, E obj)
  {
    conexiones = new Hashtable<String, Nodo<E>>();
    estadoDibujo = 0;
    distancia = new Vector<Integer>();
    contenido = obj;
    x = 0;
    y = 0;
    this.id = id;
  }

  public int getEstadoDibujo()
  {
    return estadoDibujo;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public void setEstadoDibujo(int EstadoDibujo)
  {
    this.estadoDibujo = EstadoDibujo;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public Rectangle getArea()
  {
    return new Rectangle(x, y, 40, 40);
  }

  public void addConexion(Nodo<E> n, int dist)
  {
    conexiones.put(n.getId(), n);
    distancia.add(dist);
  }

  public Hashtable<String, Nodo<E>> getConexiones()
  {
    return conexiones;
  }

  public Vector<Integer> getDistancia()
  {
    return distancia;
  }

  public E getContenido()
  {
    return contenido;
  }

  public void setContenido(E contenido)
  {
    this.contenido = contenido;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }
}
