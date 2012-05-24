/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Line;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author EdwardFC
 */
public class PanelMovement extends JPanel implements Runnable {

    private int index = 0;
    private Vector<BufferedImage> images;
    private Line<BufferedImage> img;

    public PanelMovement(Line<BufferedImage> im) {
        super();
        setBounds(0, 0, 240, 400);
        images = new Vector<BufferedImage>();
        img = im;
        for (int i = 0; i < img.size(); i++) {
            images.add(img.pop());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(images.get(index), 0, 0, this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(500);
                if (index < images.size()-1) {
                    index++;
                } else {
                    index = 0;
                    Thread.sleep(2000);
                }
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
