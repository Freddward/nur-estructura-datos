/*
 * To change frm template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author EdwardFC
 */
public class PopPanel extends JPanel {

    private Image img;

    public PopPanel() {
        super();
        this.img = Toolkit.getDefaultToolkit().getImage("txt.png");
      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }

   

}
