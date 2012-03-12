/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomorphing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Observable;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 *
 * @author Roberto A Pinto S
 */
public class Morphing extends Observable {

    private static Logger logger = Logger.getLogger(Morphing.class);
    private BufferedImage imagenActual;
    private int saltos;
    
    public Morphing() {
        this.imagenActual = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        saltos = 0;
        logger.info("Crea una nueva imagen que es la imagen actual");
    }
    

    public void setImageActual(BufferedImage imagenActual) {
        this.imagenActual = imagenActual;
        logger.info("Modificamos la Imagen atravez de este metodo");
    }

    public void setSaltos(int saltos) {
        this.saltos = saltos;
    }

    public int getSaltos() {
        return saltos;
    }

    public BufferedImage morphingImage(BufferedImage imageInicial, BufferedImage imagenFinal, int tam) {
       
        
        setImageActual(new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB));
        
        for (int i = 0; i < imageInicial.getWidth(); i++) {
            for (int j = 0; j < imagenFinal.getHeight(); j++) {
                Color img1=new Color(imageInicial.getRGB(i, j));
                Color img2=new Color(imagenFinal.getRGB(i, j));
                
                                
                
               int redValue = img1.getRed() +((img2.getRed() - img1.getRed())/(saltos))*tam; 
               int  greenValue = img1.getGreen() +((img2.getGreen() - img1.getGreen())/(saltos))*tam; 
               int blueValue = img1.getBlue() +((img2.getBlue() - img1.getBlue())/(saltos))*tam; 

               imagenActual.setRGB(i, j,new Color(redValue, greenValue, blueValue).getRGB());
                
               
            }
        }
                setChanged();
                notifyObservers();
        return imagenActual;
        //logger.info("metemos los pixeles de las otras imagenes a la imagen actual");
    }
}

