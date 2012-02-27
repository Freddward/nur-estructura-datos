package mandelbroot;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.PropertyConfigurator;

public class Ejecucion extends JFrame
{
  private JPanel principal;
  private Logica3 objLogica3;
  private Panel1 objPanel1;  
 public static void main(String[] args)
  {
    String resource = "/auditoria.properties"; 
    URL configFileResource; 
    configFileResource = Logica3.class.getResource(resource); 
    PropertyConfigurator.configure(configFileResource);
      JFrame frame=new Ejecucion();
      frame.setSize(800,700);
      frame.setResizable(false);
      frame.setTitle("mandelbroot");
      Panel1 e=new Panel1();
      frame.add(e);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setLocationRelativeTo(null);
  }
}
