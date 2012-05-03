/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.log4j.Logger;

/**
 *
 * @author Williams&&Erwin&&EdwardFC
 */
public class RellenadorGrafo {

    private static Logger logger = Logger.getLogger(RellenadorGrafo.class);

    public RellenadorGrafo() {
    }

    public Grafo fill() {
        Grafo graph = new Grafo();
        for (int i = 0; i < 225; i++) {
            graph.addNodo("" + i, i);
        }
        logger.info("relleno 225 nodos");
        return graph;
    }

    public void relaciones(Grafo gra) {
        Grafo graph = gra;
        FileReader tr;
        try {
            File t = new File("relacion.txt");
            tr = new FileReader(t);
            BufferedReader br = new BufferedReader(tr);
            String linea1 = "";
            String linea2 = "";
            while (br.ready()) {
                linea1 = br.readLine();
                linea2 = br.readLine();
                graph.conectDouble(linea1, linea2, 1);
            }
            br.close();
            tr.close();
        } catch (Exception l) {
        }

    }

    public void estadoImagen(Grafo gra) {
        Grafo graph = gra;
        FileReader tr;
        try {
            File t = new File("estado.txt");
            tr = new FileReader(t);
            BufferedReader br = new BufferedReader(tr);
            String linea = "";
            int p = 1;
            while (br.ready()) {
                linea = br.readLine();
                graph.getNodos().get("" + p).setStateDraw(Integer.parseInt(linea));
                p++;
            }
            br.close();
            tr.close();
        } catch (Exception l) {
        }
        graph.getNodos().get("224").setStateDraw(3);
        graph.getNodos().get("0").setStateDraw(2);

    }
}
