/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pantalla;

import Logica.Pantalla;
import java.net.URL;
import javax.swing.JFrame;


/**
 *
 * @author Williams
 */
public class Main {
    
    public static void main (String[]args){
           String resource = "auditoria.properties";
                URL configFileResource; 
                configFileResource = Pantalla.class.getResource(resource);
                //PropertyConfigurator.configure(configFileResource);
                Pantalla f=new Pantalla();
                f.setSize(580,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
        }
}
