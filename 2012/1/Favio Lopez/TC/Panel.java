package TC;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Panel extends JPanel implements Observer {

    private Arreglos objArreglo; 

    public Panel(Arreglos a) {
        objArreglo = a;
        objArreglo.addObserver(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(300,300);
        g.setColor(Color.red);
        int y = 0;
        for (int i: objArreglo.getArreglo()) {
            g.drawLine(0, y, i, y);
            y++;
        }


    }
    
    public void update(Observable o, Object arg) {
        this.repaint();
    }

 
}
