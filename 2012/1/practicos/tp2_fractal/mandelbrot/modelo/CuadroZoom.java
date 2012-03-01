package mandelbrot.modelo;

import java.util.Observable;

import mandelbrot.vista.PanelDibujo;

import org.apache.log4j.Logger;

/**
 * Esta clase solamente contiene el estado del cuadro que hara el zoom
 */
public class CuadroZoom extends Observable {
    
    private final static Logger logger = 
        Logger.getLogger(CuadroZoom.class);
        
    /**
     * Indica si el usuario esta seleccionanddo un zoom para realizar
     */
    private boolean selecting;
    
    /**
     * Extremo superior izquierdo en el eje x
     */
    private int x0;
    /**
     * Extremo superior izquierdo en el eje y
     */
    private int y0;
    /**
     * Extremo inferior derecho en el eje x
     */
    private int x1;
    /**
     * Extremo inferior derecho en el eje y
     */
    private int y1;
    
    public CuadroZoom() {
        selecting = false;
    }
    
    /**
     * Indica al observer que este modelo ha cambiado
     */
    public void listo() {
        this.setChanged();
        this.notifyObservers();
    }


    public void setX0(int newx0) {
        this.x0 = newx0;
    }

    public int getX0() {
        return x0;
    }

    public void setY0(int newy0) {
        this.y0 = newy0;
    }

    public int getY0() {
        return y0;
    }

    public void setX1(int newx1) {
        this.x1 = newx1;
    }

    public int getX1() {
        return x1;
    }

    public void setY1(int newy1) {
        this.y1 = newy1;
    }

    public int getY1() {
        return y1;
    }

    public void setSelecting(boolean newselecting) {
        this.selecting = newselecting;
    }

    public boolean isSelecting() {
        return selecting;
    }

    /**
     * Si el cuadro es muy pequeno no se dibuja
     * @return
     */
    public boolean seleccionValida() {
        return ((this.x1 - this.x0) > 1 && (this.y1 - this.y0) > 1);
    }
}
