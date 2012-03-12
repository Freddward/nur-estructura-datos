/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Morphing;

import java.net.URL;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author G & M
 */
public class Pantalla extends JFrame{
    private static Logger logger = Logger.getLogger(Pantalla.class);
    private Panel miPanel;
    public Pantalla(){
        super("Morphing");
        miPanel = new Panel();
        this.getContentPane().add(miPanel);
        this.setSize(740, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(miPanel.bar);
    }
    public static void main(String[] args) {
        String resource = "/Auditoria.properties";
        URL configFileResource;
        configFileResource = Pantalla.class.getResource(resource);
        configFileResource = Panel.class.getResource(resource);
        configFileResource = Cadena.class.getResource(resource);
        configFileResource = Morphing.class.getResource(resource);
        configFileResource = Pila.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        new Pantalla();
        
    }
}
