package mandelbrot.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import mandelbrot.MandelbrotViewer;

import mandelbrot.modelo.CuadroZoom;

import mandelbrot.vista.PanelDibujo;

import org.apache.log4j.Logger;

/**
 * Esta clase se encarga de manejar todos los efectos que ocurran con el raton
 * sobre la superficie a dibujar
 */
public class ControlMouse implements MouseListener, MouseMotionListener {
    
    private final static Logger logger = 
        Logger.getLogger(ControlMouse.class);
    
    /**
     * La relacion con el objeto que debe manejar
     */
    private CuadroZoom zoom;
    /**
     * La vista que se encarga de mostrar el objeto
     */
    private PanelDibujo vista;
    /**
     * La relacion con el controlador principal del fractal, esto se necesita
     * cuando se hace un zoom
     */
    private Fractalizador control;

    /**
     * Constructor, necesita de parametro los 3 objetos que son atributos en 
     * esta clase.
     * @param z
     * @param v
     * @param c
     */
    public ControlMouse(CuadroZoom z, PanelDibujo v, Fractalizador c) {
        this.zoom = z;
        this.vista = v;
        this.control = c;
        
        logger.info("Adiciona a la vista como observador del cuadro que hace zoom");
        this.zoom.addObserver(this.vista);
        this.vista.setControlZoom(this);
        
        logger.info("Se coloca a este objeto como listener de todo lo que haga " +
            "el raton sobre la vista");
        this.vista.addMouseListener(this);
        this.vista.addMouseMotionListener(this);
    }

    /**
     * Este es el metodo que manda a dibujar el cuadrado que indica el area sobre
     * la que el usuario desea hacer zoom
     * @param e
     */
    public void mouseDragged(MouseEvent e) {
        if (!this.zoom.isSelecting()) 
            return;
        int xf = e.getX();
        int yf = e.getY();
        
        // Solamente el cuadrado se puede hacer en direccion positiva
        if (xf <= this.zoom.getX0())
            xf = this.zoom.getX0()+1;
        if (yf <= this.zoom.getY0())
            yf = this.zoom.getY0()+1;
        
        this.zoom.setX1(xf);
        this.zoom.setY1(yf);
        
        this.zoom.listo();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Este metodo para marcar que hacemos selecting
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        this.zoom.setSelecting(true);
        logger.info("Comienza un nuevo zoom en: " + e.getX() + "x" + e.getY());
        this.zoom.setX0(e.getX());
        this.zoom.setY0(e.getY());
        this.zoom.setX1(e.getX() + 1);
        this.zoom.setY1(e.getY() + 1);
    }

    /**
     * Este metodo para parar el selecting y decirle al modelo que cambie de 
     * coordenadas
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        // SI no estaba seleccionando nada
        if (!this.zoom.isSelecting())
            return;
        this.zoom.setSelecting(false);
        
        // Para que no haga zoom sobre espacios muy pequenos (de 1 pixel)
        if (!zoom.seleccionValida()) {
            logger.info("Zoom sobre un area muy pequena: " + 
                        zoom.getX0() + "," + zoom.getY0() + " x " +
                        zoom.getX1() + "," + zoom.getY1());
            return;
        }
        
        logger.info("Suelta el mouse y comienza a hacer el zoom");
        control.hacerZoom(zoom);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }


    public void setZoom(CuadroZoom newzoom) {
        this.zoom = newzoom;
    }

    public CuadroZoom getZoom() {
        return zoom;
    }
}
