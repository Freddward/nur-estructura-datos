/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *






/
package morphing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Williams
 */
public class Morphing  {

   




  private BufferedImage imageIncio;
     private BufferedImage imagenFinal;
     private BufferedImage imagenIntermedia;
     private Cola<BufferedImage> cola;
    public Morphing(int fase) {
  



      cola=new Cola<BufferedImage>();
        imageIncio = this.loadImage("Bosque Solitario.jpeg");
        imagenFinal = this.loadImage("Bosque y Lago.jpeg");
     

   imagenIntermedia = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        cola.insertar(imageIncio);
        int fases=fase+1;
             for (int i = 1; i < fases; i++) {
            for (int y = 0; y < 800; y++) {
                     for (int x = 0; x < 600; x++) {
                      Color colorF1=













new Color(imageIncio.getRGB(y, x));
                      Color colorF2=new Color(imagenFinal.getRGB(y, x)); 
                     
 int red=colorF1.getRed()+((colorF2.getRed()-colorF1.getRed())/fases)*i; 
                      int green=colorF1.getGreen()+((colorF2.getGreen()-colorF1.getGreen())/fases)*i;
                      int blue=colorF1.getBlue()+((colorF2.getBlue()-colorF1.getBlue())/fases)*i;
                      imagenIntermedia.setRGB(y, x, new Color(red,green,blue).getRGB());             
                }  
            }
            cola.insertar(imagenIntermedia);
        }  
        cola.insertar(imagenFinal);
    }
    public Cola<BufferedImage> getCola() {
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
