package morphing1;

import java.awt.Graphics;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Panel12 extends JPanel implements Observer{
  private  Logger logger = Logger.getLogger(Panel12.class);
    private BufferedImage pantalla;
    private Morphing1 morphing;

    public Panel12() {   
        super();
    } 
    public void imagen(BufferedImage img){
        if(img!=null)pantalla=img;
        repaint();
    }
    public void paintComponent(Graphics g) {
         
         g.drawImage(pantalla, 0, 0, this);  
       
    }
    public void update(Observable o, Object arg) {
        repaint();
    }
    public static void main(String []asd)
    {
      String resource = "/auditoria.properties"; 
      URL configFileResource; 
      configFileResource = Panel12.class.getResource(resource); 
      PropertyConfigurator.configure(configFileResource);
      
        JFrame frame=new JFrame();
        Morphing1 m=new Morphing1();
        Panel12 panel=new Panel12();
        frame.add(panel);
        panel.imagen(m.getCola().getRaiz().getContenido());  
        panel.imagen(m.getCola().getRaiz().getSiguiente().getContenido());
        panel.imagen(m.getCola().getRaiz().getSiguiente().getSiguiente().getContenido());
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
    }
}
   
   
    
    
