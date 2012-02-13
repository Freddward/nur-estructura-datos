/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



/**
 *
 * @author Sistemas
 */
public class Consola {

    private static Logger logger = Logger.getLogger(Consola.class);

    public static void main(String[] args) {
        String resource =
                "/auditoria.properties";
        URL configFileResource;
        configFileResource = Consola.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);

        Window w = new Window();
        w.setVisible(true);
    }
}
