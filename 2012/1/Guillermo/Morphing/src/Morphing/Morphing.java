package Morphing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author G & M
 */
public class Morphing extends Observable {

    private static Logger logger = Logger.getLogger(Morphing.class);
    private BufferedImage imagenActual;
    private int pasos;

    public Morphing() {
        this.imagenActual = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        pasos = 0;

    }

    public void setImagenActual(BufferedImage imagenActual) {
        this.imagenActual = imagenActual;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public int getPasos() {
        return pasos;
    }

    public BufferedImage morphingImagen(BufferedImage imageInitial, BufferedImage imageFinal, int tamaño) {
        /*int ini = 0;
        int img = 0;
        int fin = 0;*/
        //System.out.println(pasos);
        setImagenActual(new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB));
        for (int i = 0; i < imageInitial.getWidth(); i++) {
            for (int j = 0; j < imageFinal.getHeight(); j++) {
                 Color colorImagenInicial = new Color(imageInitial.getRGB(i, j));
                Color colorImagenFinal = new Color(imageFinal.getRGB(i, j));
                
                int red = colorImagenInicial.getRed() + ((colorImagenFinal.getRed() - colorImagenInicial.getRed()) / pasos) * tamaño;
                int green = colorImagenInicial.getGreen() + ((colorImagenFinal.getGreen() - colorImagenInicial.getGreen()) / pasos) * tamaño;
                int blue = colorImagenInicial.getBlue() + ((colorImagenFinal.getBlue() - colorImagenInicial.getBlue()) / pasos) * tamaño;
                imagenActual.setRGB(i, j, new Color(red, green, blue).getRGB());
                setChanged();
                notifyObservers();
            }
        }
    //    System.out.println(k);
    //    System.out.println("Ya esta listo");
        return imagenActual;
    }
}
    