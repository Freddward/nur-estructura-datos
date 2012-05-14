package pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.log4j.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EdwardFC
 */
public class MiPanel extends JPanel implements Observer, MouseListener {

    private Grafo grafo;
    private Image[] imagenes;
    private Dijkstra distra;
    private int de, a, meta;
    private static Logger logger = Logger.getLogger(MiPanel.class);

    public MiPanel(Grafo gr) {
        meta = 211;
        this.grafo = gr;
        distra = new Dijkstra(grafo);
        imagenes = new Image[5];
        imagenes[2] = Toolkit.getDefaultToolkit().getImage("bueno.png");
        imagenes[0] = Toolkit.getDefaultToolkit().getImage("piso.png");
        imagenes[1] = Toolkit.getDefaultToolkit().getImage("malla.png");
        imagenes[3] = Toolkit.getDefaultToolkit().getImage("malo.png");
        imagenes[4] = Toolkit.getDefaultToolkit().getImage("meta.png");
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        int x1 = 0, y1 = 0;

        for (int i = 1; i <= 36; i++) {
            Nodo<Integer> n = grafo.getNodos().get("" + i);
            n.setX(x1);
            n.setY(y1);
            g.drawImage(imagenes[n.getEstadoDibujo()], n.getX(), n.getY(), this);
            x1 += 40;
            int A = Integer.parseInt(n.getId());
            if ((n.getContenido()) % 6 == 0) {
                x1 = 0;
                y1 += 40;
            }

        }
    }
    private boolean detener;

    public void movidaThread() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                movida();
            }
        };


        Thread procesoMovida = new Thread(r);
        procesoMovida.start();
    }

    public void movida() {
        Vector<Integer> recorre = distra.distanciaMasCorta(de, a);
        for (int i = recorre.size() - 1; i > 0; i--) {
            Iterator<Nodo<Integer>> iso = grafo.getNodos().values().iterator();
            while (iso.hasNext()) {
                Nodo<Integer> n = iso.next();
                n.setEstadoDibujo(n.getEstadoDibujo() == 3 ? 0 : n.getEstadoDibujo());
            }
            try {
                grafo.getNodos().get("" + recorre.get(i)).setEstadoDibujo(3);


                repaint();
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Iterator<Nodo<Integer>> i = grafo.getNodos().values().iterator();
        while (i.hasNext()) {
            Nodo<Integer> n = i.next();
            if (n.getArea().contains(e.getPoint())) {
                if (n.getEstadoDibujo() == 0) {
                    int cambio = 0;
                    int malo = 0;
                    Iterator<Nodo<Integer>> i2 = grafo.getNodos().values().iterator();
                    while (i2.hasNext()) {
                        Nodo<Integer> n2 = i2.next();
                        if (n2.getEstadoDibujo() == 2) {
                            cambio = n2.getContenido();
                        }
                        if (n2.getEstadoDibujo() == 3) {
                            malo = n2.getContenido();
                        }
                    }
                    logger.info("se movio el personaje del nodo " + cambio + " al " + n.getContenido());
                    n.setEstadoDibujo(2);
                    grafo.getNodos().get("" + cambio).setEstadoDibujo(0);
                    a = n.getContenido();
                    de = malo;
                    this.movidaThread();
                    if (n.getContenido() == meta) {
                        JOptionPane.showMessageDialog(null, "Pasaste");
                    }
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
