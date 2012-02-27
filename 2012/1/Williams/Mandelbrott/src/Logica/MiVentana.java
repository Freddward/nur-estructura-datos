/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.net.URL;
import javax.swing.JFrame;
import org.apa00che.log4j.PropertyConfigurator;

/*  * 
 *
 * @author Williams
 */
public class MiVentana {
     public static void main (String[]args){
                String resource = "/auditoria.properties";
                URL configFileResource; 
               configFileResource = MiVentana.class.getResource(resource);
                PropertyConfigurator.configure(configFileResource);
                JFrame f=new JFrame();
                Mipanel p=new Mipanel();
                f.add(p);
                f.setSize(800,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
        }
}
