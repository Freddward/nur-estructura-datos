package dijkstra.Estructura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @Author Erwin Justiniano
 */
public class RellenarGrafo
{


  public Grafo rellenado()
  {
    Grafo grafo = new Grafo();
    for (int i = 0; i < 225; i++)
    {
      grafo.addNodo("" + i, i);
    }

    return grafo;
  }

  public void relaciones(Grafo gra)
  {
    Grafo grafo = gra;
    FileReader tr;
    try
    {
      File t = new File("relacion.txt");
      tr = new FileReader(t);
      BufferedReader br = new BufferedReader(tr);
      String linea1 = "";
      String linea2 = "";
      while (br.ready())
      {
        linea1 = br.readLine();
        linea2 = br.readLine();
        grafo.conectarDoble(linea1, linea2, 1);
      }
      br.close();
      tr.close();
    }
    catch (Exception l)
    {
    }

  }

  public void estadoImagen(Grafo gra)
  {
    Grafo grafo = gra;
    FileReader tr;
    try
    {
      File t = new File("estado.txt");
      tr = new FileReader(t);
      BufferedReader br = new BufferedReader(tr);
      String linea = "";
      int p = 1;
      while (br.ready())
      {
        linea = br.readLine();
        grafo.getNodos().get("" +
                             p).setEstadoDibujo(Integer.parseInt(linea));
        p++;
      }
      br.close();
      tr.close();
    }
    catch (Exception l)
    {
    }
    grafo.getNodos().get("224").setEstadoDibujo(3);
    grafo.getNodos().get("0").setEstadoDibujo(2);

  }
}
