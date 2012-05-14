/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Williams
 */
public class MiFrame extends JFrame {

    private static Logger logger = Logger.getLogger(MiFrame.class);
    private MiPanel panel;
    private Grafo grafo;
    private RellenadorGrafo rellenador;

    public MiFrame() {
        super("Vladimir Attack");
        logger.info("se inicialiso el rellenador de nodos");
        rellenador = new RellenadorGrafo();
        grafo = rellenador.relleado();
        rellenador.relaciones(grafo);
        logger.info("se inicializo el rellenador de imagen");
        rellenador.estadoImagen(grafo);
        panel = new MiPanel(grafo);
        this.add(panel);
        this.setSize(616, 638);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String as[]) throws FileNotFoundException {
        String resource = "auditoria.properties";
        URL configFileResource;
        configFileResource = MiFrame.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        MiFrame f = new MiFrame();

    }
}
