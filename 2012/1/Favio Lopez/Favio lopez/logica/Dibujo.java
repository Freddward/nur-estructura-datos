package logica;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Dibujo
{
  protected int x, y;
  protected Rectangle area = new Rectangle ();
  
  public abstract void dibujar(Graphics g);


    public void setX(int x) {
        this.x = x;
        area.x=x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
        area.y=y;
    }

    public int getY() {
        return y;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public Rectangle getArea() {
        return area;
    }
}
