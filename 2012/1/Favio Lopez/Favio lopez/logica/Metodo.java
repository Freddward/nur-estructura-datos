package logica;

import java.awt.Graphics;

import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;

public class Metodo extends Dibujo
{
  private Logger logger = Logger.getRootLogger();
  
  private String nombre;
  private  Vector <Parametro> parametros;
  
  public Metodo()
  {
    parametros = new Vector<Parametro>();
  }
  
  public boolean existeParametro(String nombre)
  {
    Iterator <Parametro> it = parametros.iterator();
    while (it.hasNext())
    {
      Parametro p = it.next();
      if (nombre.equals(p.getNombre()))
      {
       logger.info("existe paramatros en el metodo");  
       return true;
       
      }
    }
    logger.info("No existen parametros en metodo");
    return false;
    
  }
  
  public void agregarParametro (Parametro p)
  {
    if (!existeParametro(p.getNombre()))
    {
      parametros.addElement(p);
      logger.info("parametro agregado");
    }
  }

  public void dibujar(Graphics g)
  {
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }

  public String getNombre()
  {
    return nombre;
  }

  public void setParametros(Vector<Parametro> parametros)
  {
    this.parametros = parametros;
  }

  public Vector<Parametro> getParametros()
  {
    return parametros;
  }
}
