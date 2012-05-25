package Pantalla;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;


public class Morphing

{

  private BufferedImage imagenIntermedia;
  private int imagenSiguiente;
  private static Logger logger = Logger.getLogger(Morphing.class);

  public Morphing()
  {
    this.imagenIntermedia =
        new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    imagenSiguiente = 0;

  }


  public void setImagenIntermedia(BufferedImage imagenIntermedia)
  {
    this.imagenIntermedia = imagenIntermedia;
  }

  public BufferedImage getImagenIntermedia()
  {
    return imagenIntermedia;
  }

  public void setImagenSiguiente(int imagenSiguiente)
  {
    this.imagenSiguiente = imagenSiguiente;
  }

  public int getImagenSiguiente()
  {
    return imagenSiguiente;
  }


  public BufferedImage morphingImage(BufferedImage imagenInicial,
                                     BufferedImage imagenFinal, int pasos)
  {
    logger.info("la imagen esta por el paso " + pasos);
    setImagenIntermedia(new BufferedImage(200, 200,
                                          BufferedImage.TYPE_INT_RGB));
    for (int i = 0; i < imagenInicial.getWidth(); i++)
    {
      for (int j = 0; j < imagenFinal.getHeight(); j++)
      {
        Color img1Color = new Color(imagenInicial.getRGB(i, j));
        Color img2Color = new Color(imagenFinal.getRGB(i, j));
        int red =
          img1Color.getRed() + ((img2Color.getRed() - img1Color.getRed()) /
                                imagenSiguiente) * pasos;
        int green =
          img1Color.getGreen() + ((img2Color.getGreen() - img1Color.getGreen()) /
                                  imagenSiguiente) * pasos;
        int blue =
          img1Color.getBlue() + ((img2Color.getBlue() - img1Color.getBlue()) /
                                 imagenSiguiente) * pasos;
        imagenIntermedia.setRGB(i, j,
                                new Color(red, green, blue).getRGB());

      }
    }
    return imagenIntermedia;
  }


}


