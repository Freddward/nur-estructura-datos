package mandelbrot;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import java.net.URL;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Erwin
 */
public class MandelBrot
  extends JFrame
  implements MouseListener, MouseMotionListener
{
  private int maxColor = 521;
  private double zoomImagen = 180;
  private BufferedImage image;
  private double ZX, ZY, PX, PY, formula;
  private static Logger log = Logger.getLogger(MandelBrot.class);

  /**
   *
   */
  public MandelBrot()
  {
    super("MANDELBROT");
    this.setBounds(100, 100, 800, 600);
    this.DibujoMandelbrot();
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
  }

  /**
   *
   */
  public void DibujoMandelbrot()
  {

    log.info("Mandelbrot");
    try
    {
      image =
          new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

      for (int y = 0; y < getHeight(); y++)
      {
        for (int x = 0; x < getWidth(); x++)
        {
          ZX = 0;
          ZY = 0;
          PX = (x - 500) / zoomImagen;
          PY = (y - 300) / zoomImagen;
          int guardarColor = maxColor;
          while (ZX * ZX + ZY * ZY < 4 && guardarColor > 0)
          {
            formula = ZX * ZX - ZY * ZY + PX;
            ZY = 2.0 * ZX * ZY + PY;
            ZX = formula;
            guardarColor--;
          }
          image.setRGB(x, y, guardarColor * guardarColor);
        }
      }
    }
    catch (Exception err)
    {
      log.error("se a producido un error", err);
    }

  }


  /**
   *
   * @param g
   */
  public void paint(Graphics g)
  {
    g.drawImage(image, 0, 0, this);
  }

  /**
   *
   * @param args
   */
  public static void main(String[] args)
  {
    String resource = "/auditoria.properties";
    URL configFileResource;
    configFileResource = MandelBrot.class.getResource(resource);
    PropertyConfigurator.configure(configFileResource);

    new MandelBrot();

  }

  public void mouseClicked(MouseEvent e)
  {

    log.info("Entro al clicked");
    zoomImagen = zoomImagen * 2;
    this.DibujoMandelbrot();
    repaint();


  }

  public void mousePressed(MouseEvent e)
  {

  }

  public void mouseReleased(MouseEvent e)
  {
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }

  public void mouseDragged(MouseEvent e)
  {

  }

  public void mouseMoved(MouseEvent e)
  {
  }
}
