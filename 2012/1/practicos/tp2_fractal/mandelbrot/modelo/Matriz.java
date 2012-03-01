package mandelbrot.modelo;

import java.util.Observable;

import org.apache.log4j.Logger;

/**
 * Representa a una matriz de reales e imaginarios. Se guarda el angulo superior
 * izquierdo (r0, i0) y el angulo inferior derecho (r1, i1).
 */
public class Matriz extends Observable {
    private final static Logger logger = 
        Logger.getLogger(CuadroZoom.class);
    
    private double r0, i0, r1, i1;
    
    /**
     * El ancho en pixeles, por defecto a 400
     */
    private int ancho;
    /**
     * El alto en pixeles, por defecto a lo que indiquen las coordenadas
     */
    private int alto;
    
    /**
     * La imagen que se muestra en el paneldibujo
     */
    private int[][] imagen;
    /**
     * La imagen sobre la que se dibuja mientras se calcula el mandelbrot
     */
    private int[][] buffer;
    
    /**
     * Constructor que coloca la matriz de mandelbrot a (-2,2)-(2,-2). El ancho
     * es de 400 pixeles
     */
    public Matriz() {
        reset();
    }
    
    /**
     * Cuando se cambian las coordenadas de vision del mandel brot se
     * avisa a la vista que es un observer de este modelo.
     * @param nuevoR0
     * @param nuevoI0
     * @param nuevoR1
     * @param nuevoI1
     */
    public void setCoordenadas(double nuevoR0, double nuevoI0, 
                               double nuevoR1, double nuevoI1) {
        r0 = nuevoR0;
        i0 = nuevoI0;
        
        r1 = nuevoR1;
        i1 = nuevoI1;
    }
    
    /**
     * Hace el cambio de la matriz de imagen por la imagen quee esta en el buffer
     * Es importante que este metodo no se llame sino una vez que el fractal ha
     * sido calculado.
     */
    public void listo() {
        this.ancho = buffer.length;
        this.alto = buffer[0].length;
        
        imagen = buffer;
        
        logger.info("Anuncia del cambio de imagen por buffer a los observadores");
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Cuando se cambian las coordenadas de vision del mandel brot se calcula
     * el nuevo ancho y alto.
     * @param x
     * @param y
     */
    public void setDimensionBuffer(int x, int y) {
        logger.info("Crea un nuevo buffer lo que significa que se calculara de nuevo");
        this.buffer = new int[x][y];
    }

    public double getR0() {
        return r0;
    }

    public double getI0() {
        return i0;
    }

    public double getR1() {
        return r1;
    }

    public double getI1() {
        return i1;
    }
    
    public int getAncho() {
        return this.ancho;
    }
    
    public int getAlto() {
        return this.alto;
    }

    public void setMatrizImagen(int i, int j, int c) {
        this.buffer[i][j] = c;
    }
    
    public int getMatriz(int i, int j) {
        return this.imagen[i][j];
    }
    
    /**
     * Vuelve todo a lsa coordenadas iniciales.
     */
    public void reset() {
        logger.info("Vuelve todo a los valores por defecto 400x400");
        r0 = -1.5;
        i0 = 1.0;
        
        r1 = 0.5;
        i1 = -1.0;
        
        ancho = 400;
        alto = 400;
        imagen = new int[400][400];
        buffer = new int[400][400];
    }
}
