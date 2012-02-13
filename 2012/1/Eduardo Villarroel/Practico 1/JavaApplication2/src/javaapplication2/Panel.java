/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Eduardo
 */
public class Panel extends JPanel implements Observer {

    private Arreglo objAr;

    public Panel(Arreglo objArray) {

        objAr = objArray;
        objAr.addObserver(this);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        this.setBackground(Color.red);
        this.setSize(600, 660);

        int aux = 4;
        for (int i = 0; i < objAr.getArreglo().length; i++) {
            gc.drawLine(0, aux, objAr.getArreglo()[i], aux);
            gc.setColor(Color.BLUE);
            aux = aux + 2;
            // this.repaint();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
