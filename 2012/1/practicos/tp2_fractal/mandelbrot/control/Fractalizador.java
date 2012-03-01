package mandelbrot.control;

import mandelbrot.modelo.CuadroZoom;
import mandelbrot.modelo.Matriz;

import mandelbrot.vista.PanelDibujo;

import org.apache.log4j.Logger;

/**
 * Este control maneja el modelo y la vista.
 */
public class Fractalizador {
    
    private final static Logger logger = 
        Logger.getLogger(Fractalizador.class);

    /**
     * El modelo que este control debe manejar
     */
    private Matriz modelo;
    /**
     * La vista que se encarga de mostrar el modelo y los cambios que este 
     * control le mande hacer.
     */
    private PanelDibujo vista;

    /**
     * El constructor asigna el modelo y la vista al controlador
     * @param modelo
     * @param vista
     */
    public Fractalizador(Matriz objModelo, PanelDibujo objVista) {
        this.modelo = objModelo;
        this.vista = objVista;
        
        logger.info("asocia este control con el paneldibujo");
        this.vista.setControl(this);
        logger.info("adiciona el paneldibujo como observador del modelo");
        this.modelo.addObserver(this.vista);
    }
    
    /**
     * Hace el zoom segun el cuadro indicado en el objeto pasado como parametro
     * @param zoom
     */
    public void hacerZoom(CuadroZoom zoom) {
        double x0 = this.convertirCoordenadaMatriz(
                        zoom.getX0(), 
                        modelo.getAncho(), 
                        modelo.getR0(), modelo.getR1());
        double y0 = this.convertirCoordenadaMatriz(
                        zoom.getY0(),
                        modelo.getAlto(),
                        modelo.getI0(), modelo.getI1());
        double x1 = this.convertirCoordenadaMatriz(
                        zoom.getX1(), 
                        modelo.getAncho(), 
                        modelo.getR0(), modelo.getR1());
        double y1 = this.convertirCoordenadaMatriz(
                        zoom.getY1(),
                        modelo.getAlto(),
                        modelo.getI0(), modelo.getI1());
        logger.info("hace un zoom (" + zoom.getX0() + "," + zoom.getY0() +
                    ")-(" + zoom.getX1() + "," + zoom.getY1() + ") a (" +
                    x0 + "," + y0 + ")-(" + x1 + "," + y1 + ")");
        modelo.setCoordenadas(x0,y0,x1,y1);
        this.calcularMatrizImagen();
    }
    
    /**
     * Todo el calculo de la matriz de imagen mandelbrot que se visualizara.
     * Cuando se termina de calcular se marca el modelo como listo para que el
     * observador del modelo pueda dibujarlo.
     */
    public void calcularMatrizImagen() {
        double diferenciaR = modelo.getR1() - modelo.getR0();
        int ancho = modelo.getAncho();
        int alto = 
            (int)Math.abs((modelo.getI1() - modelo.getI0()) * (double)ancho / diferenciaR);
        modelo.setDimensionBuffer(modelo.getAncho(), alto);
        
        for(int i=0; i<ancho; i++) {
            for(int j=0; j<alto; j++) {
                double numReal = 
                    convertirCoordenadaMatriz(i, ancho, modelo.getR0(), modelo.getR1());
                double numImg =
                    convertirCoordenadaMatriz(j, alto, modelo.getI0(), modelo.getI1());
                int c = calcularMandelbrot(numReal, numImg, 255);
                
                modelo.setMatrizImagen(i, j, c);
            }
        }
        
        logger.info("Ha creado la matriz para mostrarla y llama al update de los observers");
        modelo.listo();
    }

    /**
     * Convierte una coordenada de entero a un double. La formula es:
     * r = eI + i * (eF - eI) / ancho
     * @param i La coordenada en forma de entero.
     * @param ancho 
     * @param extremoInicial 
     * @param extremoFinal
     * @return
     */
    private double convertirCoordenadaMatriz(int i, int ancho, 
                                             double extremoInicial, 
                                             double extremoFinal) {
        return extremoInicial + (double)i * (extremoFinal - extremoInicial) / (double)ancho;
    }

    /**
     * Calcula una iteracion de mandelbrot.
     * @param numReal
     * @param numImg
     * @param max El maximo de iteraciones que se van a realizar
     * @return
     */
    private int calcularMandelbrot(double numReal, double numImg, int max) {
        int numIter = 0;
        
        double[] z0 = new double[2];
        z0[0] = numReal;
        z0[1] = numImg;
        double[] zn = new double[2];
        zn[0] = numReal;
        zn[1] = numImg;
        
        double[] zn1 = new double[2];
        while(numIter < max && converge(zn)) {
            zn1[0] = zn[0]*zn[0] - zn[1]*zn[1] + z0[0];
            zn1[1] = 2*zn[0]*zn[1] + z0[1];
            
            zn[0] = zn1[0];
            zn[1] = zn1[1];
            numIter++;
        }
        
        return numIter;
    }

    /**
     * Solamente indica si el punto converge x^2 + y^2 < 4
     * @param zn
     * @return
     */
    private boolean converge(double[] zn) {
        return (zn[0]*zn[0] + zn[1]*zn[1]) <= 4;
    }
    
    public Matriz getModel() {
        return this.modelo;
    }
}
