/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Williams
 */
public class Acercamiento extends JPanel implements Observer {

     @Override
    public void paintComponent(Graphics g) {
       
         super.paintComponents(g);
         g.drawImage(null, WIDTH, WIDTH, WIDTH, WIDTH, this);
       
    }
     @Override
    public void update(Observable o, Object arg) {
        
        this.repaint();
    }
}
