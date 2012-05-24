/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author EdwardFC
 */
public class ScreenMain extends JFrame {

    private PanelMain scr;
    private static Logger logger = Logger.getLogger(ScreenMain.class);

    public ScreenMain() {
        super("Method Morphing");
        scr = new PanelMain();
        this.getContentPane().add(scr);
        this.setSize(733, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setJMenuBar(scr.bar);
        this.setResizable(false);

    }

    public static void main(String[] args) {
        String resource = "Auditory.properties";
        URL configFileResource;
        configFileResource = ScreenMain.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        logger.info("Se inicio la aplicación");
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteSkin");

                } catch (Exception e) {
                }
                new ScreenMain();
            }
        });
    }
}
