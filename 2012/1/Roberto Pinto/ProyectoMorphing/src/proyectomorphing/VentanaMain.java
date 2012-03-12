/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomorphing;

import java.net.URL;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Roberto A Pinto S
 */
public class VentanaMain extends JFrame{
    
    private static Logger logger=Logger.getLogger(VentanaMain.class);
    private PanelMain pantalla;
    public VentanaMain(){
        super("Morphing Pinto");
        pantalla = new PanelMain();
        this.getContentPane().add(pantalla);
        this.setSize(745, 455);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(pantalla.bar);
    }
    public static void main(String[] args) {
        String resource =
                "/proyectomorphing/auditoria.properties";
        URL configFileResource; 
        configFileResource = VentanaMain.class.getResource(resource);
        configFileResource = Morphing.class.getResource(resource); 
        PropertyConfigurator.configure(configFileResource);
        new VentanaMain();
    }
}
