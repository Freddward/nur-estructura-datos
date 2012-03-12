/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package morphing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Williams
 */
public class Mipanel extends JPanel implements Observer  {
    private BufferedImage pantalla;
    public Mipanel() {   
        super();
    } 
    public void ImagenActual(BufferedImage img){
        if(img!=null)pantalla=img;
        repaint();
    }
     @Override
    public void paintComponent(Graphics g) {
         
         g.drawImage(pantalla, 0, 0, this);    
    }
    public void update(Observable o, Object arg) {
        repaint();
    }
}
   
   
    
    
