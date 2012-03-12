/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomorphing;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Roberto A Pinto S
 */

public class PanelMain extends JPanel implements Observer, ActionListener {

    private static Logger logger= Logger.getLogger(PanelMain.class);
    protected JMenuBar bar;
    private JMenu options, help;
    private JButton Saltos;
    private JMenuItem Inicio, Salir;
    private Morphing morphing;
    private BufferedImage imagenInicial;
    private BufferedImage img;
    private BufferedImage imagenFinal;
    private Pila<BufferedImage> Pila;

    public PanelMain() {
        super();
        morphing = new Morphing();
        morphing.addObserver(this);
        Pila = new Pila<BufferedImage>();
        Pila.addObserver(this);
        bar = new JMenuBar();
        options = new JMenu("Options");
        help = new JMenu("Help");
        Inicio = new JMenuItem("Inicio");
        Inicio.addActionListener(this);
        Saltos = new JButton("Salto");
        Saltos.addActionListener(this);
        Salir = new JMenuItem("Salir");
        Salir.addActionListener(this);
        options.add(Inicio);
        options.add(Salir);
        bar.add(options);
        bar.add(Saltos);
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
        g.drawImage(imagenInicial, 0, 5, this);
        g.drawImage(img, 245, 5, this);
        g.drawImage(imagenFinal, 490, 5, this);
    }

    @Override
    public void update(Observable o, Object arg) {
       logger.info("Marca los cambiosque hace el metodo observado");
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Inicio.equals(e.getSource())) {
            this.imagenInicial = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
            this.imagenInicial = loadImage("sol.jpg");
            this.imagenFinal = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
            this.imagenFinal = loadImage("puma.jpg");
            this.img = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
       
            Thread inicio = new Thread(new Runnable() {

                @Override
                public void run() {
                    
                    String saltando=JOptionPane.showInputDialog("Meter la cantidad de Saltos dentro de la Pila +1");
                    try{
                    int saltito=Integer.parseInt(saltando);
                    morphing.setSaltos(saltito);
                    }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Insertar bien los datos");
                    }
                    
                    
                    
                    while (Pila.size() <= morphing.getSaltos()) {
       
                        Pila.push(morphing.morphingImage(imagenInicial, imagenFinal, (Pila.size())));
                        
                                                    
                        }
                    
                }
            });
            inicio.start();
            
        }
        if (Saltos.equals(e.getSource())) {

            Thread reset = new Thread(new Runnable() {

                @Override
                public void run() {
                    if(Pila.size() > 0)
                    {
                    img = Pila.pop();
                    repaint();                   
                    }
                    else{
                    Saltos.setEnabled(false);
                    }
                }
            });
            reset.start();
            
        }
        
        
        
        
        
        if(Salir.equals(e.getSource()))
        {
            System.exit(0);
        }

    }
    
}

