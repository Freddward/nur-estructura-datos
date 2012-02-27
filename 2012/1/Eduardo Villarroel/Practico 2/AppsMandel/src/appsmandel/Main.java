/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package appsmandel;

import java.net.URL;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Eduardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         String resource = "auditoria.properties";
        URL configFileResource;
        configFileResource = Main.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);

        Ventana win = new Ventana();
        win.setVisible(true);
    }

}
