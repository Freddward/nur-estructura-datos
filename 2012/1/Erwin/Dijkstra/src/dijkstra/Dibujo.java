package dijkstra;

import dijkstra.Estructura.Grafo;
import dijkstra.Estructura.Nodo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Iterator;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 *
 * @Author Erwin Justiniano
 */
public class Dibujo
  extends JPanel
  implements MouseListener
{
  /**
   *
   */
  private Grafo grafo;
  private Image[] imagenes;
  private final static Logger logger = Logger.getLogger(Frame.class);

  /**
   *
   * @param gr
   */
  public Dibujo(Grafo gr)
  {
    this.grafo = gr;
    imagenes = new Image[4];
    imagenes[2] = Toolkit.getDefaultToolkit().getImage("spide.GIF");
    imagenes[1] = Toolkit.getDefaultToolkit().getImage("pared.JPG");
    imagenes[0] = Toolkit.getDefaultToolkit().getImage("piso.JPG");
    imagenes[3] = Toolkit.getDefaultToolkit().getImage("hulk.png");
    this.addMouseListener(this);
  }

  @Override
  public void paint(Graphics g)
  {
    int x1 = 0, y1 = 0;
    logger.info("Entro al metodo paint");
    for (int i = 0; i < 225; i++)
    {

      Nodo<Integer> n = grafo.getNodos().get("" + i);
      n.setX(x1);
      n.setY(y1);
      g.drawImage(imagenes[n.getEstadoDibujo()], n.getX(), n.getY(), this);
      x1 += 40;
      if ((n.getContenido() + 1) % 15 == 0)
      {
        x1 = 0;
        y1 += 40;
      }

    }
  }


  @Override
  public void mouseClicked(MouseEvent e)
  {
    logger.info("entro al mouseclicked");
    Iterator<Nodo<Integer>> i = grafo.getNodos().values().iterator();
    while (i.hasNext())
    {
      Nodo<Integer> n = i.next();
      if (n.getArea().contains(e.getPoint()))
      {
        if (n.getEstadoDibujo() == 0)
        {
          int cambio = 0;
          Iterator<Nodo<Integer>> i2 =
            grafo.getNodos().values().iterator();
          while (i2.hasNext())
          {
            Nodo<Integer> n2 = i2.next();
            if (n2.getEstadoDibujo() == 2)
            {
              cambio = n2.getContenido();
            }
          }

          n.setEstadoDibujo(2);
          grafo.getNodos().get("" + cambio).setEstadoDibujo(0);
        }
      }
    }
    repaint();
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
  }
}
