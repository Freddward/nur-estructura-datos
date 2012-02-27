/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 *
 * @author Williams
 */
public class Mandelbrot extends Observable {
 
    private static Logger logger = Logger.getLogger(Mandelbrot.class);
    private  int ejeX, ejeY;
    private final int colorFijo=570;
    private  double tamaño=150;
    private BufferedImage imagenMandelbrot;
    private double xc,yc,zx,zy,tmp;
    public Mandelbrot(){
        
        imagenMandelbrot = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        construccionMandelbrot();
    }
    public void construccionMandelbrot(){
       
         for (int y = 0; y < 600; y++) {
            for (int x = 0; x < 800; x++) {
                zx = zy = 0;
                xc = (x - (500+ejeX))/tamaño;// posicion
                yc = (y - (300+ejeY)) / tamaño;//posicion
                int color = colorFijo;
                while (zx * zx + zy * zy < 4 && color > 0) {//se ve cuales son los puntos divergente
                    tmp = zx * zx - zy * zy + xc;
                    zy = 2.0 * zx * zy + yc;
                    zx = tmp;
                    color--;
                }
                imagenMandelbrot.setRGB(x, y, color);
                
            }
        }
    }
    public void setTamaño() {
        this.tamaño+= 300;
        logger.info("Aumento el tamaño");
    }
    
    public void setEjeX(int ejeX) {
        this.ejeX+= ejeX;
    }

    public void setEjeY(int ejeY) {
        this.ejeY+= ejeY;
    }
    
    public BufferedImage getImagenMandelbrot() {
      
        return imagenMandelbrot;
        
    }

   
   
    
    
    
    
}
