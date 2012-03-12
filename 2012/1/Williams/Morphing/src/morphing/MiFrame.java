/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package morphing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MiFrame extends JFrame implements ActionListener {
    private static Logger logger = Logger.getLogger(MiFrame.class);
    private JButton siguiente, Reanudar;
    private Mipanel panel;
    private Morphing morp;
    private int cantFases;
    public MiFrame() {
        siguiente = new JButton("Siguiente");
        Reanudar = new JButton("Reanudar");
        panel = new Mipanel();
        panel.add(siguiente, BorderLayout.NORTH);
        panel.add(Reanudar, BorderLayout.NORTH);
        String Comienzo = JOptionPane.showInputDialog("En cuantas fases lo quiere ver el morphin");
        cantFases = Integer.parseInt(Comienzo);
        morp = new Morphing(cantFases);
        panel.ImagenActual(morp.getCola().pop());
        siguiente.addActionListener(this);
        Reanudar.addActionListener(this);
        this.add(panel);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (siguiente == e.getSource()) {
            if (morp.getCola().getRaiz() != null) {
                panel.ImagenActual(morp.getCola().pop());
            }
            logger.info("Cambio de imagen");
        }
        if (Reanudar == e.getSource()) {
            morp = new Morphing(cantFases);
            panel.ImagenActual(morp.getCola().pop());
            logger.info("Volvio a la primera imagen");
        }

    }
    public static void main(String[] asd) {
        String resource = "/auditoria.properties";
        URL configFileResource;
        configFileResource = MiFrame.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        new MiFrame();
    }
}
