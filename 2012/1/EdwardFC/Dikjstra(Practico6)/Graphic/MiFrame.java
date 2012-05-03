package Graphic;

import Logic.Dijkstra;
import Logic.Grafo;
import Logic.Nodo;
import Logic.RellenadorGrafo;
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

public class MiFrame extends JFrame {

    private MiPanel panel;
    private Grafo graph;
    private Dijkstra dij;
    private RellenadorGrafo filler;
    private static Logger logger = Logger.getLogger(MiFrame.class);

    public MiFrame() {
        super("Persuit's Game");
        logger.info("se inicialiso el filler de nodos");
        filler = new RellenadorGrafo();
        graph = filler.fill();
        filler.relaciones(graph);
        logger.info("se inicializo el filler de imagen");
        filler.estadoImagen(graph);
        dij = new Dijkstra(graph);
        panel = new MiPanel(graph);
        this.movidaThread();
        this.add(panel);
        this.setSize(616, 638);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void movidaThread() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                movida();
            }
        };
        Thread mover = new Thread(r);
        mover.start();
    }

    public void movida() {

        Vector<Integer> recorre = dij.getRecorrido(224, 0);
        for (int i = recorre.size() - 1; i >= 0; i--) {
            Iterator<Nodo<Integer>> is = graph.getNodos().values().iterator();
            while (is.hasNext()) {
                Nodo<Integer> n = is.next();
                n.setStateDraw(n.getStateDraw() == 3 ? 0 : n.getStateDraw());
            }
            try {
                graph.getNodos().get("" + i).setStateDraw(3);
                Thread.sleep(150);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public static void main(String as[]) {
        String resource = "Auditory.properties";
        URL configFileResource;
        configFileResource = MiFrame.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        logger.info("Se inicio la aplicación");
        new MiFrame();

    }
}
