/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Logic.AlghoSot;
import Logic.BubbleSort;
import Logic.QuickSort;
import Main.Controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class Panel extends JPanel implements Observer, ActionListener {

    protected JMenuBar bar;
    private JMenu options, help;
    private JMenuItem bubble, quick, disorder, about, credits;
    private AlghoSot alg;
    private BubbleSort bbl;
    private QuickSort qck;
    private Image img;
    private PopScreen pop;
    private static Logger logger = Logger.getLogger(Controller.class);

    public Panel() {
        super();
        img = Toolkit.getDefaultToolkit().getImage("Fondo.png");
        alg = new AlghoSot();
        bbl = new BubbleSort();
        qck = new QuickSort();
        alg.addObserver(this);
        bbl.addObserver(this);
        qck.addObserver(this);
        alg.start();
        bar = new JMenuBar();
        options = new JMenu("Options");
        help = new JMenu("Help");
        bubble = new JMenuItem("BubbleSort");
        bubble.addActionListener(this);
        quick = new JMenuItem("QuickSort");
        quick.addActionListener(this);
        disorder = new JMenuItem("Disorder");
        disorder.addActionListener(this);
        about = new JMenuItem("About");
        about.addActionListener(this);
        credits = new JMenuItem("Credits");
        credits.addActionListener(this);
        this.add(bar);
        options.add(bubble);
        options.add(quick);
        options.add(disorder);
        help.add(about);
        help.add(credits);
        bar.add(options);
        bar.add(help);

    }

    @Override
    public void update(Observable o, Object arg) {

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        int a = 0;
        for (int element : alg.getArray()) {
            g.drawLine(a, element, a, 0);
            g.setColor(Color.blue);
            a += 3;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (bubble.equals(e.getSource())) {
            bubble.setVisible(false);
            quick.setVisible(false);
            disorder.setVisible(false);
            Thread bur = new Thread(new Runnable() {

                @Override
                public void run() {
                    bbl.sort(alg.getArray());
                    disorder.setVisible(true);
                }
            });
            bur.start();
            logger.info("Se llamó al método Burbuja");
        }
        if (quick.equals(e.getSource())) {
            bubble.setVisible(false);
            quick.setVisible(false);
            disorder.setVisible(false);
            Thread qui = new Thread(new Runnable() {

                @Override
                public void run() {
                    qck.sort(alg.getArray(), 0, alg.getArray().length - 1);
                    logger.info("Se ordenó por el método Quicksort");
                    disorder.setVisible(true);
                }
            });
            qui.start();
            logger.info("Se llamó al método Quicksort");
        }
        if (disorder.equals(e.getSource())) {
            bubble.setVisible(true);
            quick.setVisible(true);
            disorder.setVisible(true);
            Thread dis = new Thread(new Runnable() {

                @Override
                public void run() {
                    alg.disorder();
                }
            });
            dis.start();
            logger.info("Se llamó al método Dersodenar");

        }
        if (about.equals(e.getSource())) {
            pop = new PopScreen();
            logger.info("Se llamó a la ayuda");
        }
        if (credits.equals(e.getSource())) {
            JOptionPane.showMessageDialog(null, "Developed by Eduardo Flores C."
                    + "\n      eduardofc24@gmail.com" + "\n                    Feb. 2012");
            logger.info("Se llamó a los créditos");
        }
    }
}
