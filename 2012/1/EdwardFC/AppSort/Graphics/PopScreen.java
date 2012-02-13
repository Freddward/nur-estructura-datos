/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import javax.swing.JFrame;

/**
 *
 * @author EdwardFC
 */
public class PopScreen extends JFrame {
    
    private PopPanel pop;
    
    public PopScreen() {
        super();
        pop = new PopPanel();
        this.getContentPane().add(pop);
        this.setSize(580, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
}
