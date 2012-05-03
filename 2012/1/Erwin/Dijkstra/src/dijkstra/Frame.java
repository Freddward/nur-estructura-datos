package dijkstra;


import dijkstra.Estructura.Dijkstra;
import dijkstra.Estructura.Grafo;

import dijkstra.Estructura.Nodo;
import dijkstra.Estructura.RellenarGrafo;

import java.net.URL;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @Author Erwin Justiniano
 */
public class Frame
  extends JFrame
{

  private Dibujo panel;
  private Grafo grafo;
  private Dijkstra distra;
  private RellenarGrafo rellenador;
  private static Logger logger = Logger.getLogger(Frame.class);

  public Frame()
  {
    super("Perseguidor");
    rellenador = new RellenarGrafo();
    grafo = rellenador.rellenado();
    rellenador.relaciones(grafo);
    rellenador.estadoImagen(grafo);
    panel = new Dibujo(grafo);
    this.add(panel);
    this.setSize(616, 638);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }

  public void movida()
  {
    logger.info("recorre en dikstra");
    Vector<Integer> recorre = distra.getRecorrido(225, 1);
    for (int i = recorre.size() - 1; i >= 0; i--)
    {
      Iterator<Nodo<Integer>> is = grafo.getNodos().values().iterator();
      while (is.hasNext())
      {
        Nodo<Integer> n = is.next();
        n.setEstadoDibujo(n.getEstadoDibujo() == 3? 0:
                          n.getEstadoDibujo());
      }
      grafo.getNodos().get("" + i).setEstadoDibujo(3);

    }
  }

  public static void main(String[] as)
  {
    String resource = "/auditoria.properties";
    URL configFileResource;
    configFileResource = Frame.class.getResource(resource);
    PropertyConfigurator.configure(configFileResource);

    new Frame();

  }
}
