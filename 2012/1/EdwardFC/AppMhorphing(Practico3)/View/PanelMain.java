/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Line;
import Logic.Morphing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class PanelMain extends JPanel implements Observer, ActionListener {

    protected JMenuBar bar;
    private JMenu options, help;
    private JMenuItem start, next, restart, credits;
    private Morphing morphing;
    private BufferedImage imageInitial;
    private BufferedImage img;
    private BufferedImage imageFinal;
    private Line<BufferedImage> line;
    private static Logger logger = Logger.getLogger(ScreenMain.class);

    public PanelMain() {
        super();
        setBounds(0, 0, 736, 450);
        setBackground(Color.BLACK);
        morphing = new Morphing();
        morphing.addObserver(this);
        line = new Line<BufferedImage>();
        line.addObserver(this);
        this.imageInitial = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.imageInitial = loadImage("ironman.jpg");
        this.imageFinal = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.imageFinal = loadImage("capitanamerica.jpg");
        this.img = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        bar = new JMenuBar();
        options = new JMenu("Options");
        help = new JMenu("Help");
        start = new JMenuItem("Start");
        start.addActionListener(this);
        next = new JMenuItem("Next");
        next.addActionListener(this);
        restart = new JMenuItem("Restart");
        restart.addActionListener(this);
        credits = new JMenuItem("Credits");
        credits.addActionListener(this);
        options.add(start);
        options.add(next);
        options.add(restart);
        help.add(credits);
        bar.add(options);
        bar.add(help);
        this.add(bar);
    }

    public static BufferedImage loadImage(String pathname) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathname));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(imageInitial, 0, 2, this);
        g.drawImage(img, 242, 2, this);
        g.drawImage(imageFinal, 484, 2, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start.equals(e.getSource())) {

            Thread begin = new Thread(new Runnable() {

                @Override
                public void run() {
                    logger.info(
                    "Se dio el numero de nodos para empezar la transformación");
                    String x = JOptionPane.showInputDialog("Ingrese la cantidad de Nodos");
                    int nodo = 0;
                    boolean valid = true;

                    if (x == null) {
                        valid = false;
                    } else {
                        try {
                            nodo = Integer.parseInt(x);
                        } catch (NumberFormatException e) {
                            valid = false;
                            JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
                            logger.info("Se ingreso un caracter no numerico");
                        }

                        if (valid) {
                            morphing.setPhases(nodo);
                        } else {
                            this.run();
                        }
                    }
                    start.setEnabled(false);
                    while (line.size() <= morphing.getPhases()) {
                        line.push(morphing.morphingImage(imageInitial, imageFinal, (line.size())));
                    }

                }
            });
            begin.start();
 
        }


        if (next.equals(e.getSource())) {

            Thread other = new Thread(new Runnable() {

                @Override
                public void run() {
                    if (line.size() > 0) {
                        img = line.pop();
                        repaint();
                    } else {
                        next.setEnabled(false);
                    }
                }
            });
            other.start();
            logger.info("Se llama al metodo pop de la cola");
        }
        if (restart.equals(e.getSource())) {
            Thread reset = new Thread(new Runnable() {

                @Override
                public void run() {
                    img = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
                    repaint();
                    start.setEnabled(true);
                    next.setEnabled(true);
                }
            });
            reset.start();
            logger.info("Se reinicia la aplicación");
        }

        if (credits.equals(e.getSource())) {
            JOptionPane.showMessageDialog(null, "Developed by Eduardo Flores C."
                    + "\n      eduardofc24@gmail.com" + "\n                    March. 2012");
            //logger.info("Se llamó a los créditos");
        }
    }
}
