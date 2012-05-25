package Estructura;

public class Pila<E>
  extends Cadena<E>
{
  public Pila()
  {
    super();
  }

  public void push(E obj)
  {
    this.insertar(obj);
  }

  public E pop()
  {
    E top = raiz.getValor();
    raiz = raiz.getSiguiente();
    return top;
  }


}
