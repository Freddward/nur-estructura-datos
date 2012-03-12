/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Morphing;

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
 * @author G & M
 */
public class Panel extends JPanel implements Observer, ActionListener {
    
    private static Logger logger = Logger.getLogger(Panel.class);
    protected JMenuBar bar;
    private JMenu opciones;
    private JMenuItem Empezar, Salir, Cambios;
    //private JButton Cambios;
    private Morphing morphing;
    private BufferedImage imagenInicial;
    private BufferedImage img;
    private BufferedImage imagenFinal;
    private Pila<BufferedImage> pila;

    public Panel() {
        super();
        morphing = new Morphing();
        morphing.addObserver(this);
        pila = new Pila<BufferedImage>();
        pila.addObserver(this);        
        bar = new JMenuBar();
        opciones = new JMenu("Archivo");
        Empezar = new JMenuItem("Empezar");
        Empezar.addActionListener(this);
        Cambios = new JMenuItem("Cambios");
        Cambios.addActionListener(this);
        Salir = new JMenuItem("Salir");
        Salir.addActionListener(this);
        opciones.add(Empezar);
        opciones.add(Salir);
        bar.add(opciones);
        bar.add(Cambios);
        this.add(bar);
    }

    public static BufferedImage cargarImagen(String nomImg) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(nomImg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(imagenInicial, 0, 5, this);
        g.drawImage(img, 245, 5, this);
        g.drawImage(imagenFinal, 490, 5, this);
    }

    @Override
    public void update(Observable o, Object arg) {
       // logger.info("Se esta realizando el pintado");
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //logger.info("Se cargan las imagenes");
        if (Empezar.equals(e.getSource())) {
        logger.info("Se inicializan las imagenes las imagenes");    
        this.imagenInicial = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.imagenInicial = cargarImagen("Maa.jpg");
        this.imagenFinal = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.imagenFinal = cargarImagen("Ma.jpg");
        this.img = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        
            Thread comienzo = new Thread(new Runnable() {

                @Override
                public void run() {

                    morphing.setPasos(7);

                    while (pila.size() <= morphing.getPasos()) {
       
                        pila.push(morphing.morphingImagen(imagenInicial, imagenFinal, (pila.size())));
                        
                        //System.out.println(pila.size());
                    }

                }
            });
            comienzo.start();
            //logger.info("Se llamó al método Burbuja");
        }
        if (Cambios.equals(e.getSource())) {
            logger.info("Se realizan los pintados de las imagenes");
            
            Thread reset = new Thread(new Runnable() {

                @Override
                public void run() {
                    if (pila.size() > 0) {
                        img = pila.pop();
                        repaint();
                    } else {
                        Cambios.setEnabled(false);
                    }
                    repaint();

                }
            });
            reset.start();
           // logger.info("Se realizan los pintados de las imagenes");
        }
        if (Salir.equals(e.getSource())){
            System.exit(0);
            logger.info("Fin del Programa");
        }

        
    }
}
