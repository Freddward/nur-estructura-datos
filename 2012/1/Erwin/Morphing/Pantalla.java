package morphing;

import java.net.URL;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Erwin
 */

public class Pantalla
  extends JFrame
{
  public Pantalla()
  {
    super("Moprhing");
  }

  public static void main(String[] args)
  {
    String resource = "/auditoria.properties";
    URL configFileResource;
    configFileResource = Pantalla.class.getResource(resource);
    PropertyConfigurator.configure(configFileResource);

    Pantalla o = new Pantalla();
    Morphing obj = new Morphing();
    Cola co = new Cola();
    o.add(obj);
    //obj.agregarImage(obj.getCola().getRaiz().getContenido());
    //obj.agregarImage(obj.getCola().getRaiz().getSiguiente().getContenido());
    obj.agregarImage(obj.getCola().getRaiz().getSiguiente().getSiguiente().getContenido());

    o.setVisible(true);
    o.setDefaultCloseOperation(EXIT_ON_CLOSE);
    o.setSize(310, 300);
    o.setLocationRelativeTo(null);


  }
}
