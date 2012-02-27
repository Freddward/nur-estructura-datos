/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appsmandel;

import java.awt.image.BufferedImage;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author Eduardo
 */
public class Mandelbrot extends Observable {

    private int colorInicial = 1130;
    private double tamano = 270;
    private BufferedImage imgMandel;
    private double coordX, CoordY;
    private double zx, zy;
    private int movHor;
    private int movVer;
    private double tmp;
    private static Logger logger = Logger.getLogger(Mandelbrot.class);

    public Mandelbrot() {
        // movHor = 550;
        //movVer = 330;
        movHor = 400;
        movVer = 350;
       // imgMandel = new BufferedImage(800, 700, BufferedImage.TYPE_INT_RGB);

        inicializarMandel();
    }

    public void inicializarMandel() {
        for (int y = 0; y < 700; y++) {
            for (int x = 0; x < 800; x++) {
                zx = zy = 0;
                // Posicion "x" que permite colocar el fractal en posicion horizontal
                coordX = (x - movHor) / tamano;

                //Posicion "y" que permite colocar el fractal de posicion vertical
                CoordY = (y - movVer) / tamano;
                int clr = colorInicial;
                // son los puntos divergente
                while (zx * zx + zy * zy < 4 && clr > 0) {
                    tmp = zx * zx - zy * zy + coordX;
                    zy = 2.0 * zx * zy + CoordY;
                    zx = tmp;
                    clr--;
                }
                imgMandel.setRGB(x, y, clr );
            }
        }
    }

    public void setMovHor(int movHorizontal) {
        int var = 0;
        // int tam = (int) tamano ;

        if (movHorizontal <= 400) { // se asigna 400 por que es la mitad de la image en X
            var = 400 - movHorizontal;
            // this.movHor = movHor + var  + tam ;

            this.movHor = movHor + var;
            logger.info("Se recorrio el fractal hacia la Izquierda");

        } else {
            var = movHorizontal - 400;
            //this.movHor = movHor - var  - tam ;

            this.movHor = movHor - var;
            logger.info("Se recorrio el fractal hacia la Derecha");
            //this.setChanged();
            // this.notifyObservers();
        }


    }

    public void setMovVer(int movVertical) {
        int var = 0;
        //int tam = (int) tamano ;
        // System.out.println("movVEr "  + tam);
        if (movVertical <= 350) {//se asigna 350, por que es la mitad de la image en "y"
            var = 350 - movVertical;
            //this.movVer = movVer + var + tam ;

            this.movVer = movVer + var;
            logger.info("Se recorrio el fractal hacia arriba");

        } else {
            var = movVertical - 350;
            //this.movVer = movVer - var - tam ;

            this.movVer = movVer - var;
            logger.info("Se recorrio el fractal hacia abajo");
        }

    }

    public void setTamano(int x, int y) {
        this.tamano = tamano + 500;
        logger.info("El tamaño de la image es: " + tamano);
        setMovHor(x);
        setMovVer(y);

    }

    public BufferedImage getImgMandel() {
        return imgMandel;
    }
}
