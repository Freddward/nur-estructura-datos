package logica;

import java.awt.Graphics;

import java.awt.Point;

import java.util.Iterator;
import java.util.Vector;

public class Diagrama extends Dibujo
{
  Grafo <Clase> g = new Grafo<Clase>();
  int x = 50;
  int y = 20;
  private Vector <Clase> clases = new Vector <Clase>();
  
  public void anadir (Clase obj){
    if (obj!= null){
       obj.setX(x);
       obj.setY(y);
       clases.addElement(obj);
       if (x<300){
        x += Clase.ANCHO + Clase.SEPARACION; 
      }
      else{
        x = 50; y += 80;
     }
    }
  }

  public void dibujar(Graphics g) {
    Iterator <Clase> it = clases.iterator();
    while (it.hasNext()){
     it.next().dibujar(g);
   }
  }
  
  public Clase buscar (Point ubicacion){
     
    for (int i = 0; i < clases.size(); i++)  {
        Clase aux = clases.get(i);
        
        if (aux.getArea().contains(ubicacion)){
                      
          return aux;
      }
      
    }
    return null;
  }
}
