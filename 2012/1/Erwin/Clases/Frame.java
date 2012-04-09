package metodo;


import java.net.URL;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Frame
  extends JFrame
{
  protected Logger logger = Logger.getLogger(Frame.class);

  public Frame()
  {
    this.setVisible(true);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(456, 456);

  }

  public static void main(String[] args)
  {
    String resource = "/auditoria.properties";
    URL configFileResource;
    configFileResource = Frame.class.getResource(resource);
    PropertyConfigurator.configure(configFileResource);


    Frame o = new Frame();
    Panel a = new Panel();
    o.add(a);
  }
}
