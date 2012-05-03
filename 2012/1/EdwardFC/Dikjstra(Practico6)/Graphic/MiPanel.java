package Graphic;

import Logic.Grafo;
import Logic.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import sun.java2d.loops.DrawRect;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EdwardFC
 */
public class MiPanel extends JPanel implements Observer, MouseListener {

    private Grafo graph;
    private Image[] images;
    private static Logger logger = Logger.getLogger(MiPanel.class);

    public MiPanel(Grafo gr) {
        this.graph = gr;
        images = new Image[4];
        images[2] = Toolkit.getDefaultToolkit().getImage("bueno.png");
        images[0] = Toolkit.getDefaultToolkit().getImage("piso.png");
        images[1] = Toolkit.getDefaultToolkit().getImage("malla.png");
        images[3] = Toolkit.getDefaultToolkit().getImage("malo.png");
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        int x1 = 0, y1 = 0;

        for (int i = 0; i < 225; i++) {
            Nodo<Integer> n = graph.getNodos().get("" + i);
            n.setX(x1);
            n.setY(y1);
            g.drawImage(images[n.getStateDraw()], n.getX(), n.getY(), this);
            x1 += 40;
            int A = Integer.parseInt(n.getId());
            if ((n.getContenido() + 1) % 15 == 0) {
                x1 = 0;
                y1 += 40;
            }

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Iterator<Nodo<Integer>> i = graph.getNodos().values().iterator();
        while (i.hasNext()) {
            Nodo<Integer> n = i.next();
            if (n.getArea().contains(e.getPoint())) {
                if (n.getStateDraw() == 0) {
                    int cambio = 0;
                    Iterator<Nodo<Integer>> i2 = graph.getNodos().values().iterator();
                    while (i2.hasNext()) {
                        Nodo<Integer> n2 = i2.next();
                        if (n2.getStateDraw() == 2) {
                            cambio = n2.getContenido();
                        }
                    }
                    logger.info("se movio el personaje del nodo " + cambio + " al " + n.getContenido());
                    n.setStateDraw(2);
                    graph.getNodos().get("" + cambio).setStateDraw(0);
                }
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
