/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import Common.DAO.ArcoDAOMysql;
import Common.DAO.ArcoDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class RellenadorGrafo {
    private static Logger logger = Logger.getLogger(RellenadorGrafo.class);
    public RellenadorGrafo() {
    }

    public Grafo relleado() {
        Grafo grafo = new Grafo();
        for (int i = 1; i <= 225; i++) {
            grafo.addNodo("" + i, i);
        }
        logger.info("relleno 225 nodos");
        return grafo;
    }

    public void relaciones(Grafo gra) {
        Grafo grafo = gra;
        try {
           ArcoDAOMysql arc = new ArcoDAOMysql();
           Iterator<ArcoDTO> i = arc.seleccionar("2").iterator();
            while (i.hasNext()) {
                ArcoDTO xx = i.next();
               
                logger.info(xx);
                grafo.conectarDoble(xx.getOrigen(),xx.getDestino());
            }
        } catch (Exception l) {
        }

    }
    public void estadoImagen(Grafo gra){
         Grafo grafo = gra;
        FileReader tr;
        try {
            File t = new File("estado.txt");
            tr = new FileReader(t);
            BufferedReader br = new BufferedReader(tr);
            String linea = "";
            int p=1;
            while (br.ready()) {
                linea = br.readLine(); 
                grafo.getNodos().get(""+p).setEstadoDibujo(Integer.parseInt(linea));
                p++;
            }
            br.close();
            tr.close();
        } catch (Exception l) {
        }
        grafo.getNodos().get("36").setEstadoDibujo(3);
        grafo.getNodos().get("1").setEstadoDibujo(2);
        grafo.getNodos().get("211").setEstadoDibujo(4);

    }
}
