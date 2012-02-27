/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appsmandel;

import javax.swing.JFrame;

/**
 *
 * @author Eduardo
 */
public class Ventana extends JFrame {

    private PanelMandelbrot objpn;

    public Ventana() {
        objpn = new PanelMandelbrot();
        this.setSize(800, 700);
        this.getContentPane().add(objpn);
        this.setTitle("Mandelbrot");
      //  this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    
}


