package morphing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * @author ERWIN
 */
public class Morphing
  extends JPanel
{

  private BufferedImage imagenInicial;
  private BufferedImage imagenFinal;
  private BufferedImage imagenIntermedia;
  private BufferedImage mostrar;
  private Cola<BufferedImage> cola;

  private Logger log = Logger.getLogger(Pantalla.class);

  /**
   *
   */
  public Morphing()
  {
    
    cola = new Cola<BufferedImage>();
    imagenIntermedia = new BufferedImage(200,200, BufferedImage.TYPE_3BYTE_BGR);

    try
    {
      log.info("Entro a hacer la logica");
      imagenFinal = ImageIO.read(new File("image.jpg"));
      imagenInicial = ImageIO.read(new File("image3.jpg"));


      for (int i = 0; i < imagenInicial.getHeight(); i++)
      {
        for (int j = 0; j < imagenInicial.getWidth(); j++)
        {
          Color color1 = new Color(imagenInicial.getRGB(i, j));
          Color color2 = new Color(imagenFinal.getRGB(i, j));
          int red = (color1.getRed() + color2.getRed())/2 ;
          int green = (color1.getGreen() + color2.getGreen()) / 2;
          int blue = (color1.getBlue() + color2.getBlue()) / 2;
          imagenIntermedia.setRGB(i, j, new Color(red, green, blue).getRGB());

        }

      }
      cola.insertar(imagenIntermedia);
      cola.insertar(imagenFinal);
      cola.insertar(imagenInicial);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      log.error("Se a producido un error", e);
    }
  }

  /**
   *
   * @return
   */
  public Cadena<BufferedImage> getCola()
  {
    return cola;
  }

  /**
   *
   * @param img
   */

  public void agregarImage(BufferedImage img)
  {
    log.info("si la imagen no es nulla");
    if (img != null)
      mostrar = img;
    repaint();
  }

  /**
   *
   * @param g
   */
  public void paintComponent(Graphics g)
  {
    
    g.drawImage(mostrar, 50, 30, this);

  }

}


