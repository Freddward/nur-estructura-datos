package Estructura;

public class Cola<E>
  extends Cadena<E>
{

  public Cola()
  {
    super();
  }

  public void push(E obj)
  {
    this.insertar(obj);
  }

  public E pop()
  {
    E obj = null;
    if (raiz.getSiguiente() == null)
    {
      obj = raiz.getValor();
      raiz = null;
    }
    else
    {
      Nodo<E> penultimo = raiz;
      while (penultimo.getSiguiente().getSiguiente() != null)
      {
        penultimo = penultimo.getSiguiente();
      }
      obj = penultimo.getSiguiente().getValor();
      penultimo.setSiguiente(null);
    }
    return obj;
  }
}


