/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appsmandel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 *
 * @author Eduardo
 */
public class PanelMandelbrot extends JPanel  implements MouseListener{
    private Mandelbrot mandel;
    
    public PanelMandelbrot (){

        this.addMouseListener(this);
        mandel = new Mandelbrot();
        //mandel.addObserver(this);
        this.setSize(800, 700);

    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        
       //
        //this.setBackground(Color.BLACK);
        gc.drawImage(mandel.getImgMandel(), 0, 0, this);
        // gc.drawString("funciona esta huevada", 100, 150);
       // gc.setColor(Color.red);
        //this.setVisible(true);
        this.repaint();
    }

    public void mouseClicked(MouseEvent e) {
    
    //mandel.setMovHor();
    //mandel.setMovVer();
    //this.repaint();
    
    mandel.setTamano(e.getX(), e.getY() );
    mandel.inicializarMandel();
       
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
        
    }

  

}
