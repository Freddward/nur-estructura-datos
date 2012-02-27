/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2mandelbrot;

/**
 *
 * @author Roberto A Pinto S
 */

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Pantalla {

    public static void main(String[] args) {
        String resource = "/proyecto2mandelbrot/auditoria.properties";
        URL configFileResource;
        configFileResource = fraktal.class.getResource(resource);
        PropertyConfigurator.configure(configFileResource);
        new fraktal(args);
    }
}
