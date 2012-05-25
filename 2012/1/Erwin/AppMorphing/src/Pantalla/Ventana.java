package Pantalla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.jvnet.substance.SubstanceLookAndFeel;

public class Ventana
  extends JFrame
  implements ActionListener
{

  private Panel panel;
  private JButton algo;
  private static Logger logger = Logger.getLogger(Ventana.class);

  public Ventana()
  {
    super("Morphing");

    panel = new Panel();

    this.add(panel);
    this.setJMenuBar(panel.menuP);
    this.setSize(700, 450);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

  }

  public static void main(String[] args)
  {
    String resource = "/auditoria.properties";
    URL configFileResource;
    configFileResource = Ventana.class.getResource(resource);
    PropertyConfigurator.configure(configFileResource);
    java.awt.EventQueue.invokeLater(new Runnable()
      {

        public void run()
        {
          try
          {
            JFrame.setDefaultLookAndFeelDecorated(true);
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.NebulaBrickWallSkin");
            //org.jvnet.substance.skin.CremeSkin
          }
          catch (Exception e)
          {
            JOptionPane.showMessageDialog(null, "A HABIDO UN ERROR");
          }

        }
      });
    new Ventana();

  }

  public void actionPerformed(ActionEvent e)
  {
    if (algo.equals(e.getSource()))
    {

    }
  }
}

