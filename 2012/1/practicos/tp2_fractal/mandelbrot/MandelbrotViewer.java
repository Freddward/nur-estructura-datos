package mandelbrot;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.net.URL;

import javax.swing.JFrame;
import javax.swing.UIManager;

import mandelbrot.vista.WinMandelbrot;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Programa para visualizar un conjunto de mandelbrot. Con el raton se pueden hacer
 * zooms en el conjunto que se pone al dia automaticamente.
 */
public class MandelbrotViewer {
    
    private final static Logger logger = 
        Logger.getLogger(MandelbrotViewer.class);
    
    /**
     * Constructor de la aplicacion
     */
    public MandelbrotViewer() {
        logger.debug("Crea el frame de la aplicacion");
        JFrame frame = new WinMandelbrot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        
        logger.debug("Coloca el frame al centro de la pantalla");
        frame.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }

    /**
     * El main de la aplicacion, construye primeramente el tema de log4j
     * @param args
     */
    public static void main(String[] args) {
        String resource = "/auditoria.properties";
        URL configFileResource = MandelbrotViewer.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MandelbrotViewer();
    }
}
