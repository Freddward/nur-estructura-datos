package morphing1;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.Observable;




import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class Morphing1 extends Observable {
      private  Logger logger = Logger.getLogger(Panel12.class);
      private BufferedImage imagenIncio;
      private BufferedImage imagenFinal;
      private BufferedImage imagenIntermedia;
      private Cadena1<BufferedImage> cola;
     public Morphing1() {
         cola=new Cola1<BufferedImage>();
         imagenIncio = this.loadImage("gir1.jpg");
          logger.info("se cargo la imagen inicial");
         imagenFinal = this.loadImage("gir2.jpg");
          logger.info("se cargo la imagen final");
         imagenIntermedia = new BufferedImage(imagenIncio.getWidth(), imagenFinal.getHeight(), BufferedImage.TYPE_INT_RGB);
          logger.info("se hizo la imagen intermedia");
         cola.insertar(imagenIncio);
          logger.info("se agrego la imagen inicial a la cola");
         for (int y = 0; y < imagenIncio.getWidth(); y++) {
                  for (int x = 0; x < imagenFinal.getHeight(); x++) {
                   Color colorFigura1=new Color(imagenIncio.getRGB(y, x));
                   Color colorFigura2=new Color(imagenFinal.getRGB(y, x)); 
                   int red=(colorFigura1.getRed()+colorFigura2.getRed())/2;
                   int green=(colorFigura1.getGreen()+colorFigura2.getGreen())/2;
                   int blue=(colorFigura1.getBlue()+colorFigura2.getBlue())/2;
                   imagenIntermedia.setRGB(y, x, new Color(red,green,blue).getRGB());      
                  setChanged();
                  notifyObservers();
             }  
          }
            logger.info("se esta haciendo el algoritmo morphing");
          cola.insertar(imagenIntermedia);
            logger.info("se agrego la imagen intermedia a la cola");
         cola.insertar(imagenFinal);
            logger.info("se agrego la imagen final a la cola");
     }
     public Cadena1<BufferedImage> getCola() {
         return cola;
     }
     private BufferedImage loadImage(String string) {
          BufferedImage imagen = null;
         try {
             imagen = ImageIO.read(new File(string));
         } catch (Exception e) {
             e.printStackTrace();
         }
         return imagen;
     }
}
