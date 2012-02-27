/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import sun.java2d.pipe.DrawImage;

/**
 *
 * @author Williams
 */
public class Mipanel extends JPanel implements Observer ,MouseListener {
      private static Logger logger = Logger.getLogger(Mipanel.class);
    private Mandelbrot mandelBrot;
    private JButton arriba,abajo,izquierda,derecha;
    public Mipanel() {
        arriba=new JButton("arriba");
        abajo =new JButton("Abajo");
        this.addMouseListener(this);
        izquierda=new JButton("<");
        derecha=new JButton(">");
        mandelBrot=new Mandelbrot();
        this.setLayout(new BorderLayout());
       this.add(arriba, BorderLayout.NORTH);
      
       this.add(izquierda, BorderLayout.WEST);
        this.add(derecha, BorderLayout.EAST);
       this.add (abajo, BorderLayout.SOUTH);
        arriba.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mandelBrot.setEjeY(150);
                mandelBrot.construccionMandelbrot();
                logger.info("movio arriba");
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
        });
        abajo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               mandelBrot.setEjeY(-150);
                mandelBrot.construccionMandelbrot();
               logger.info("movio abajo");
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
        });
        izquierda.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mandelBrot.setEjeX(150);
                mandelBrot.construccionMandelbrot();
                logger.info("movio izquierda");
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
        });
        derecha.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mandelBrot.setEjeX(-150);
                mandelBrot.construccionMandelbrot();
                logger.info("movio derecha");
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
        });
    }   
   
    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(mandelBrot.getImagenMandelbrot(), 0, 0, this);  
    }
     @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        mandelBrot.setTamaño();
        mandelBrot.construccionMandelbrot();
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
