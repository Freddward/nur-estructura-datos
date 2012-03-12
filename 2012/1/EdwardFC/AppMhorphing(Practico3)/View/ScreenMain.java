/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author EFC
 */
public class ScreenMain extends JFrame {

    private PanelMain scr;
    private static Logger logger = Logger.getLogger(ScreenMain.class);

    public ScreenMain() {
        super("Method Morphing");
        scr = new PanelMain();
        this.getContentPane().add(scr);
        this.setSize(736, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(scr.bar);
    }

    public static void main(String[] args) {
        String resource = "Auditory.properties";
        URL configFileResource;
        configFileResource = ScreenMain.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        logger.info("Se inicio la aplicación");
        new ScreenMain();

    }
}
