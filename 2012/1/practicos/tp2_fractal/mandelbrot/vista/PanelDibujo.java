package mandelbrot.vista;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;

import java.awt.Image;

import java.awt.image.BufferedImage;

import java.awt.image.ImageObserver;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import mandelbrot.control.ControlMouse;
import mandelbrot.control.Fractalizador;

import mandelbrot.modelo.CuadroZoom;
import mandelbrot.modelo.Matriz;

import org.apache.log4j.Logger;

/**
 * El panel donde se muestra todo el dibujo del fractal. Este panel lo unico que
 * hace es pintar el dibujo que se encuentra en el modelo, al cual accede a 
 * traves del controlador.
 */
public class PanelDibujo extends JPanel implements Observer {
    
    private final static Logger logger = 
        Logger.getLogger(PanelDibujo.class);
    
    /**
     * El control de toda la aplicacion
     */
    private Fractalizador control;
    /**
     * El control del raton
     */
    private ControlMouse controlZoom;
    /**
     * La referencia a la ventana superior 
     */
    private WinMandelbrot padre;

    /**
     * El constructor.
     * @param p
     */
    public PanelDibujo(WinMandelbrot p) {
        this.padre = p;
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inicializa el objeto sin niung layout ya que solamente se mostrara una figura.
     * @throws Exception
     */
    private void jbInit() throws Exception {
        this.setLayout( null );
    }
    
    /**
     * Sobre escribiomos el metodo de getPreferredSize para que cuando se despliegue
     * el panel dibujo los componentes se arreglen para dar el tamano que se necesite
     * @return
     */
    public Dimension getPreferredSize() {
        Matriz m = this.control.getModel();
        return new Dimension(m.getAncho(), m.getAlto());
    }

    /**
     * Solamente usado al incializar
     * @param ctl
     */
    public void setControl(Fractalizador ctl) {
        this.control = ctl;
    }
    
    /**
     * Solamente usado al incializar
     * @param ctl
     */
    public void setControlZoom(ControlMouse ctl) {
        this.controlZoom = ctl;
    }

    /**
     * Este es el metodo al que llama cuando alguno de los modelos cambia
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        repaint();
    }
    
    /**
     * Dibuja el fractal y si necesita dibuja el cuadro que muestra la seleccion
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        logger.debug("Pinta la imagen del fractal");
        Matriz m = this.control.getModel();
        Image img = new BufferedImage(m.getAncho(), m.getAlto(), BufferedImage.TYPE_INT_ARGB);
        Graphics gImg = img.getGraphics();
        
        for(int i=0; i<m.getAncho(); i++) {
            for(int j=0; j<m.getAlto(); j++) {
                int c = m.getMatriz(i, j);
                gImg.setColor(new Color(c,c,c));
                gImg.drawLine(i,j,i,j);
            }
        }
        
        g.drawImage(img, 0, 0, this);
        
        // ahora dibuja el cuadro si necesita
        CuadroZoom z = this.controlZoom.getZoom();
        if (!z.isSelecting())
            return;
        
        logger.debug("Pinta la imagen del cuadro de zoom con transparencia");
        // el cuaro es de un amarillo con transparencia
        g.setColor(new Color(255, 255, 0, 128));
        int anchoZoom = z.getX1() - z.getX0();
        int altoZoom = z.getY1() - z.getY0();
        g.fillRect(z.getX0(), z.getY0(), anchoZoom, altoZoom );
    }
}
