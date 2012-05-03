package logica;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.util.Observable;
import java.util.Observer;

import java.util.logging.Logger;

import javax.swing.JPanel;

/**
 * El panel donde se muestra todo el dibujo del fractal. Este panel lo unico que
 * hace es pintar el dibujo que se encuentra en el modelo, al cual accede a 
 * traves del controlador.
 */
public class PanelDibujo extends JPanel  implements Observer {

   // private static final Logger logger = Logger.getLogger(PanelDibujo.class);

    private int x = 30, y = 30;
    private int[] enemy={12,12};
    
    private int x1 = (enemy[1]*30)-30,y1=(enemy[0]*30)-30;


    /**
     * La referencia a la ventana superior 
     */
    private VentanaLaberinto padre;

    private DibujoGrafo elDibujoGrafo;

    /**
     * El constructor.
     * @param p
     */
    public PanelDibujo() {
        this.padre = null;
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
        this.setLayout(null);
    }

    /**
     * Sobre escribiomos el metodo de getPreferredSize para que cuando se despliegue
     * el panel dibujo los componentes se arreglen para dar el tamano que se necesite
     * @return
     */
    public Dimension getPreferredSize() {

        return new Dimension(700, 700);
    }

    /**
     * Este es el metodo al que llama cuando alguno de los modelos cambia
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        if (o instanceof Grafo)
            elDibujoGrafo = new DibujoGrafo((Grafo<DibujoNodo>)o);
        repaint();
    }

    /**
     * Dibuja el fractal y si necesita dibuja el cuadro que muestra la seleccion
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = 30, height = 30;
        //logger.debug("Pinta la imagen del grafo");

        if (elDibujoGrafo == null) {
          //  logger.warn("No existe grafo cargado.");
            return;
        }
        
        elDibujoGrafo.dibujar(g);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, width, height);
        g.setColor(Color.RED);
        g.fillOval(x1, y1, width, height);
    }


    public void setPoint(int x, int y) {
        this.x = x;
        this.y =y;
    }
    
    public void setPointenemy(int x, int y) {
        enemy[0]=x;
        enemy[1]=y;
        this.x1 =  (enemy[1]*30)-30;
        this.y1 = (enemy[0]*30)-30;
    }




}
