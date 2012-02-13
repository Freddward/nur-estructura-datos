/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Main.Controller;
import javax.swing.JFrame;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class Screen extends JFrame {

    private Panel scr;
    private static Logger logger = Logger.getLogger(Controller.class);

    public Screen() {
        super("Sort Alghoritms");
        
        scr = new Panel();
        this.getContentPane().add(scr);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(scr.bar);
    }

    public static void main(String[] args) {
        new Screen();
    }
}
