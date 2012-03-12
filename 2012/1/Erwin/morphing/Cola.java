package morphing;

public class Cola<E>
  extends Cadena<E>
{

  public Cola()
  {
    super();
  }

  /**
   *
   * @param obj
   */
  public void push(E obj)
  {
    this.insertar(obj);
  }

  /**
   *
   * @return
   */
  public E pop()
  {
    E tope = raiz.getContenido();
    raiz = raiz.getSiguiente();
    return tope;
  }
}
