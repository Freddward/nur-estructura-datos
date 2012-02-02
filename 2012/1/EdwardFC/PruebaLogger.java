/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author EdwardFC
 */
public class PruebaLogger {

    private static Logger logger = Logger.getLogger(PruebaLogger.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String resource = "/Auditoria.properties";
        URL configFileResource;
        configFileResource = PruebaLogger.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        PruebaLogger obj = new PruebaLogger();
        obj.hacer();
    }

    public void hacer() {
        logger.debug("Esta por imprimir hola");
        System.out.println("Hola");
        logger.info("Ya imprimio hola");
    }
}
